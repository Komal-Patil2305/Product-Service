package com.komal.ProductService.exception;

import net.bytebuddy.implementation.bind.annotation.Super;

public class ResourseAlreadyExistException extends RuntimeException {
	
	public ResourseAlreadyExistException(String message) {
		super(message);
	}
}
