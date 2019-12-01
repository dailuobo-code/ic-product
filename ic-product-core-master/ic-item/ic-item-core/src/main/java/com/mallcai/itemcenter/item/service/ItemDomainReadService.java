package com.mallcai.itemcenter.item.service;

import com.alibaba.druid.util.MySqlUtils;
import com.google.common.base.Throwables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.html.HtmlEscapers;
import com.google.common.net.UrlEscapers;
import com.mallcai.itemcenter.category.api.bean.response.BackCategoryInfo;
import com.mallcai.itemcenter.dto.Paging;
import com.mallcai.itemcenter.exception.ServiceException;
import com.mallcai.itemcenter.item.api.bean.request.item.ItemPagingRequest;
import com.mallcai.itemcenter.item.api.facade.internal.BackItemCategoryReadFacade;
import com.mallcai.itemcenter.item.model.FullItemBO;
import com.mallcai.itemcenter.item.model.Item;
import com.mallcai.itemcenter.item.model.ItemDetail;
import com.mallcai.itemcenter.sku.model.Sku;
import com.mallcai.itemcenter.item.repository.ItemDAO;
import com.mallcai.itemcenter.sku.service.SkuDomainReadService;
import com.mallcai.itemcenter.utils.MysqlEscapers;
import com.mallcai.itemcenter.utils.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.util.HtmlUtils;

import javax.annotation.Resource;
import java.net.URLEncoder;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * ItemDomainService
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/21 11:46<br/>
 */
@Slf4j
@Service
public class ItemDomainReadService {
    private final ItemDAO itemDAO;
    private final SkuDomainReadService skuDomainReadService;
    private final ItemDetailDomainReadService itemDetailDomainReadService;

    @Resource
    private BackItemCategoryReadFacade backItemCategoryReadFacade;

    public ItemDomainReadService(ItemDAO itemDAO,
                                 SkuDomainReadService skuDomainReadService,
                                 ItemDetailDomainReadService itemDetailDomainReadService) {
        this.itemDAO = itemDAO;
        this.skuDomainReadService = skuDomainReadService;
        this.itemDetailDomainReadService = itemDetailDomainReadService;
    }

    public Item findById(Long id) {
        try {
            return itemDAO.findById(id);
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            log.error("fail to queryByCode item by id: {}, cause: {}", id, Throwables.getStackTraceAsString(e));
            throw new ServiceException("item.find.fail");
        }
    }

    public FullItemBO findFullItemById(Long itemId) {
        return findFullItemById(itemId, true);
    }

    public FullItemBO findFullItemById(Long itemId, boolean cached) {
        try {
            Item item = findItemById(itemId, cached);
            List<Sku> skuList = skuDomainReadService.findByItemId(itemId, cached);
            ItemDetail itemDetail = itemDetailDomainReadService.findByItemId(itemId, cached);

            return new FullItemBO(item, skuList, itemDetail);
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            log.error("fail to query fullItem by itemId: {} cause: {}", itemId, Throwables.getStackTraceAsString(e));
            throw new ServiceException("item.find.fail");
        }
    }

    public FullItemBO findFullItemByIdWithDetail(Long itemId) {
        return findFullItemByIdWithDetail(itemId, true);
    }

    public FullItemBO findFullItemByIdWithDetail(Long itemId, boolean cached) {
        try {
            Item item = findItemById(itemId, cached);
            List<Sku> skuList = skuDomainReadService.findByItemId(itemId, cached);
            ItemDetail itemDetail = itemDetailDomainReadService.findByItemId(itemId, cached);

            return new FullItemBO(item, skuList, itemDetail);
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            log.error("fail to query full item with detail by itemId: {},cause: {}", itemId, Throwables.getStackTraceAsString(e));
            throw new ServiceException("item.find.fail");
        }
    }

    public Paging<Item> paging(ItemPagingRequest request) {
        try {
            Map<String, Object> criteria = Maps.newHashMap();

            Long categoryId = request.getCategoryId();
            if (categoryId != null) {
                List<BackCategoryInfo> childIds = backItemCategoryReadFacade.findChildrenByPid(categoryId);
                if (CollectionUtils.isNotEmpty(childIds)) {
                    List<Long> categoryIds = Lists.newArrayList();
                    childIds.forEach(c -> {
                        categoryIds.add(c.getId());
                    });
                    criteria.put("categoryIds", categoryIds);
                } else {
                    criteria.put("categoryId", categoryId);
                }
            }

            PageInfo pageInfo = PageInfo.fromLastId(request.getOffset(), request.getLimit());
            String itemName = request.getItemName();
            if (StringUtils.isNotBlank(itemName)) {
                itemName = MysqlEscapers.mysqlEscaper().escape(itemName);
            }
            criteria.put("nameLike", itemName);
            criteria.put("sellerId", request.getSellerId());
            if (request.getStatus() != null) {
                criteria.put("status", request.getStatus().getValue());
            }
            if (request.getType() != null) {
                criteria.put("type", request.getType());
            }

            pageInfo.into(criteria);
            return itemDAO.paging(criteria);
        } catch (ServiceException e) {
            throw e;
        } catch (Exception e) {
            log.error("fail to paging by {}, cause:{}", request, Throwables.getStackTraceAsString(e));
            throw new ServiceException("item.paging.fail");
        }
    }

    public List<Item> listBy(Map<String, Object> criteria) {
        try {
            return itemDAO.list(criteria);
        } catch (Exception e) {
            log.error("fail to listBy by {}, cause:{}", criteria, Throwables.getStackTraceAsString(e));
            return Collections.emptyList();
        }
    }

    private Item findItemById(Long itemId, boolean cached) {
        Item item;
        if (cached) {
            item = itemDAO.findByIdCached(itemId);
        } else {
            item = itemDAO.findById(itemId);
        }
        return item;
    }

}
