package com.komal.ProductService.utility;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.komal.ProductService.exception.ResourseAlreadyExistException;
import com.komal.ProductService.exception.ResourseNotExistException;
import com.komal.ProductService.exception.SomethingWentWrongException;

import com.komal.ProductService.model.ProductModel;



@Component
public class ObjectValidator {

	
	
	
	
	
	
	public  Map<String, String> validateProduct(ProductModel productmodel){
		Map<String, String> validatemap= new HashMap<>();
		String productName = productmodel.getProductName();
		double productPrice = productmodel.getProductPrice();
		int productQuantity = productmodel.getProductQuantity();
		int deliveryCharges = productmodel.getDeliveryCharges();
		long supplierId = productmodel.getSupplierId();
		long categoryId = productmodel.getCategoryId();
		
		
		if(productName==null || productName.trim().equals("")){
			validatemap.put("productName", "Invalid Product Name");
		}
		
		if(productPrice<=0) {
			validatemap.put("productPrice", "product price shoule be greter than 0");
		}
		
		if(productQuantity<=0) {
			validatemap.put("productQuantity", "product Quantity shoule be greter than 0");
		}
		
		if(deliveryCharges<=0) {
			validatemap.put("deliverycharges", "delivery carges should not be negative");
		}
		
		
		if(supplierId>0) {
			try {
				// supplierservice.getsupplierbyId(supplierId);
			} catch (ResourseNotExistException e) {
				
				validatemap.put("Supplier",e.getMessage());
				
			}catch(SomethingWentWrongException e){
				validatemap.put("Supplier", e.getMessage());
			}
		}else {
			validatemap.put("Supplier", "Supplier Id must be grater than 0");
		}
		
		
		if(categoryId>0) {
			try {
	// categoryService.getcategoryById(categoryId);
		} catch (ResourseNotExistException e) {
			
			validatemap.put("Category",e.getMessage());
			
		}catch(SomethingWentWrongException e){
			validatemap.put("Categoryr", e.getMessage());
		}
	}else {
		validatemap.put("Category", "Category Id must be grater than 0");
	}
			
		
		return validatemap;
		
	}
}
