package com.dailuobo.api.domain.util;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.dailuobo.api.domain.entity.DailyOrder;

import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class ExcelUtil {

	private static final String DEFAULT_SHEET_NAME = "sheet1";


	public static void main(String[] args) {
		String[] columns = new String[] { "商品编号", "商品名称", "项目代码", "计重标识", "单价", "贮存方式", "上限", "下限", "销售商" };

		List<ExcelData> list = new ArrayList<>();
		ExcelData data = new ExcelData();
		data.setProductCode("11101");
		data.setProductName("生菜");
		data.setProjectCode("11101");
		data.setCalFlag("计重");
		data.setUnitPrice("1");
		data.setKeepType("常温");
		data.setUpperLimit("");
		data.setLowerLimit("");
		data.setSaleName("安徽菜菜");
		list.add(data);
		writeExcel(list, columns, "c:/test.xls");
	}

	/**
	 * 将list内容写入excel
	 *
	 * @param list
	 * @param columns
	 * @param fullFileName
	 */
	public static void writeExcel(List<ExcelData> list, String[] columns, String fullFileName) {
		if (list == null || list.size() == 0) {
			return;
		}

		String[][] values = new String[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			values[i] = new String[columns.length];

			ExcelData obj = list.get(i);
			values[i][0] = obj.getProductCode();
			values[i][1] = obj.getProductName();
			values[i][2] = obj.getProjectCode();
			values[i][3] = obj.getCalFlag();
			values[i][4] = obj.getUnitPrice();
			values[i][5] = obj.getKeepType();
			/*values[i][6] = obj.getUpperLimit();
			values[i][7] = obj.getLowerLimit();*/
			values[i][6] = obj.getQuality();
			values[i][7] = obj.getQualityTime();
			values[i][8] = obj.getSaleName();
		}

		HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(null, columns, values, null);

		try {
			OutputStream os = new FileOutputStream(fullFileName);
			wb.write(os);
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 将list内容写入excel
	 *
	 * @param list
	 * @param columns
	 */
	public static HSSFWorkbook writeExcelToNew(List<ExcelData> list, String[] columns) {
		if (list == null || list.size() == 0) {
			return null;
		}

		String[][] values = new String[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			values[i] = new String[columns.length];

			ExcelData obj = list.get(i);
			values[i][0] = obj.getProductCode();
			values[i][1] = obj.getProductName();
			values[i][2] = obj.getProjectCode();
			values[i][3] = obj.getCalFlag();
			values[i][4] = obj.getUnitPrice();
			values[i][5] = obj.getKeepType();
			values[i][6] = obj.getQuality();
			values[i][7] = obj.getQualityTime();
			values[i][8] = obj.getSaleName();
		}

		return ExcelUtil.getHSSFWorkbook(null, columns, values, null);
	}

	public static void dailyOrderWriteExcel(List<DailyOrder> list, String[] columns, String fullFileName,HttpServletResponse response) throws IOException {
		if (CollectionUtils.isEmpty(list)) {
			return;
		}

		String[][] values = new String[list.size()][];
		for (int i = 0; i < list.size(); i++) {
			values[i] = new String[columns.length];

			DailyOrder data = list.get(i);
			if(data.getPurchaseDate()!=null){
				String purchaseDate=DatetimeUtil.fmtDate(data.getPurchaseDate(),DatetimeUtil.DATE_PATTERN);
				purchaseDate=purchaseDate+DatetimeUtil.dateToWeek(data.getPurchaseDate());
				values[i][0] = purchaseDate;
			}
			values[i][1]=data.getStoreName();
			values[i][2]=data.getNewUser()!=null?data.getNewUser().toString():"0";
			values[i][3]=data.getNewUserOrder()!=null?data.getNewUserOrder().toString():"0";
			values[i][4]=data.getNewUserAmount()!=null?Utils.formatNumber(data.getNewUserAmount(),2,RoundingMode.HALF_UP).toString():"0";
			if(data.getNewUserAmount()!=null&&data.getNewUser()!=null&&data.getNewUser()!=0){
				Double price=data.getNewUserAmount()*100/data.getNewUser();
				values[i][5]= String.valueOf(Utils.formatNumber(price/100,2,RoundingMode.HALF_UP));
			}
			values[i][6]=data.getOldUser()!=null?data.getOldUser().toString():"0";
			values[i][7]=data.getOldUserOrder()!=null?data.getOldUserOrder().toString():"0";
			values[i][8]=data.getOldUserAmount()!=null?Utils.formatNumber(data.getOldUserAmount(),2,RoundingMode.HALF_UP).toString().toString():"0";
			if(data.getOldUserAmount()!=null&&data.getOldUser()!=null&&data.getOldUser()!=0){
				Double price=data.getOldUserAmount()*100/data.getOldUser();
				values[i][9]= String.valueOf(Utils.formatNumber(price/100,2,RoundingMode.HALF_UP));
			}
			values[i][10]=data.getTotalUser()!=null?data.getTotalUser().toString():"0";
			values[i][11]=data.getTotalOrder()!=null?(data.getTotalOrder()+data.getUndOrder())+"":"0";

			values[i][12]=data.getTotalAmount()!=null?Utils.formatNumber((data.getTotalAmount()+data.getUndActualPrice()),2,RoundingMode.HALF_UP).toString():"0";
			Double totalAmount=data.getTotalAmount()!=null?data.getTotalAmount():0d;
			Double UndActualPrice=data.getUndActualPrice()!=null?data.getUndActualPrice():0d;
			Double vipPrice=data.getVipPrice()!=null?data.getVipPrice():0d;
			Double couponPrice=data.getCouponPrice()!=null?data.getCouponPrice():0d;
			values[i][13]=Utils.add(totalAmount,UndActualPrice,vipPrice,couponPrice).toString();
			values[i][14]=data.getVipPrice()!=null?Utils.formatNumber(data.getVipPrice(),2,RoundingMode.HALF_UP).toString():"0";
			values[i][15]=data.getCouponPrice()!=null?Utils.formatNumber(data.getCouponPrice(),2,RoundingMode.HALF_UP).toString():"0";
			if(data.getTotalAmount()!=null&&data.getTotalUser()!=null&&data.getTotalUser()!=0){
				Double price=(data.getTotalAmount()+data.getUndActualPrice())*100/(data.getTotalUser()+data.getUndUser());
				values[i][16]= String.valueOf(Utils.formatNumber(price/100,2,RoundingMode.HALF_UP));
			}
//			if(data.getTotalUser()!=null&&data.getTotalUser()!=0){
//				Double price=Utils.formatNumber(Utils.add(totalAmount,vipPrice,couponPrice)*100/data.getTotalUser(),2, RoundingMode.HALF_UP);
//				values[i][17]= String.valueOf(price/100);
//			}

		}

		HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(null, columns, values, null);

		// 第六步，将文件存到浏览器设置的下载位置
		String filename = fullFileName + ".xls";
		response.setContentType("application/ms-excel;charset=UTF-8");
		response.setHeader("Content-Disposition", "attachment;filename="
				.concat(String.valueOf(URLEncoder.encode(filename, "UTF-8"))));
		OutputStream out = response.getOutputStream();


		try {
			wb.write(out);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	/**
	 * 生成excel内容
	 *
	 * @param sheetName
	 * @param title
	 * @param values
	 * @param wb
	 * @return
	 */
	public static HSSFWorkbook getHSSFWorkbook(String sheetName, String[] title, String[][] values, HSSFWorkbook wb) {
		if (sheetName == null) {
			sheetName = DEFAULT_SHEET_NAME;
		}
		// 第一步，创建一个webbook，对应一个Excel文件
		if (wb == null) {
			wb = new HSSFWorkbook();
		}
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet(sheetName);
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow(0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		//style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式2018-11-7 yr更新POI版本此方法改变
		//style.setAlignment(HorizontalAlignment.CENTER); // 创建一个居中格式

		HSSFCell cell = null;
		// 创建标题
		for (int i = 0; i < title.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(title[i]);
			cell.setCellStyle(style);
		}
		// 创建内容
		for (int i = 0; i < values.length; i++) {
			row = sheet.createRow(i + 1);
			for (int j = 0; j < values[i].length; j++) {
				row.createCell(j).setCellValue(values[i][j]);
			}
		}

		return wb;
	}

	public static void exportExcel(String[][] values, String[] columns, String fullFileName,HttpServletResponse response) {


		HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(null, columns, values, null);

		try {
			setResponseHeader(response, fullFileName);
			OutputStream os = response.getOutputStream();
			wb.write(os);
			os.flush();
			os.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//发送响应流方法
	public static void setResponseHeader(HttpServletResponse response, String fileName) {
		try {
			try {
				fileName = new String(fileName.getBytes(),"ISO8859-1");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			response.setContentType("application/octet-stream;charset=ISO8859-1");
			response.setHeader("Content-Disposition", "attachment;filename="+ fileName);
			response.addHeader("Pargam", "no-cache");
			response.addHeader("Cache-Control", "no-cache");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}


