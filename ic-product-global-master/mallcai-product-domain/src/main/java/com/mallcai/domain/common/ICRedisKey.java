package com.mallcai.domain.common;

public class ICRedisKey {

    /**
     * 总部前台类目树的缓存
     * [
     * {
     * "cid":1,//一级类目Id
     * "cname":"xxx",
     * "order":1,//展示顺序
     * "child":[
     * {"cid":1,//一级类目Id
     * "cname":"xxx",
     * "order":1,//展示顺序
     * },{...},{...}]
     * }
     * ]
     *
     * @return
     */
    public final static String buildHqFcatTree() {
        return "hq_fcat_tree";
    }

    /**
     * 总部类目关联的商品/后台类目条目的缓存
     * {
     * "relationType":1,//1.后台类目,2.商品Id
     * "items":[
     * {
     * "id":1,//总部商品productno或后台类目id
     * “order":1//顺序
     * }
     * ]//如果relation_type=1,对应后台类目Id，如果=2，为指定商品列表
     * }
     *
     * @param categoryId       前台类目ID
     * @param parentCategoryId 前台类目父ID
     * @return
     */
    public final static String buildHqFcatAssociateItem(Long categoryId, Long parentCategoryId) {
        return "hq_fcat_associate_item:cid:" + categoryId + ":pcid:" + parentCategoryId;
    }
}
