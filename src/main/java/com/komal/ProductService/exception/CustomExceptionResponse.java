package com.komal.ProductService.exception;

public class CustomExceptionResponse {
	
	private String path;
	private String timestamp;
	private String message;

	
	public CustomExceptionResponse() {
		super();
		// TODO Auto-generated constructor stub
	}


	public CustomExceptionResponse(String path, String timestamp, String message) {
		super();
		this.path = path;
		this.timestamp = timestamp;
		this.message = message;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public String getTimestamp() {
		return timestamp;
	}


	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}


	public String getMessage() {
		return message;
	}


	public void setMessage(String message) {
		this.message = message;
	}


	@Override
	public String toString() {
		return "CustomExceptionResponse [path=" + path + ", timestamp=" + timestamp + ", message=" + message + "]";
	}
	

}
