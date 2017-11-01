package zhu.aa.excel;

import java.io.File;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import zhu.aa.dao.ORGDaoImpl;
import zhu.aa.pojo.Bill;
import zhu.aa.service.ORGService;
import zhu.aa.service.ORGServiceImpl;

public class BillExcel {

	public void createExcel(List list,String orgname) {
		// 第一步，创建一个webbook，对应一个Excel文件
		HSSFWorkbook wb = new HSSFWorkbook();
		// 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
		HSSFSheet sheet = wb.createSheet("表一");
		// 设置单元格宽高
		// 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
		HSSFRow row = sheet.createRow((int) 0);
		// 第四步，创建单元格，并设置值表头 设置表头居中
		HSSFCellStyle style = wb.createCellStyle();
		// 创建一个居中格式
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		HSSFCell cell = row.createCell((short) 0);
		cell.setCellValue("服务号");
		cell.setCellStyle(style);
		sheet.setColumnWidth((short) 0, 20 * 256);
		cell = row.createCell((short) 1);
		cell.setCellValue("求救时间");
		cell.setCellStyle(style);
		sheet.setColumnWidth((short) 1, 20 * 300);
		cell = row.createCell((short) 2);
		cell.setCellValue("用户名称");
		cell.setCellStyle(style);
		cell = row.createCell((short) 3);
		cell.setCellValue("所属机构");
		cell.setCellStyle(style);
		sheet.setColumnWidth((short) 3, 20 * 256);
		cell = row.createCell((short) 4);
		cell.setCellValue("省");
		cell.setCellStyle(style);
		cell = row.createCell((short) 5);
		cell.setCellValue("市");
		cell.setCellStyle(style);
		sheet.setColumnWidth((short) 5, 20 * 256);
		cell = row.createCell((short) 6);
		cell.setCellValue("县");
		cell.setCellStyle(style);
		cell = row.createCell((short) 7);
		cell.setCellValue("车牌");
		cell.setCellStyle(style);
		sheet.setColumnWidth((short) 7, 20 * 200);
		cell = row.createCell((short) 8);
		cell.setCellValue("故障类型");
		cell.setCellStyle(style);
		sheet.setColumnWidth((short) 8, 20 * 500);
		cell = row.createCell((short) 9);
		cell.setCellValue("救援结果");
		cell.setCellStyle(style);
		sheet.setColumnWidth((short) 9, 20 * 300);
		cell = row.createCell((short) 10);
		cell.setCellValue("小计金额");
		cell.setCellStyle(style);
		cell = row.createCell((short) 11);
		cell.setCellValue("拖车公里数");
		cell.setCellStyle(style);
		cell = row.createCell((short) 12);
		cell.setCellValue("超范围补助公里数");
		cell.setCellStyle(style);
		sheet.setColumnWidth((short) 12, 20 * 300);
		cell = row.createCell((short) 13);
		cell.setCellValue("费用类型");
		cell.setCellStyle(style);
		sheet.setColumnWidth((short) 13, 20 * 500);
		cell = row.createCell((short) 14);
		cell.setCellValue("确认金额");
		cell.setCellStyle(style);
		cell = row.createCell((short) 15);
		cell.setCellValue("到达里程");
		cell.setCellStyle(style);
		cell = row.createCell((short) 16);
		cell.setCellValue("牵引里程");
		cell.setCellStyle(style);
		cell = row.createCell((short) 17);
		cell.setCellValue("过路费");
		cell.setCellStyle(style);
		cell = row.createCell((short) 18);
		cell.setCellValue("应结算金额");
		cell.setCellStyle(style);
		cell = row.createCell((short) 19);
		cell.setCellValue("差额");
		cell.setCellStyle(style);
		cell = row.createCell((short) 20);
		cell.setCellValue("接电");
		cell.setCellStyle(style);
		cell = row.createCell((short) 21);
		cell.setCellValue("换胎");
		cell.setCellStyle(style);
		cell = row.createCell((short) 22);
		cell.setCellValue("送油");
		cell.setCellStyle(style);
		cell = row.createCell((short) 23);
		cell.setCellValue("拖车");
		cell.setCellStyle(style);
		cell = row.createCell((short) 24);
		cell.setCellValue("困境");
		cell.setCellStyle(style);
		cell = row.createCell((short) 25);
		cell.setCellValue("照片审核状态");
		cell.setCellStyle(style);
		sheet.setColumnWidth((short) 25, 20 * 200);
		cell = row.createCell((short) 26);
		cell.setCellValue("备注");
		cell.setCellStyle(style);
		try {
			// 第五步，写入实体数据 实际应用中这些数据从数据库得到，
			System.out.println("生成账单中。。。。。"+list.size());
			for (int i = 0; i < list.size(); i++) {
				Bill bill = (Bill) list.get(i);
				if (bill == null) {
					// 第四步，创建单元格，并设置值
					row = sheet.createRow((int) i + 1);
					row.createCell((short) 0).setCellValue("");
					row.createCell((short) 1).setCellValue("");
					row.createCell((short) 2).setCellValue("");
					row.createCell((short) 3).setCellValue("");
					row.createCell((short) 4).setCellValue("");
					row.createCell((short) 5).setCellValue("");
					row.createCell((short) 6).setCellValue("");
					row.createCell((short) 7).setCellValue("");
					row.createCell((short) 8).setCellValue("");
					row.createCell((short) 9).setCellValue("");
					row.createCell((short) 10).setCellValue("");
					row.createCell((short) 11).setCellValue("");
					row.createCell((short) 12).setCellValue("");
					row.createCell((short) 13).setCellValue("");
					row.createCell((short) 25).setCellValue("");
					cell = row.createCell((short) 27);
				}else {
					// 第四步，创建单元格，并设置值
					row = sheet.createRow((int) i + 1);
					row.createCell((short) 0).setCellValue(bill.getSERVICE_TRADE_ID());
					row.createCell((short) 1).setCellValue(bill.getRESCUE_TIME());
					row.createCell((short) 2).setCellValue(bill.getUSER_NAME());
					row.createCell((short) 3).setCellValue(bill.getNAME_S());
					row.createCell((short) 4).setCellValue(bill.getCITY());
					row.createCell((short) 5).setCellValue(bill.getAREAS());
					row.createCell((short) 6).setCellValue(bill.getCOUNTRY());
					row.createCell((short) 7).setCellValue(bill.getLICENSE());
					row.createCell((short) 8).setCellValue(bill.getCASEDESC());
					row.createCell((short) 9).setCellValue(bill.getSTATUS_DETAIL());
					row.createCell((short) 10).setCellValue(bill.getCOMFIRMED_FEE());
					row.createCell((short) 11).setCellValue(bill.getTRAIL_RANGE_KM());
					row.createCell((short) 12).setCellValue(bill.getOUTER_RANGE_KM());
					row.createCell((short) 13).setCellValue(bill.getFEE_TYPE_DESC());
					row.createCell((short) 25).setCellValue(bill.getPIC_AUDIT_STATE());
					cell = row.createCell((short) 27);
				}
			}
			// 第六步，将文件存到指定位置
			Calendar cal = Calendar.getInstance();
			int m = cal.get(Calendar.MONTH);
			int y = cal.get(Calendar.YEAR);
			if (m == 0) {
				m = 12;
				y = y - 1;
			}
			FileOutputStream fout = new FileOutputStream(orgname + y + "年" + m + "月账单.xls");//"D://" +
			System.out.println("excel已生成");
			wb.write(fout);
			fout.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}