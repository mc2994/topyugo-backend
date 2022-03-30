package com.topyougo.productimport.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.QuoteMode;
import org.springframework.http.HttpHeaders;
import com.topyougo.productimport.dto.ProductsDTO;
import com.topyougo.productimport.service.Report;

public class CSVReport extends Report {

    private ByteArrayOutputStream out = new ByteArrayOutputStream();

    @Override
    public byte[] getFile() {
	return out.toByteArray();
    }

    @Override
    public void createReport(List<ProductsDTO> productsList) {
	final CSVFormat format = CSVFormat.DEFAULT.withQuoteMode(QuoteMode.MINIMAL);

	try (CSVPrinter csvPrinter = new CSVPrinter(new PrintWriter(out), format);) {
	    for (ProductsDTO product : productsList) {
		List<String> data = Arrays.asList(

		);

		csvPrinter.printRecord(productsList);
	    }

	    csvPrinter.flush();
	} catch (IOException e) {
	    throw new RuntimeException("fail to import data to CSV file: " + e.getMessage());
	}
    }

    @Override
    public HttpHeaders getHeaders() {
	HttpHeaders headers = new HttpHeaders();
	headers.add("Content-Disposition", "attachment;filename=order_file.csv");
	return headers;
    }

    @Override
    public String getMediaType() {
	return "text/csv";
    }
}
