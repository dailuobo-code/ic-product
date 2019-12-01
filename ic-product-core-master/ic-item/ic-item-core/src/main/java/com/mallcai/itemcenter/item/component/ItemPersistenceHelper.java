package com.mallcai.itemcenter.item.component;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mallcai.itemcenter.category.api.bean.request.BackCategoryQueryRequest;
import com.mallcai.itemcenter.category.api.bean.response.BackCategoryInfo;
import com.mallcai.itemcenter.category.api.facade.BackCategoryReadFacade;
import com.mallcai.itemcenter.common.JsonSupport;
import com.mallcai.itemcenter.exception.ServiceException;
import com.mallcai.itemcenter.item.enums.ItemStatus;
import com.mallcai.itemcenter.item.model.FullItemOperateBO;
import com.mallcai.itemcenter.item.model.Item;
import com.mallcai.itemcenter.item.model.ItemDetail;
import com.mallcai.itemcenter.sku.model.Sku;
import com.mallcai.itemcenter.item.repository.ItemDAO;
import com.mallcai.itemcenter.sku.model.GroupedSkuAttribute;
import com.mallcai.itemcenter.sku.model.SkuAttribute;
import com.mallcai.itemcenter.sku.repository.SkuDAO;
import com.mallcai.itemcenter.utils.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.mallcai.itemcenter.dto.ICResponse.asserts;

/**
 * 商品创建更新等处理帮助方法，参数补全、校验等，此处不作持久化操作
 * <p>
 * TODO 规格属性、销售属性的校验
 * TODO 将校验逻辑放入服务端
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/21 15:46<br/>
 */
@Slf4j
@Component
public class ItemPersistenceHelper implements JsonSupport {


    private final SkuDAO skuDAO;
    private final ItemDAO itemDAO;
    private final BackCategoryReadFacade backCategoryReadFacade;

    public ItemPersistenceHelper(SkuDAO skuDAO,
                                 ItemDAO itemDAO,
                                 BackCategoryReadFacade backCategoryReadFacade) {
        this.skuDAO = skuDAO;
        this.itemDAO = itemDAO;
        this.backCategoryReadFacade = backCategoryReadFacade;
    }

    /**
     * 商品创建处理
     *
     * @param item       商品
     * @param skuList    sku集合
     * @param itemDetail 商品详情
     * @param sellerId   店铺id
     * @return 带有详情信息的聚合对象
     */
    public FullItemOperateBO createHandler(Item item, List<Sku> skuList, ItemDetail itemDetail, Long sellerId) {

        // 扩展item的基础信息至sku中
        diffusion(item, skuList);

        // 先判断启用的有效性
        assertAllDisable(skuList);

        // 前置扩展
        beforeCreateExtension(item, skuList, itemDetail);

        // 处理销售属性
        handleSellAttribute(item, skuList);

        // 处理Item
        item = handleCreateItem(item, sellerId);

        // 处理Sku
        skuList = handleCreateSku(skuList, item);

        // 处理ItemDetail
        handleItemDetail(item.getSpuId(), item.getId(), itemDetail);

        // 商品创建扩展
        onCreateExtension(item, skuList, itemDetail);

        // 特殊处理字段，或者设置默认值
        beforePersistent(item, skuList, itemDetail);
        // 生成hashing值
        item.setHashCode(Digestor.itemDigest(item, itemDetail));

        FullItemOperateBO bo = FullItemOperateBO.fromCreate(item, skuList, itemDetail);
        return bo;
    }

    /**
     * 商品更新处理
     *
     * @param item       商品
     * @param skuList    sku集合
     * @param itemDetail
     * @param sellerId   店铺id
     * @return 处理后商品信息
     */
    public FullItemOperateBO updateHandler(Item item, List<Sku> skuList, ItemDetail itemDetail, Long sellerId, Long updatedBy) {
        // 前置扩展
        beforeUpdateExtension(item, skuList, itemDetail);

        // 处理销售属性
        handleSellAttribute(item, skuList);

        item = handleUpdateItem(itemDAO.findById(item.getId()), item, sellerId, updatedBy);

        FullItemOperateBO bo = handleUpdateSku(skuDAO.findByItemId(item.getId()), skuList, item, sellerId, updatedBy);

        assertAllDisable(skuList);

        handleItemDetail(null, item.getId(), itemDetail);
        bo.setItem(item);
        bo.setItemDetail(itemDetail);

        // todo: 1. feature 位标处理

        // 商品更新后置扩展
        onUpdateExtension(item, skuList, itemDetail);

        // 生成md5值
        item.setHashCode(Digestor.itemDigest(item, itemDetail));

        return bo;
    }

    private void beforeCreateExtension(Item item, List<Sku> skuList, ItemDetail itemDetail) {
    }

    private void onCreateExtension(Item item, List<Sku> skuList, ItemDetail itemDetail) {
    }

    /**
     * 扩展基本信息
     *
     * @param item    item对象
     * @param skuList sku集合
     */
    private void diffusion(Item item, List<Sku> skuList) {
        for (Sku sku : skuList) {
            sku.setType(item.getType());
            sku.setUpdatedBy(item.getUpdatedBy());
        }
    }

    /**
     * 防止sku都无效
     *
     * @param skuList sku集合
     */
    private void assertAllDisable(List<Sku> skuList) {
        for (Sku sku : skuList) {
            if (Objects.equals(sku.getStatus(), ItemStatus.ON_SHELF.getValue())) {
                return;
            }
        }

        throw new ServiceException("forbid.all.sku.disable");
    }

    /**
     * 处理销售属性
     *
     * @param item    商品
     * @param skuList sku集合
     */
    private void handleSellAttribute(Item item, List<Sku> skuList) {

        // 索引形式为： 属性key -> 属性val -> skuAttribute
        Map<String, Map<String, SkuAttribute>> map = Maps.newHashMap();

        for (Sku sku : skuList) {
            List<SkuAttribute> attributes = sku.getAttributes();

            // 判断sku是否存在销售属性
            if (CollectionUtils.isEmpty(attributes)) {
                log.warn("no sku attribute for sku: {}", sku);
                continue;
            }

            for (SkuAttribute attr : attributes) {
                // 属性key不存在，建立次级 val -> skuAttribute (Map结构)
                map.putIfAbsent(attr.getAttrKey(), Maps.newLinkedHashMap());

                // 次级map，value不存在，放入skuAttribute
                map.get(attr.getAttrKey()).putIfAbsent(attr.getAttrVal(), attr);
            }
        }

        // 建立 key -> List<skuAttribute> 的Map结构
        Map<String, List<SkuAttribute>> skuAttributeMap = map.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        it -> new ArrayList<>(it.getValue().values())));

        // 转换为组化的skuAttribute
        List<GroupedSkuAttribute> list = skuAttributeMap.entrySet().stream()
                .map(it -> new GroupedSkuAttribute(it.getKey(), it.getValue()))
                .collect(Collectors.toList());
        item.setSkuAttributes(list);
    }

    /**
     * 处理item中的默认值
     *
     * @param item     源对象
     * @param sellerId 店铺id
     * @return
     */
    private Item handleCreateItem(Item item, Long sellerId) {

        // 叶子类目有效性检查
        dealBackCategory(item);

        // 初始化状态设置
        item.setStatus(ItemStatus.OFF_SHELF.getValue());
        /*Integer status = item.getStatus();
        ItemStatus statusEnum = ItemStatus.fromValue(status);
        if (statusEnum == null) {
            //默认上架
            item.setStatus(ItemStatus.ON_SHELF.getValue());
        } else {
            item.setStatus(status);
        }*/


        // 设置店铺
        item.setSellerId(sellerId);
        if(item.getItemCode()==null) {
            item.setItemCode(StringUtils.EMPTY);
        }

        // todo: 填入item的主键id
        // generateId(item);

        // todo: 持久化的前置处理，用于支持特定字段的特殊处理逻辑
        // deal(item);

        return item;
    }


    /**
     * 后台类目校验及处理
     * <p>
     * 类目树读出字段类型为ArrayList<Integer>，需要特别关注
     *
     * @param item 待创建商品
     */
    private void dealBackCategory(Item item) {

        Long categoryId = item.getCategoryId();

        // PART 1: 判断类目是否为有效的后台叶子类目
        BackCategoryQueryRequest getByIdR = BackCategoryQueryRequest.builder().id(categoryId).build();
        BackCategoryInfo backCategoryDto = asserts(backCategoryReadFacade.findById(getByIdR));
        if (backCategoryDto == null || backCategoryDto.getHasChildren()) {
            throw new ServiceException("invalid.leaf.category");
        }

        // PART 2：将类目信息写入 item -> extra -> categoryList中
        BackCategoryQueryRequest getAncestorsByIdR = BackCategoryQueryRequest.builder().id(categoryId).build();
        List<BackCategoryInfo> categoryList = asserts(backCategoryReadFacade.findAncestorsOf(getAncestorsByIdR));
        List<Long> categoryIdList = categoryList.stream().map(BackCategoryInfo::getId).collect(Collectors.toList());

        if (log.isDebugEnabled()) {
            log.debug("Category Id List: {} for itemId: {}(PRE)", categoryIdList, item.getId());
        }

        Map<String, String> itemExtra = CollectionUtils.isEmpty(item.getExtra())
                ? Maps.newLinkedHashMap()
                : item.getExtra();

        itemExtra.put("categoryList", JsonMapper.nonEmptyMapper().toJson(categoryIdList));
        item.setExtra(itemExtra);
    }

    /**
     * 处理sku中的默认值
     *
     * @param skuList sku集合
     * @param item    item对象
     * @return 处理后的sku集合
     */
    private List<Sku> handleCreateSku(List<Sku> skuList, Item item) {
        List<Sku> skuList1 = Lists.newArrayList();
        for (Sku sku : skuList) {
            Integer status = sku.getStatus();
            ItemStatus statusEnum = ItemStatus.fromValue(status);
            if (statusEnum == null || Objects.equals(status, ItemStatus.ON_SHELF.getValue())) {
                // 默认启用
                sku.setStatus(ItemStatus.ON_SHELF.getValue());
            } else {
                //只保留启用的sku
                continue;
            }

            // 处理sku的图片信息
            dealSkuImage(sku, item);

            // PART 1: 准备数据并生成主键id
            sku.setType(item.getType());
            sku.setSellerId(item.getSellerId());
            sku.setItemId(item.getId());

            if (sku.getName() == null) {
                sku.setName(item.getName());
            }
            if (sku.getSkuCode() == null) {
                sku.setSkuCode(StringUtils.EMPTY);
            }

            // todo: sku id
            // generateId(sku);

            // todo: 持久化的前置处理，用于支持特定字段的特殊处理逻辑
            // deal(item, sku);

            skuList1.add(sku);
        }

        dealPrice(item, skuList1);
        return skuList1;
    }

    /**
     * Sku上图片的处理
     * <p>
     * 若sku中的某销售属性上设置了图片，且可展示，则将其拷贝至Sku的图片上<br>
     * <b>***否则使用item上的主图***</b>
     *
     * @param sku  sku对象
     * @param item 商品对象
     */
    private void dealSkuImage(Sku sku, Item item) {
        if (!CollectionUtils.isEmpty(sku.getAttributes())) {
            for (SkuAttribute skuAttribute : sku.getAttributes()) {
                if (skuAttribute.getShowImage() != null && skuAttribute.getShowImage()) {
                    sku.setImage(skuAttribute.getImage());
                    return;
                }
            }
        }

        sku.setImage(item.getMainImage());
    }

    /**
     * 高低价计算逻辑
     * <p>
     * 计算过程中，如果出现sku中未设置价格的，整个逻辑终止。
     * </p>
     *
     * @param item    商品
     * @param skuList sku集合
     * @return 商品对象
     */
    public Item dealPrice(Item item, List<Sku> skuList) {
        if (CollectionUtils.isEmpty(skuList) || item == null) {
            return item;
        }

        Long lowPrice = Long.MAX_VALUE;
        Long highPrice = Long.MIN_VALUE;

        for (Sku sku : skuList) {

            // 禁用的商品不参与价格计算
            if (sku.getStatus() != ItemStatus.ON_SHELF.getValue()) {
                continue;
            }

            if (sku.getPrice() == null) {
                log.warn("检测到sku未设置价格，跳过高低价计算");
                return item;
            }

            lowPrice = lowPrice > sku.getPrice() ? sku.getPrice() : lowPrice;
            highPrice = highPrice < sku.getPrice() ? sku.getPrice() : highPrice;
        }

        item.setHighPrice(highPrice);
        item.setLowPrice(lowPrice);

        return item;
    }

    /**
     * 处理商品详情页
     *
     * @param spuId      spuId
     * @param itemId     itemId
     * @param itemDetail
     * @return
     */
    private ItemDetail handleItemDetail(Long spuId, Long itemId, ItemDetail itemDetail) {
        itemDetail.setItemId(itemId);

        // if (spuId == null) {
        //     return itemDetail;
        // }

        // todo: handle spu
        // SpuDetail spuDetail = assertResult(spuReadDomainService::findSpuDetailBySpuId, spuId, "spu");

        // if (CollectionUtils.isEmpty(itemDetail.getim())) {
        //     itemDetail.setImages(spuDetail.getImages());
        // }

        // itemDetail.setDetail(spuDetail.getPcDetail());
        // itemDetail.setExtra(spuDetail.getExtra());

        return itemDetail;
    }

    private void beforeUpdateExtension(Item item, List<Sku> skuList, ItemDetail itemDetail) {
        // todo: 更新前置扩展
    }

    private void onUpdateExtension(Item item, List<Sku> skuList, ItemDetail itemDetail) {
        // todo: 后置商品扩展
    }

    private Item handleUpdateItem(Item original, Item item, Long sellerId, Long updatedBy) {
        if (original == null) {
            throw new ServiceException("item.not.exist");
        }

        if (!original.getSellerId().equals(sellerId)) {
            throw new ServiceException("item.not.belong.to.seller");
        }

        if (!original.getVersion().equals(item.getVersion())) {
            throw new ServiceException("version.value.mismatch");
        }

        // 设置更新用户id
        item.setUpdatedBy(updatedBy);

        // 初始化状态设置
        item.setStatus(original.getStatus());

        // 设置卖家
        item.setSellerId(sellerId);

        // 合并额外字段
        if (!CollectionUtils.isEmpty(original.getExtra()) && !CollectionUtils.isEmpty(item.getExtra())) {
            original.getExtra().putAll(item.getExtra());
            item.setExtra(original.getExtra());
        }

        // todo: 补全更新信息，处理特殊字段

        return item;
    }

    private FullItemOperateBO handleUpdateSku(List<Sku> originalList, List<Sku> skuList, Item item, Long sellerId, Long updatedBy) {
        // 当item通过检查后，sku不执行部分检查，直接使用公共信息

        Map<Long, Sku> toDeleteSkuMap = originalList.stream().collect(Collectors.toMap(Sku::getId, Function.identity()));

        List<Sku> toCreateSkuList = Lists.newLinkedList();
        List<Sku> toDeleteSkuList = Lists.newLinkedList();
        List<Sku> toUpdateSkuList = Lists.newLinkedList();

        for (Sku sku : skuList) {
            if (sku.getName() == null) {
                sku.setName(item.getName());
            }

            // 处理sku的图片信息
            dealSkuImage(sku, item);

            Integer status = sku.getStatus();
            ItemStatus statusEnum = ItemStatus.fromValue(status);
            if (statusEnum == null || Objects.equals(status, ItemStatus.ON_SHELF.getValue())) {
                // 默认启用
                sku.setStatus(ItemStatus.ON_SHELF.getValue());
            } else {
                sku.setStatus(status);
            }

            // 不信任传入的关键数据，使用新数据覆盖
            sku.setItemId(item.getId());
            sku.setType(item.getType());
            sku.setUpdatedBy(updatedBy);
            sku.setSellerId(sellerId);

            if (sku.getId() == null) {
                toCreateSkuList.add(sku);
                // todo: 创建 ID
                // generateHelper.generateId(sku);
            } else {
                Sku original = toDeleteSkuMap.remove(sku.getId());
                sku.setVersion(original.getVersion());
                toUpdateSkuList.add(sku);
            }

            // todo: 补全更新信息，处理特殊字段
            beforePersistent(item, sku);
        }

        FullItemOperateBO bo = new FullItemOperateBO();
        bo.setToCreateSkuList(toCreateSkuList);

        if (!toDeleteSkuMap.isEmpty()) {
            toDeleteSkuList = new ArrayList<>(toDeleteSkuMap.values());
            toDeleteSkuList.forEach(it -> it.setUpdatedBy(updatedBy));
            bo.setToDeleteSkuList(toDeleteSkuList);
        } else {
            bo.setToDeleteSkuList(Collections.emptyList());
        }

        bo.setToUpdateSkuList(toUpdateSkuList);
        dealPrice(item, skuList);
        return bo;
    }

    private void beforePersistent(Item item, List<Sku> skuList, ItemDetail itemDetail) {
        if (item.getSpuId() == null) {
            item.setSpuId(0L);
        }
        if (item.getServiceFeeTempId() == null) {
            item.setServiceFeeTempId(0L);
        }
        if (item.getVideoUrl() == null) {
            item.setVideoUrl("");
        }
        skuList.forEach(it -> {
            if (it.getSkuTemplateId() == null) {
                it.setSkuTemplateId(0L);
            }
            if (it.getOuterId() == null) {
                it.setOuterId("");
            }
        });
        if (itemDetail.getExtra() == null) {
            itemDetail.setExtra(Maps.newHashMap());
        }
    }

    private void beforePersistent(Item item, Sku sku) {
        if (sku.getId() == null) {
            if (sku.getExtra() == null) {
                sku.setExtra(Collections.emptyMap());
            }
            if (sku.getSkuTemplateId() == null) {
                sku.setSkuTemplateId(0L);
            }
            if (sku.getOuterId() == null) {
                sku.setOuterId("");
            }
        }
    }
}
