package com.mallcai.biz.category.dao.redisdao;

import com.alibaba.fastjson.JSON;
import com.mallcai.backend.common.redis.DefaultMasterJedisProxy;
import com.mallcai.backend.common.redis.generator.RedisKeyGenerator;
import com.mallcai.biz.category.dao.mapper.CategoryMapper;
import com.mallcai.domain.dataobject.category.CategoryDO;
import com.mallcai.domain.enums.CategoryLevelEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 分类redis操作
 */
@Repository
@Slf4j
public class CategoryRedisDao {

    @Resource
    private CategoryMapper categoryMapper;

    /**
     * 刷新一级分类
     */
    public void refreshFirstCategory() {
        try {
            List<CategoryDO> lv1CategoryList = categoryMapper.listAllLv1Category();
            if (CollectionUtils.isEmpty(lv1CategoryList)) {
                return;
            }
            String lv1CategoryKey = RedisKeyGenerator.generateProdClassifyKey(1);

            DefaultMasterJedisProxy.getInstance().delKey(lv1CategoryKey);
            DefaultMasterJedisProxy.getInstance().set(lv1CategoryKey, JSON.toJSONString(lv1CategoryList));
        } catch (Exception ex) {
            log.error("[刷新一级分类缓存失败]", ex);
        }

    }

    /**
     * 刷新指定二级分类
     */
    public void refreshSecondCategory(Integer parentId) {
        CategoryDO categoryDO = new CategoryDO();
        categoryDO.setFatherId(parentId);
        categoryDO.setLevel(CategoryLevelEnum.LEVEL_TWO.getCode());
        try {
            List<CategoryDO> lv2CategoryList = categoryMapper.listCategory(categoryDO);
            if (CollectionUtils.isEmpty(lv2CategoryList)) return;
            String lv2CategoryKey = RedisKeyGenerator.generateProdClassifyKey(2) + ":classifyId:" + parentId;
            DefaultMasterJedisProxy.getInstance().delKey(lv2CategoryKey);
            DefaultMasterJedisProxy.getInstance().set(lv2CategoryKey, JSON.toJSONString(lv2CategoryList));
        } catch (Exception ex) {
            log.error(String.format("刷新二级缓存失败，fatherId:%s", parentId), ex);
        }

    }


    public void refreshAllSecondCategory() {
        CategoryDO categoryDO = new CategoryDO();
        categoryDO.setLevel(CategoryLevelEnum.LEVEL_TWO.getCode());
        try {
            List<CategoryDO> lv2CategoryList = categoryMapper.listCategory(categoryDO);
            if (CollectionUtils.isEmpty(lv2CategoryList)) return;
            Map<Integer, List<CategoryDO>> categoryLv1Lv2Map = lv2CategoryList.stream()
                    .collect(Collectors.groupingBy(CategoryDO::getFatherId));
            categoryLv1Lv2Map.forEach((lv1ClassifyId, lv2Category) -> {
                String lv2CategoryKey = RedisKeyGenerator.generateProdClassifyKey(2) + ":classifyId:" + lv1ClassifyId;
                DefaultMasterJedisProxy.getInstance().delKey(lv2CategoryKey);
                DefaultMasterJedisProxy.getInstance().set(lv2CategoryKey, JSON.toJSONString(lv2CategoryList));
            });
        } catch (Exception ex) {
            log.error("refresh all level_2 category fail", ex);
            return;
        }
    }
}
