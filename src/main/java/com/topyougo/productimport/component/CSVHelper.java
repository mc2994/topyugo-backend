package com.topyougo.productimport.component;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.codec.binary.StringUtils;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;
import org.apache.commons.csv.QuoteMode;
import org.apache.poi.util.StringUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.topyougo.productimport.dto.OrderStatus;
import com.topyougo.productimport.dto.ProductsDTO;
import com.topyougo.productimport.dto.TrackingStatus;

@Component
public class CSVHelper {
	public static String TYPE = "text/csv";
	static String[] HEADERs = { "Id", "Title", "Description", "Published" };

	public static boolean hasCSVFormat(MultipartFile file) {

		if (!TYPE.equals(file.getContentType())) {
			return false;
		}

		return true;
	}

	public List<ProductsDTO> csvToOrders(InputStream is) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			List<ProductsDTO> productList = new ArrayList<ProductsDTO>();
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (CSVRecord row : csvRecords) {
				ProductsDTO products = new ProductsDTO();
				if(!row.get(0).equals("")) {
					products.setOrderID(Long.valueOf(row.get(0)));
				}
				products.setOrderNo(row.get(1));
				products.setDateOrdered(convertStringToDate(row.get(2)));
				products.setFirstName(row.get(3));
				products.setLastName(row.get(4));
		
				if(OrderConverter.mapValues.containsKey(String.valueOf(row.get(5)))){
					products.setOrderStatus(OrderStatus.fromString(row.get(5)));
				}else {
					products.setOrderStatus(OrderStatus.NEW_ORDER.getValue());
				}
				products.setCodAmount(Double.parseDouble(row.get(6)));
				products.setContactNo(String.valueOf(row.get(7)));
				products.setProduct(row.get(8));
				products.setVariant(row.get(9));
				products.setAmount(isRowEmpty(row.get(10)) ? 0 : Double.parseDouble(row.get(10)));
				products.setQuantity(Integer.parseInt(row.get(11)));
				products.setOrderAmount(isRowEmpty(row.get(12)) ? 0 : Double.parseDouble(row.get(12)));
				products.setAddress(row.get(13));
				products.setShippingDate(convertStringToDate(row.get(14)));
				products.setCourier(row.get(15));
				products.setCustomerNote(row.get(16));
				products.setRtsReason(row.get(17));
				products.setRtsDetails(row.get(18));
				products.setDateIntransit(convertStringToDate(row.get(19)));
				products.setDaysIntransit(isRowEmpty(row.get(20)) ? 0 : Integer.parseInt(row.get(20)));
				products.setShippingFee(isRowEmpty(row.get(21)) ? 0 : Double.parseDouble(row.get(21)));
				products.setProvince(row.get(22));
				products.setRegion(row.get(23));
				products.setTrackingNumber(row.get(24));
				if(!row.get(25).equals("")) {
					products.setTrackingStatus(TrackingStatus.valueOf(row.get(25)).getValue());
				}else {
					products.setTrackingStatus("");
				}
				productList.add(products);
			}
			return productList;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}

	public static ByteArrayInputStream ordersToCSV(List<ProductsDTO> products) {
		final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

		try (ByteArrayOutputStream out = new ByteArrayOutputStream();
				CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
			for (ProductsDTO product : products) {
				List<String> data = Arrays.asList(

				);

				csvPrinter.printRecord(data);
			}

			csvPrinter.flush();
			return new ByteArrayInputStream(out.toByteArray());
		} catch (IOException e) {
			throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
		}
	}

	public static Date convertStringToDate(String dateInput) {
		DateFormat format = new SimpleDateFormat("MMM dd, yyyy", Locale.ENGLISH);
		try {
		//	System.out.println("Date In Transit "+dateInput);
			if(isRowEmpty(dateInput)==false) {
				Date date = format.parse(dateInput);
				return date;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Date convertDate(String dateInput) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
		try {
			//System.out.println("Date In Transit "+dateInput);
			if(isRowEmpty(dateInput)==false) {
				Date date = format.parse(dateInput);
				return date;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static boolean isRowEmpty(String value) {
		if(value != null && !value.isEmpty()) 
		{ return false; }
		return true;
	}
	
	public List<ProductsDTO> csvTrackingUpdate(InputStream is) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			List<ProductsDTO> productList = new ArrayList<ProductsDTO>();
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (CSVRecord row : csvRecords) {
				ProductsDTO products = new ProductsDTO();
				products.setOrderNo(row.get(0));
				products.setTrackingNumber(row.get(1));
				products.setCourier(row.get(2));				
				productList.add(products);
			}
			return productList;
		} catch (IOException e) {
			throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
		}
	}
}