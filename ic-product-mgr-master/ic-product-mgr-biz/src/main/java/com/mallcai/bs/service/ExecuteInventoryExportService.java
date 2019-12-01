package com.mallcai.bs.service;

import com.dailuobo.api.domain.vo.DDLWarehouse;
import com.dailuobo.api.domain.vo.InventoryExportVo;
import com.mallcai.backend.common.api.Response;
import com.mallcai.bs.enums.InventoryExportEnum;
import com.mallcai.bs.mapper.CityWarehouseMapper;
import com.mallcai.bs.mapper.InventoryExportMapper;
import com.mallcai.bs.mapper.MarketingInventoryMapper2;
import com.mallcai.bs.model.ExportInventoryLine;
import com.mallcai.bs.model.ExportParameter;
import com.mallcai.bs.utils.ExcelUtil;
import com.mallcai.service.openapi.ICategoryService;
import com.mallcai.service.vo.ic.common.SOAProductClassify;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @ClassName: ExecuteInventoryExportService
 * @Description: 具体执行库存excel文件导出功能的服务
 * @Author: zhangxuefeng
 * @Date: 2019/6/24 下午3:05
 * @Version: 1.0
 **/
@Service
@Slf4j
public class ExecuteInventoryExportService {

    private static final int pageSize = 1000;

    private static final List<String> titles = Arrays.asList("库存Id", "城市名", "cityProductId", "SKU",
            "商品名称", "投放名称", "库存量", "可用量", "预警量", "在单量", "是否共享库存", "仓库名", "一级分类名", "二级分类名", "门店Id");


    @Autowired
    private InventoryExportMapper inventoryExportMapper;

    @Autowired
    private MarketingInventoryMapper2 marketingInventoryMapper2;

    @Autowired
    private CityWarehouseMapper cityWarehouseMapper;

    @Autowired
    private ICategoryService iCategoryService;
    @Autowired
    private OssService ossService;


    private static BlockingQueue<ExportParameter> exportWaitingList = new ArrayBlockingQueue<>(100);

    public static void addToExportList(ExportParameter parameter) {
        exportWaitingList.add(parameter);
    }


    @PostConstruct
    public void run() {

        Executor executor = Executors.newFixedThreadPool(1);
        executor.execute(() -> {
            while (true) {
                try{
                    ExportParameter parameter =  exportWaitingList.take();
                    doExport(parameter);
                }catch (Exception|Error e) {
                    log.error("处理库存导出异常: {}",  e.getMessage());
                }

            }
        });
    }

    public void doExport(ExportParameter parameter) {
        try{
            List<Integer> cityIds = parameter.getCityIds();
            List<Integer> classifyIds = parameter.getClassifyIds();
            Integer total = marketingInventoryMapper2.countInventoryByCityIdAndClassifyId(cityIds, classifyIds);
            if(total > 0) {

                Map<Integer, String> warehouseMap = new HashMap<>();
                Map<Integer, String> classifyMap = new HashMap<>();
                Map<Integer, Integer> fatherClassifyMap = new HashMap<>();
                try{
                    List<DDLWarehouse> warehouseList = cityWarehouseMapper.getWareHouse(cityIds,null,null,null);
                    warehouseMap = warehouseList.stream()
                            .collect(HashMap::new, (m, e) -> m.put(e.getWarehouseId(), e.getWarehouseName()), HashMap::putAll);

                    Response<List<SOAProductClassify>> classifyRet = iCategoryService.getByLevel(Arrays.asList(1, 2));
                    List<SOAProductClassify> classifies = classifyRet.getData();
                    if(classifyRet.isSuccess() && classifies != null && classifies.size() > 0) {
                        classifyMap = classifies.stream().collect(HashMap::new,
                                (m, e) -> m.put(e.getClassifyId(), e.getClassifyName()), HashMap::putAll);
                        fatherClassifyMap = classifies.stream().filter(e -> {
                            return e.getFatherId() > 0;
                        }).collect(HashMap::new, (m, e) -> m.put(e.getClassifyId(), e.getFatherId()), HashMap::putAll);
                    }
                }catch (Exception e) {
                    log.error("获取仓库/类目信息失败，异常是:{}", e.getMessage());
                }


                HSSFWorkbook wb = ExcelUtil.createHSSFWorkbookWithTitleRow(titles, "库存");
                if(wb != null) {
                    System.out.println("运行到此处");
                    for(int start = 0; start < total; start += pageSize) {
                        List<ExportInventoryLine> lines = marketingInventoryMapper2.selectInventoryByCityIdAndClassifyId(cityIds, classifyIds, start, pageSize);
                        fillXSSFWorkbookWithInventory(lines, wb.getSheet("库存"), start+1, warehouseMap, classifyMap, fatherClassifyMap);
                    }

                    String url = uploadToOss(wb, parameter.getFilename());
                    if(!StringUtils.isEmpty(url)) {
                        updateExportStatus(InventoryExportEnum.SUCCESS.getStatus(), url, parameter.getExportId());
                        return;
                    }else{
                        log.error("url无效，上传oss失败");
                    }
                }else {
                    log.error("创建excel文件失败，导出库存文件失败");
                }

                updateExportStatus(InventoryExportEnum.FAILED.getStatus(), null, parameter.getExportId());
                return;
            }else{
                updateExportStatus(InventoryExportEnum.NO_DATA.getStatus(), null, parameter.getExportId());
                return;
            }
        }catch (Exception|Error e) {
            System.out.println("异常-------" + e.getMessage());
            log.error("执行excel导出异常，异常信息: {}", e.getMessage());
        }
    }

    public void updateExportStatus(byte status, String url, final Integer exportId) {
        InventoryExportVo vo = new InventoryExportVo();
        vo.setStatus(status);
        vo.setDownloadUrl(url);
        vo.setUpdateTime(new Date());
        inventoryExportMapper.updateById(vo, exportId);
        return;
    }


    public String uploadToOss(HSSFWorkbook wb, String filename) {
        try{
            ByteArrayOutputStream output = new ByteArrayOutputStream();
            wb.write(output);
            ByteArrayInputStream inputStream = new ByteArrayInputStream(output.toByteArray());
            return ossService.upload(filename, inputStream);
        }catch (Exception e) {
            log.error("上传oss异常: {}", e.getMessage());
        }
        return null;
    }

    public static void writeToLocalFile(HSSFWorkbook wb, String filename) {
        try{
            OutputStream out = new FileOutputStream("/tmp/" + filename);
            wb.write(out);
            out.flush();
            out.close();
        }catch (Exception e) {
            log.error("生成文件异常, {}", e.getMessage());
        }

    }

    public static void fillXSSFWorkbookWithInventory(List<ExportInventoryLine> lines, HSSFSheet sheet, Integer rowNum,
                                                     Map<Integer, String> warehouseMap, Map<Integer, String> classifyMap, Map<Integer, Integer> fatherClassifyMap) {
        try{
            for(ExportInventoryLine line : lines) {
                HSSFRow row = sheet.createRow(rowNum++);
                HSSFCell cell = row.createCell(0);
                cell.setCellValue(line.getId());

                cell = row.createCell(1);
                cell.setCellValue(line.getCityName());

                cell = row.createCell(2);
                cell.setCellValue(line.getCityProductId());

                cell = row.createCell(3);
                cell.setCellValue(line.getProductNo());

                cell = row.createCell(4);
                cell.setCellValue(line.getHqProductName());

                cell = row.createCell(5);
                cell.setCellValue(line.getShowName());

                cell = row.createCell(6);
                cell.setCellValue(line.getAvailableBase());

                cell = row.createCell(7);
                cell.setCellValue(line.getAvailable());

                cell = row.createCell(8);
                cell.setCellValue(line.getThreshold());

                cell = row.createCell(9);
                cell.setCellValue(line.getAvailableBase() - line.getAvailable());

                cell = row.createCell(10);
                if(line.getIsShare() == 1) {
                    cell.setCellValue("是");
                }else if(line.getIsShare() == 0) {
                    cell.setCellValue("否");
                }else{
                    cell.setCellValue("未知标记:" + line.getIsShare().toString());
                }

                cell = row.createCell(11);
                String warehouseName = warehouseMap.get(line.getWarehouseId());
                if(StringUtils.isEmpty(warehouseName)) {
                    warehouseName = "默认仓库:" + line.getWarehouseId();
                }
                cell.setCellValue(warehouseName);

                cell = row.createCell(13);
                String classifyName = classifyMap.get(line.getClassifyId());
                if(StringUtils.isEmpty(classifyName)) {
                    classifyName = "未知分类:" + line.getClassifyId().toString();
                    cell.setCellValue(classifyName);
                    cell = row.createCell(12);
                    cell.setCellValue("未知分类");
                }else{
                    cell.setCellValue(classifyName);
                    cell = row.createCell(12);
                    Integer fatherId = fatherClassifyMap.get(line.getClassifyId());
                    if(fatherId != null && !StringUtils.isEmpty(classifyMap.get(fatherId))) {
                        cell.setCellValue(classifyMap.get(fatherId));
                    }else{
                        cell.setCellValue("未知分类");
                    }
                }

                cell = row.createCell(14);
                cell.setCellValue(line.getStoreId());
            }
        }catch (Exception|Error e) {
            System.out.println(e.getMessage());
        }

    }

}
