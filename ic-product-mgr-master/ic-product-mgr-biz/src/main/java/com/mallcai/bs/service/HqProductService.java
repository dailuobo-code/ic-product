package com.mallcai.bs.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mallcai.service.openapi.IProductService;
import com.mallcai.service.vo.HqProductTax;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mallcai.bs.dao.HqProductDao;
import com.dailuobo.api.domain.entity.HqProduct;
import com.dailuobo.api.domain.util.ExcelData;
import com.dailuobo.api.domain.util.ExcelUtil;
import com.dailuobo.api.domain.vo.CityProductIdAndMode;
import com.dailuobo.api.domain.vo.TACityProduct;

@Service
public class HqProductService {
    @Autowired
    private HqProductDao hqProductDao;

    //@Autowired
    //private SOAService soaService;

    @Autowired
    private CityProductService cityProductService;

    @Autowired
    private SOAMarketingInventoryService soaMarketingInventoryService;

    @Autowired
    private CityService cityService;

    @Autowired
    private IProductService iProductService;

    // 上下架商品
    public void status(Map<String, Object> param) {
        this.hqProductDao.status(param);
        this.hqProductDao.updateCityProductStatus(param);
        Integer status = (Integer) param.get("status");
        if (status == 0) {
            List<CityProductIdAndMode> list = hqProductDao.getCityProductIds((List<Integer>) param.get("hqProductIds"));
            List<Integer> cityProductIds = new ArrayList<>();
            for (CityProductIdAndMode idAndMode : list) {
                if (idAndMode.getDeliveryMode() == 1) {
                    cityProductIds.add(idAndMode.getCityProductId());
                }
            }
            if (!cityProductIds.isEmpty()) {
                try {
                    //soaService.deleteMarketingInventory(cityProductIds);
                    soaMarketingInventoryService.deleteMarketingInventory(cityProductIds);

                } catch (Exception e) {
                    e.printStackTrace();
                    throw new RuntimeException(e);
                }
            }
        }
    }

    //生成各地市电子秤excel表格原始方法
    @Transactional
    public void  establishExcel(){
        List<ExcelData> list = new ArrayList<>();
        List<TACityProduct> msgList = cityProductService.getMsgList();
        for (TACityProduct taCityProduct : msgList) {
            ExcelData excelData = new ExcelData();
            excelData.setCalFlag(taCityProduct.getUnitType());
            excelData.setKeepType(taCityProduct.getKeepType());
            excelData.setProductCode(taCityProduct.getProductNo());
            excelData.setProductName(taCityProduct.getShowName());
            excelData.setUnitPrice("1");
            excelData.setProjectCode(taCityProduct.getProjectCode());
            excelData.setQualityTime(taCityProduct.getQualityTime());
            list.add(excelData);
        }
        String[] columns = new String[] { "商品编号", "商品名称", "项目代码", "计重标识", "单价", "贮存方式","保质期" };
        ExcelUtil.writeExcel(list, columns, "e:/test.xls");
        /*ExcelUtil.writeExcel(list, columns, "/usr/local/tomcat/webapps/ROOT/add.xls")*/;
    }

    // 增加商品
    public void add(HqProduct hqproduct) {
        this.hqProductDao.add(hqproduct);
        /* establishExcel();*/
        // 2019-06-20 15:15:39 新增 还没有city_product无需更新
        // cityService.establishExcelNew();//更新商品信息更新电子秤表
    }

    public void duplicate(HqProduct hqproduct) {
        this.hqProductDao.duplicate(hqproduct);
    }

    // 删除
    public void delete(List<Integer> ids) {
        List cityProductList = this.hqProductDao.getCityProductIds(ids);
        if (cityProductList != null && !cityProductList.isEmpty()) {
            return;
        }
        this.hqProductDao.delete(ids);
    }

    // 编辑商品
    public void update(HqProduct hqproduct) {
        this.hqProductDao.update(hqproduct);
    }

    public void updateProductName(Integer hqProductId, String newName) {
        this.hqProductDao.updateProductName(hqProductId, newName);
    }

    public List<HqProduct> selectAll(Map<String, Object> param) {
        List<HqProduct> list = hqProductDao.selectAll(param);
        List<Integer> productTaxIdList = new ArrayList<>();
        if(list != null && !list.isEmpty()){
            list.forEach(hqProduct -> {
                hqProduct.setProductRate(null);
                if(hqProduct.getProductTaxId() != null){
                    productTaxIdList.add(hqProduct.getProductTaxId());
                }
            });
        }
        if(productTaxIdList != null && !productTaxIdList.isEmpty()){
            Map<Integer,HqProductTax> map = getProductTaxesMapByCodeList(productTaxIdList);
            if(map != null){
                list.forEach(hqProduct -> {
                    if(hqProduct.getProductTaxId() != null){
                        HqProductTax hqProductTax = map.get(hqProduct.getProductTaxId());
                        if(hqProductTax != null){
                            hqProduct.setProductTaxId(hqProductTax.getId());
                            hqProduct.setProductRate(hqProductTax.getTaxRate());
                            hqProduct.setTaxClassifyCode(hqProductTax.getTaxClassifyCode());
                        }
                    }
                });
            }
        }
        return list;
    }

    @Transactional
    public boolean exist(String productNo) {
        return hqProductDao.exist(productNo) == 1;
    }

    @Transactional
    public boolean exist2(Map<String, Object> param) {
        return hqProductDao.exist2(param) == 1;
    }

    public Integer getPointProduct(HqProduct hqProduct) {
        return hqProductDao.getPointProduct(hqProduct);
    }

    public List<HqProduct> findByClassifyId(Integer classifyId) {
        Map<String,Object> params = new HashMap<>();
        params.put("classifyId", classifyId);
        return this.selectAll(params);
    }

    public HqProduct selectHqProductById(Integer hqProductId){
        return hqProductDao.selectHqProductById(hqProductId);
    }

    public Map<Integer,HqProductTax> getProductTaxesMapByCodeList(List<Integer> productTaxIdList){
        List<HqProductTax> list = iProductService.getProductTaxesByIds(productTaxIdList);
        if(list == null || list.isEmpty()){
            return null;
        }
        Map<Integer,HqProductTax> map = new HashMap<>();
        list.forEach(hqProductTax -> map.put(hqProductTax.getId(),hqProductTax));
        return map;
    }

    public List<HqProductTax> getProductTaxesByproductTaxId(Integer productTaxId){
        if(productTaxId == null){
            return null;
        }
        List<Integer> taxCodes = new ArrayList<>();
        taxCodes.add(productTaxId);
        return iProductService.getProductTaxesByIds(taxCodes);
    }

    public List<HqProductTax> getProductTaxesByCode(String taxCode){
        if(StringUtils.isEmpty(taxCode)){
            return null;
        }
        List<String> taxCodes = new ArrayList<>();
        taxCodes.add(taxCode);
        return iProductService.getProductTaxesByCode(taxCodes);
    }


}
