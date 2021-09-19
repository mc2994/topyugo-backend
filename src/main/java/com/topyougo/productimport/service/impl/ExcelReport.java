package com.topyougo.productimport.service.impl;

import java.io.ByteArrayOutputStream;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.http.HttpHeaders;
import com.topyougo.productimport.dto.ProductsDTO;
import com.topyougo.productimport.service.Report;
import com.topyougo.productimport.util.DateUtil;

public class ExcelReport extends Report {

	private ByteArrayOutputStream out = new ByteArrayOutputStream();

	@Override
	public byte[] getFile() {
		return out.toByteArray();
	}

	@Override
	public void createReport(List<ProductsDTO> productsList) {
		String[] headerColumn = { "ID", "ORDER NO.", "ORDER DATE", "FIRST NAME", "LAST NAME", "ORDER STATUS",
				"COD AMT.", "CONTACT NO.", "PRODUCT", "VARIANT", "AMT", "QTY", "ORDER AMT.", "ADDRESS", "SHIPPING DATE",
				"COURIER", "CUSTOMER NOTE", "RTS REASON", "RTS DETAILS", "DATE INTRANSIT", "DAYS INTRANSIT",
				"SHIPP. FEE", "PROVINCE", "REGION", "TRACKING NUMBER", "TRACKING STATUS" };
		
	//	ByteArrayOutputStream out = new ByteArrayOutputStream();
		Workbook workbook = null;

		try {
			workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("Orders");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.GREEN.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			// Row for Header
			Row headerRow = sheet.createRow(0);

			// Header
			for (int col = 0; col < headerColumn.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(headerColumn[col]);
				cell.setCellStyle(headerCellStyle);
			}

			int rowIdx = 1;
			for (ProductsDTO order : productsList) {
				Row row = sheet.createRow(rowIdx++);
				row.createCell(0).setCellValue(order.getOrderID());
				row.createCell(1).setCellValue(order.getOrderNo());
				row.createCell(2).setCellValue(DateUtil.fortmatDateToString(order.getDateOrdered()));
				row.createCell(3).setCellValue(order.getFirstName());
				row.createCell(4).setCellValue(order.getLastName());
				row.createCell(5).setCellValue(order.getOrderStatus().getValue());
				row.createCell(6).setCellValue(order.getCodAmount());
				row.createCell(7).setCellValue(order.getContactNo());
				row.createCell(8).setCellValue(order.getProduct());
				row.createCell(9).setCellValue(order.getVariant());
				row.createCell(10).setCellValue(order.getAmount() != null ? order.getAmount() : 0);
				row.createCell(11).setCellValue(order.getQuantity());
				row.createCell(12).setCellValue(order.getOrderAmount());
				row.createCell(13).setCellValue(order.getAddress());
				row.createCell(14).setCellValue(DateUtil.fortmatDateToString(order.getShippingDate()));
				row.createCell(15).setCellValue(order.getCourier().getValue());
				row.createCell(16).setCellValue(order.getCustomerNote());
				row.createCell(17).setCellValue(order.getRtsReason());
				row.createCell(18).setCellValue(order.getRtsDetails());
				row.createCell(19).setCellValue(DateUtil.fortmatDateToString(order.getDateIntransit()));
				row.createCell(20).setCellValue(order.getDaysIntransit() != null ? order.getDaysIntransit() : 0);
				row.createCell(21).setCellValue(order.getShippingFee() != null ? order.getShippingFee() : 0);
				row.createCell(22).setCellValue(order.getProvince());
				row.createCell(23).setCellValue(order.getRegion());
				row.createCell(24).setCellValue(order.getTrackingNumber());
				row.createCell(25).setCellValue(order.getTrackingStatus().getValue());
			}
			workbook.write(out);
			out.close();
			workbook.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		// return out.toByteArray();
	}

	@Override
	public HttpHeaders getHeaders() {
		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment;filename=order_file.xlsx");
		return headers;
	}

	@Override
	public String getMediaType() {
		return "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
	}
}