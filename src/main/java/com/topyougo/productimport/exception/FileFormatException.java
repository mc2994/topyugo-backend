package com.topyougo.productimport.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class FileFormatException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public FileFormatException() {
		super();
	}

	public FileFormatException(String message, Throwable cause) {
		super(message, cause);
	}

	public FileFormatException(String message) {
		super(message);
	}

}
