package com.topyougo.productimport.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.topyougo.productimport.service.FileDownloadService;

@WebMvcTest(value=FileDownloadController.class)
class FileControllerUT {

	@MockBean
	private FileDownloadService fileDownloadService;
	
	@Test
	final void testDownloadExcel() {

	}

	@Test
	final void testDownloadReport() {
	
	}

}
