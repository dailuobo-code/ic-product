package com.dailuobo.api.domain.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.multipart.MultipartFile;

/**
 * 
 * @author hewangtong
 * 
 */
public class ReadExcel {
    // 总行数
    private int totalRows = 0;
    // 总条数
    private int totalCells = 0;
    // 错误信息接收器
    private String errorMsg;

    // 构造方法
    public ReadExcel() {
    }

    // 获取总行数
    public int getTotalRows() {
        return totalRows;
    }

    // 获取总列数
    public int getTotalCells() {
        return totalCells;
    }

    // 获取错误信息
    public String getErrorInfo() {
        return errorMsg;
    }

    /**
     * 读EXCEL文件，获取信息集合
     * 
     * @param fielName
     * @return
     */
    public List<Map<String, Object>> getExcelInfo(MultipartFile mFile,Integer otherType) {
        String fileName = mFile.getOriginalFilename();// 获取文件名
        try {
            if (!validateExcel(fileName)) {// 验证文件名是否合格
                return null;
            }
            boolean isExcel2003 = true;// 根据文件名判断文件是2003版本还是2007版本
            if (isExcel2007(fileName)) {
                isExcel2003 = false;
            }
            return createExcel(mFile.getInputStream(), isExcel2003,otherType);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据excel里面的内容读取客户信息
     * 
     * @param is      输入流
     * @param isExcel2003   excel是2003还是2007版本
     * @return
     * @throws IOException
     */
    public List<Map<String, Object>> createExcel(InputStream is, boolean isExcel2003,Integer otherType) {
        try {
            Workbook wb = null;
            if (isExcel2003) {// 当excel是2003时,创建excel2003
                wb = new HSSFWorkbook(is);
            } /*else {// 当excel是2007时,创建excel2007
                wb = new XSSFWorkbook(is);
            }*/
            return readExcelValue(wb,otherType);// 读取Excel里面客户的信息
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 读取Excel里面客户的信息
     * 
     * @param wb
     * @return
     */
    private List<Map<String, Object>> readExcelValue(Workbook wb,Integer otherType) {
        // 得到第一个shell
        Sheet sheet = wb.getSheetAt(0);
        // 得到Excel的行数
        this.totalRows = sheet.getPhysicalNumberOfRows();
        // 得到Excel的列数(前提是有行数)
        if (totalRows > 1 && sheet.getRow(0) != null) {
            this.totalCells = sheet.getRow(0).getPhysicalNumberOfCells();
        }
        List<Map<String, Object>> userList = new ArrayList<Map<String, Object>>();
        // 循环Excel行数
        for (int r = 1; r < totalRows; r++) {
            Row row = sheet.getRow(r);
            if (row == null) {
                continue;
            }
            // 循环Excel的列
            Map<String, Object> map = new HashMap<String, Object>();
            String product="";
            Float spec_num=0f;
            String spec_name="";
            Float price=0f;
            /******************循环赋值*******************/
            for (int c = 0; c < this.totalCells; c++) {
            	String value="";
                Cell cell = row.getCell(c);
                if (null != cell) {
                	 if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                         String name = String.valueOf(cell.getNumericCellValue());
                         value = name.substring(0, name.length() - 2 > 0 ? name.length() - 2 : 1);// 名称
                     } else {
                    	 value =cell.getStringCellValue();// 编号
                     }
                    if (c == 0) {
                    	product 	= value;
                    } else if (c == 1) {
                    	if(value.indexOf("--")!=-1) value="1";
                    	spec_num 	= Float.valueOf(value);
                    } else if (c == 2) {
                    	spec_name 		= value;
                    } else if (c == 3) {
                    	price		= Float.valueOf(value);;
                    }
                }
            }
            /*********************************************/
            
            /******************数据处理********************/
            if(spec_name.indexOf("g")!=-1){ //包含 g
            	spec_num = Utils.mul(spec_num, 2f);
            	float avg_price = price/spec_num;
            	price = Math.round(avg_price*100f) / 100f;
            }
            /*********************************************/
            
            
            map.put("product", product);
//          map.put("spec_num", spec_num);
//          map.put("spec_name", spec_name);
            map.put("price", price);
            map.put("otherType", otherType);
            // 添加到list
            userList.add(map);
        }
        return userList;
    }

    /**
     * 验证EXCEL文件
     * 
     * @param filePath
     * @return
     */
    public boolean validateExcel(String filePath) {
        if (filePath == null || !(isExcel2003(filePath) || isExcel2007(filePath))) {
            errorMsg = "文件名不是excel格式";
            return false;
        }
        return true;
    }

    // @描述：是否是2003的excel，返回true是2003
    public static boolean isExcel2003(String filePath) {
        return filePath.matches("^.+\\.(?i)(xls)$");
    }

    // @描述：是否是2007的excel，返回true是2007
    public static boolean isExcel2007(String filePath) {
        return filePath.matches("^.+\\.(?i)(xlsx)$");
    }
    
}