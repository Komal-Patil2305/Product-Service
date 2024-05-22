package com.komal.ProductService.exception;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;



@RestControllerAdvice
public class GlobalExceptionHandler {
	
	   @ExceptionHandler(ResourseAlreadyExistException.class)
		public CustomExceptionResponse xyz(ResourseAlreadyExistException rx, HttpServletRequest request) {
		   String message=rx.getMessage();
		   String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()); 
		   String path=request.getRequestURI();
		   
		   
		   CustomExceptionResponse response= new CustomExceptionResponse(path, timestamp, message);
		   
			return response;
			
		}
	   
	   
	   @ExceptionHandler(MethodArgumentNotValidException.class)
	   public Map<String, String> MethodArgumentNotValidException(MethodArgumentNotValidException me) {
		
		   HashMap<String ,String> errormap= new HashMap<>();
		   List<FieldError> fieldErrors = me.getFieldErrors();	
			for (FieldError fieldError : fieldErrors) {
				String field = fieldError.getField();
				String message = fieldError.getDefaultMessage();
				
				errormap.put(field, message);
			}
			return errormap;
			
	

	   }   
		@ExceptionHandler(ResourseNotExistException.class)
		public CustomExceptionResponse abc(ResourseNotExistException rnx,HttpServletRequest request) {
			String mes = rnx.getMessage();
			String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()); 
			String path=request.getRequestURI();
			
			CustomExceptionResponse response= new CustomExceptionResponse(path, timestamp, mes);
			return response;
		}
		
		
		@ExceptionHandler(SomethingWentWrongException.class)
		public CustomExceptionResponse abc(SomethingWentWrongException se,HttpServletRequest request) {
			String mes = se.getMessage();
			String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(Calendar.getInstance().getTime()); 
			String path=request.getRequestURI();
			
			CustomExceptionResponse response= new CustomExceptionResponse(path, timestamp, mes);
			return response;
		}
		
		
		
		
		

}
