package com.mallcai.bs.utils;


import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mallcai.bs.common.ExcelField;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @ClassName: ExcelUtil
 * @Description:
 * @Author: zhangxuefeng
 * @Date: 2019/6/25 上午12:45
 * @Version: 1.0
 **/
@Slf4j
public class ExcelUtil {

    public static HSSFWorkbook createHSSFWorkbookWithTitleRow(List<String> titles, String sheetName) {
        try {
            HSSFWorkbook wb = new HSSFWorkbook();
            HSSFSheet sheet = wb.createSheet(sheetName);
            HSSFRow row = sheet.createRow(0);

            HSSFCell cell;
            for (int i = 0; i < titles.size(); i++) {
                cell = row.createCell(i);
                cell.setCellValue(titles.get(i));
            }

            return wb;
        } catch (Exception e) {
            System.out.println("创建excel文件失败: " + e.getMessage());
        }
        return null;
    }


    public static <T> void create(HSSFWorkbook wb, String sheetName, List<T> beans) {


        HSSFSheet sheet = wb.createSheet(sheetName);

        if (CollectionUtils.isEmpty(beans)) {
            return;
        }
        Class<? extends Object> cls = beans.get(0).getClass();
        List<Field> declaredFields = Lists.newArrayList();
        while (cls != null) {
            declaredFields.addAll(Arrays.asList(cls.getDeclaredFields()));
            //得到父类,然后赋给自己
            cls = cls.getSuperclass();
        }
        Map<Field, Float> fieldSortMap = Maps.newHashMap();
        // 筛选出标有注解的字段
        for (Field field : declaredFields) {
            ExcelField annotation = field.getAnnotation(ExcelField.class);
            if (annotation != null) {
                fieldSortMap.put(field, annotation.sort());
            }
        }
        List<Map.Entry<Field, Float>> list = Lists.newArrayList(fieldSortMap.entrySet());
        //升序排序
        Collections.sort(list, (o1, o2) -> o1.getValue().compareTo(o2.getValue()));

        List<Field> annotationFields = Lists.newArrayList();
        list.forEach(n -> annotationFields.add(n.getKey()));

        // 获取注解的值，即内容标题
        HSSFRow row = sheet.createRow(0);
        for (int i = 0; i < annotationFields.size(); i++) {
            HSSFCell cell = row.createCell(i);
            cell.setCellValue(annotationFields.get(i).getAnnotation(ExcelField.class).title());
        }
        try {
            // 获取内容
            for (int num = 1; num <= beans.size(); num++) {
                int index = num - 1;
                T t = beans.get(index);
                HSSFRow dataRow = sheet.createRow(num);
                for (int cellIndex = 0; cellIndex < annotationFields.size(); cellIndex++) {
                    Field field = annotationFields.get(cellIndex);
                    String fieldName = field.getName();
                    String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Method method = ReflectionUtils.findMethod(t.getClass(), methodName);
                    if (method != null) {
                        Object value = ReflectionUtils.invokeMethod(method, t);
                        HSSFCell dataCell = dataRow.createCell(cellIndex);
                        dataCell.setCellValue(String.valueOf(value));
                    }
                }
            }
        } catch (Exception e) {
            log.info("实体对象转数组失败", e);
        }
//        return result;
    }


}
