package com.mallcai.itemcenter.item.api.facade.peikun;

import com.mallcai.itemcenter.BaseTest;
import com.mallcai.itemcenter.category.api.facade.InitCategoryAndPropertyData;
import com.mallcai.itemcenter.dto.ICResponse;
import org.junit.Test;

import javax.annotation.Resource;

public class InitCategoryAndPropertyDataTest extends BaseTest {

    @Resource
    private InitCategoryAndPropertyData initCategoryAndPropertyData;

    @Test
    public void testInitCategoryData() {
        //String json = "[{\"pname\":\"家居家纺\",\"cnames\":[\"家居内衣\",\"床上用品\"]},{\"pname\":\"数码电器\",\"cnames\":[\"家用电器\",\"手机数码\"]},{\"pname\":\"美妆个护\",\"cnames\":[\"口腔护理\",\"母婴用品\",\"沐浴洗发\",\"美体护肤\",\"衣物洗护\"]}]";
        String json = "[{\"pname\":\"调味干货\",\"cnames\":[\"酱油醋\",\"调味酱汁\"]},{\"pname\":\"乳品酒饮\",\"cnames\":[\"奶制品\",\"饮用水\"]},{\"pname\":\"美妆个护\",\"cnames\":[\"口腔护理\",\"母婴用品\",\"沐浴洗发\",\"美体护肤\",\"衣物洗护\"]}]";
        ICResponse<Boolean> result = initCategoryAndPropertyData.initCategoryData(json);
    }

    @Test
    public void testInitCategoryPropertyData() {
        String json = "[{\"categoryId\":477,\"attrKey\":\"长\",\"group\":\"DEFAULT\",\"status\":1,\"attrMetasJson\":{\"REQUIRED\":\"true\",\"VALUE_TYPE\":\"STRING\",\"USER_DEFINED\":\"true\"},\"attrValsJson\":[\"10\",\"20\"],\"index\":1,\"extraJson\":\"{}\"},{\"categoryId\":477,\"attrKey\":\"宽\",\"group\":\"DEFAULT\",\"status\":1,\"attrMetasJson\":{\"REQUIRED\":\"true\",\"VALUE_TYPE\":\"STRING\",\"USER_DEFINED\":\"true\"},\"attrValsJson\":[\"10\",\"20\"],\"index\":1,\"extraJson\":\"{}\"},{\"categoryId\":477,\"attrKey\":\"高\",\"group\":\"DEFAULT\",\"status\":1,\"attrMetasJson\":{\"REQUIRED\":\"true\",\"VALUE_TYPE\":\"STRING\",\"USER_DEFINED\":\"true\"},\"attrValsJson\":[\"10\",\"20\"],\"index\":2,\"extraJson\":\"{}\"},{\"categoryId\":477,\"attrKey\":\"计量单位\",\"group\":\"DEFAULT\",\"status\":1,\"attrMetasJson\":{\"IMPORTANT\":\"true\",\"REQUIRED\":\"true\",\"VALUE_TYPE\":\"STRING\",\"USER_DEFINED\":\"true\"},\"attrValsJson\":[\"克\",\"份\",\"提\",\"箱\",\"盒\"],\"index\":3,\"extraJson\":\"{}\"},{\"categoryId\":477,\"attrKey\":\"商品产地\",\"group\":\"DEFAULT\",\"status\":1,\"attrMetasJson\":{\"REQUIRED\":\"true\",\"VALUE_TYPE\":\"STRING\",\"USER_DEFINED\":\"true\"},\"attrValsJson\":[\"杭州市\"],\"index\":4,\"extraJson\":\"{}\"},{\"categoryId\":477,\"attrKey\":\"颜色\",\"group\":\"DEFAULT\",\"status\":1,\"attrMetasJson\":{\"IMPORTANT\":\"true\",\"REQUIRED\":\"true\",\"VALUE_TYPE\":\"STRING\",\"SKU_CANDIDATE\":\"true\"},\"attrValsJson\":[\"红色\",\"白色\",\"灰色\"],\"index\":5,\"extraJson\":\"{}\"},{\"categoryId\":477,\"attrKey\":\"尺寸\",\"group\":\"DEFAULT\",\"status\":1,\"attrMetasJson\":{\"IMPORTANT\":\"true\",\"REQUIRED\":\"true\",\"VALUE_TYPE\":\"STRING\",\"SKU_CANDIDATE\":\"true\"},\"attrValsJson\":[\"S\",\"M\",\"L\",\"XL\",\"XXL\",\"XXXL\"],\"index\":6,\"extraJson\":\"{}\"}]";
        ICResponse<Boolean> result = initCategoryAndPropertyData.initCategoryPropertyData(json);
    }

}
