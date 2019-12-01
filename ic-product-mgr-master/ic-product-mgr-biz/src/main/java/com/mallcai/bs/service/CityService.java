package com.mallcai.bs.service;

import com.mallcai.bs.dao.CityDao;
import com.dailuobo.api.domain.entity.City;
import com.dailuobo.api.domain.entity.OpenCity;
import com.dailuobo.api.domain.util.ExcelData;
import com.dailuobo.api.domain.util.ExcelUtil;
import com.dailuobo.api.domain.vo.DDLCity;
import com.dailuobo.api.domain.vo.TACityProduct;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.*;

@Service
@Slf4j
public class CityService {
    @Autowired
    private CityDao cityDao;

    @Autowired
    private CityProductService cityProductService;
    @Autowired
    private OssService ossService;

    @Transactional
    public List<City> selectAll(Map<String, Object> param) {
        return cityDao.selectAll(param);
    }

    public void update(City city) {
        cityDao.update(city);
        establishExcelNewToOneCity(city.getCityId());//更新供应商名称更新电子秤表格
    }

    //生成各地市电子秤excel表格新方法
    @Transactional
    public void  establishExcelNew(){
        //首先从tbl_city获取已经开通的城市
        List<OpenCity> openCities = cityProductService.getOpenCity();
        for (OpenCity openCity : openCities) {
            List<ExcelData> list = new ArrayList<>();
            List<TACityProduct> taCityProducts = cityProductService.getMsgListByCityId(openCity);
            for (TACityProduct taCityProduct : taCityProducts) {
                makeExcelData(list, taCityProduct);
            }
            if(list.isEmpty()){
                continue;
            }
            String[] columns = new String[] { "商品编号", "商品名称", "项目代码", "计重标识", "单价", "贮存方式","保期","保期日期","销售商" };
            HSSFWorkbook wb = ExcelUtil.writeExcelToNew(list, columns);
            try {
                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                wb.write(stream);
                ByteArrayInputStream inputStream = new ByteArrayInputStream(stream.toByteArray());
                ossService.uploadToNew("balance/"+openCity.getCityId()+"_add.xls", inputStream);
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 单个城市电子秤excel表格新方法
     * @param cityId
     */
    @Transactional
    public void  establishExcelNewToOneCity(Integer cityId){
        //首先从tbl_city获取已经开通的城市
        OpenCity openCity = new OpenCity();
        openCity.setCityId(cityId);
        List<ExcelData> list = new ArrayList<>();
        List<TACityProduct> taCityProducts = cityProductService.getMsgListByCityId(openCity);
        if(taCityProducts == null || taCityProducts.isEmpty()){
            return;
        }
        for (TACityProduct taCityProduct : taCityProducts) {
            makeExcelData(list, taCityProduct);
        }

        String[] columns = new String[] { "商品编号", "商品名称", "项目代码", "计重标识", "单价", "贮存方式","保期","保期日期","销售商" };
        HSSFWorkbook wb = ExcelUtil.writeExcelToNew(list, columns);
        try {
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            wb.write(stream);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(stream.toByteArray());
            ossService.uploadToNew("balance/"+openCity.getCityId()+"_add.xls", inputStream);
            inputStream.close();
        } catch (IOException e) {
            log.error("establishExcelNewToOneCity ex",e);
        }
    }

    /**
     * 单个城市电子秤excel表格新方法
     * @param cityId
     */
    @Transactional
    public List<ExcelData> establishExcelNewToOneCityV2(Integer cityId){
        //首先从tbl_city获取已经开通的城市
        OpenCity openCity = new OpenCity();
        openCity.setCityId(cityId);
        List<ExcelData> list = new ArrayList<>();
        List<TACityProduct> taCityProducts = cityProductService.getMsgListByCityId(openCity);
        if(taCityProducts == null || taCityProducts.isEmpty()){
            return Collections.EMPTY_LIST;
        }
        for (TACityProduct taCityProduct : taCityProducts) {
            makeExcelData(list, taCityProduct);
        }
        return list;
    }

    private void makeExcelData(List<ExcelData> list, TACityProduct taCityProduct) {
        ExcelData excelData = new ExcelData();
        excelData.setCalFlag(taCityProduct.getUnitType());
        excelData.setKeepType(taCityProduct.getKeepType());
        excelData.setProductCode(taCityProduct.getProductNo());
        excelData.setProductName(taCityProduct.getShowName());
        excelData.setUnitPrice("1");
        excelData.setProjectCode(taCityProduct.getProjectCode());

        String qualityTime = taCityProduct.getQualityTime().replace("：", ":");
        excelData.setQuality(qualityTime);
        excelData.setQualityTime(qualityTime);
        if (qualityTime.contains(":")) {
            excelData.setQuality(qualityTime.split(":")[0]);
            excelData.setQualityTime(qualityTime.split(":")[1]);
        }
        excelData.setSaleName(taCityProduct.getSaleName());
        list.add(excelData);
    }

    @Transactional
    public List<DDLCity> getDDLCities(Map<String, Object> param) {
        return cityDao.getDDLCities(param);
    }

    public List<DDLCity> getCityNamesByCityIds(Map<String, Object> param) {
        return cityDao.getCityNamesByCityIds(param);
    }
    /**
     * 通过城市IDlist查询城市.
     * @param cityIdList
     * @return
     */
    public List<City> selectCityByIdList(List<Integer> cityIdList){
        return cityDao.selectCityByIdList(cityIdList);
    }

    public City getById(Integer id) {
       return cityDao.getById(id);
    }
}
