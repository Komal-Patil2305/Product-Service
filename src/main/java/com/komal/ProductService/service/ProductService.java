package com.komal.ProductService.service;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.komal.ProductService.entity.ProductEntity;
import com.komal.ProductService.model.ProductModel;
import com.komal.ProductService.model.Product_Supplier_Category;

public interface ProductService {

	public boolean addproduct(ProductModel productmodel) ;
	public ProductModel getProductbyId(long productId);
	public Product_Supplier_Category getProductwithSCbyPId(long productId);
	
	
	
	public boolean deleteProductById(long productId);
	public boolean updateproduct(ProductModel productmodel );
	
	public List<ProductModel> getAllProduct();
	public List<ProductModel> getProduuctByDesaOrder();
	
	public  List<ProductModel> sortProduct(String OrderType, String property);
	
	public double gettingMaxPriceProduct();
	public List<ProductModel> getMaxPriceProduct();
	
	public ProductModel getproductByName(String productName);
	public List<ProductModel> getAllProducts(double lowPrice, double highPrice);
	public List<ProductModel> getproductStartWith(String expression);
	public double productPriceAverage();
	public double countOfTotalProduct();
	public ProductModel getproductByProductQuantity(int productQuantity);
	
	
	public ProductModel getProductsByName(String productName);
	public Map<String, Object> uploadSheet(MultipartFile file);
//	public String uploadSheets(MultipartFile file);
	

}
