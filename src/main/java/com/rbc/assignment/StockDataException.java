package com.rbc.assignment;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class StockDataException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
	
	private HttpStatus status;
	private String message;
	
	public StockDataException(HttpStatus status, String message) {
		super(message);
		this.status = status;	
	}

}
