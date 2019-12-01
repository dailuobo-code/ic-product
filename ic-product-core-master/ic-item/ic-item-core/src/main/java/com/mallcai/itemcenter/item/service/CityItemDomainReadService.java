package com.mallcai.itemcenter.item.service;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mallcai.itemcenter.category.api.bean.response.BackCategoryInfo;
import com.mallcai.itemcenter.dto.Paging;
import com.mallcai.itemcenter.item.api.bean.request.item.CityItemListRelationRequest;
import com.mallcai.itemcenter.item.api.bean.request.item.CityItemPagingRequest;
import com.mallcai.itemcenter.item.api.facade.internal.BackItemCategoryReadFacade;
import com.mallcai.itemcenter.item.model.CityItem;
import com.mallcai.itemcenter.item.model.FullCityItemBO;
import com.mallcai.itemcenter.item.repository.CityItemDAO;
import com.mallcai.itemcenter.sku.model.CitySku;
import com.mallcai.itemcenter.sku.repository.CitySkuDAO;
import com.mallcai.itemcenter.utils.MysqlEscapers;
import com.mallcai.itemcenter.utils.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * CityItemDomainReadService
 *
 * @author <a href="mailto:zhangchengdong@mallcai.com">张成栋</a>
 * @date 2019/10/24 16:02<br/>
 */
@Slf4j
@Component
public class CityItemDomainReadService {
    private final CitySkuDAO citySkuDAO;
    private final CityItemDAO cityItemDAO;

    @Resource
    private BackItemCategoryReadFacade backItemCategoryReadFacade;

    public CityItemDomainReadService(CitySkuDAO citySkuDAO, CityItemDAO cityItemDAO) {
        this.citySkuDAO = citySkuDAO;
        this.cityItemDAO = cityItemDAO;
    }

    public FullCityItemBO findFullItemById(Long itemId, Long cityId) {
        CityItem cityItem = cityItemDAO.findByItemIdAndCityId(itemId, cityId);
        List<CitySku> citySkus = citySkuDAO.findByItemIdAndCityId(itemId, cityId);
        return new FullCityItemBO(cityItem, citySkus);
    }

    public List<CityItem> listBy(CityItemListRelationRequest request) {
        return cityItemDAO.list(ImmutableMap.of("itemIds", request.getItemIds()));
    }

    public Paging<CityItem> paging(CityItemPagingRequest request) {
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
        String itemName = request.getItemName();
        if (StringUtils.isNotBlank(itemName)) {
            itemName = MysqlEscapers.mysqlEscaper().escape(itemName);
        }
        criteria.put("nameLike", itemName);
        criteria.put("cityId", request.getCityId());
        criteria.put("sellerId", request.getSellerId());
        if (request.getStatus() != null) {
            criteria.put("status", request.getStatus().getValue());
        }
        if (request.getType() != null) {
            criteria.put("type", request.getType());
        }
        PageInfo.fromLastId(request.getOffset(), request.getLimit()).into(criteria);;

        return cityItemDAO.paging(criteria);
    }
}
