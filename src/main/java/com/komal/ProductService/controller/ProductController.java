package com.komal.ProductService.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.komal.ProductService.model.ProductModel;
import com.komal.ProductService.model.Product_Supplier_Category;
import com.komal.ProductService.service.ProductService;

@RestController
@RequestMapping("product")
public class ProductController {
	
	@Autowired
	private ProductService pservice;
	
	
	@PostMapping("add-product")
	public ResponseEntity<String> addproduct(@RequestBody ProductModel productmodel){
		boolean isAdded = pservice.addproduct(productmodel);
		return ResponseEntity.ok("product is Added");
		}
	
	
	@GetMapping("get-product/{productId}")
	public ResponseEntity<ProductModel> getProductbyId(@PathVariable long productId){
		 ProductModel productModel = pservice.getProductbyId(productId);
		return ResponseEntity.ok(productModel);
		
	}
	
	@GetMapping("get-products-with-sc/{productId}")
	public ResponseEntity<Product_Supplier_Category> getProductbyIdUsingSupplierCategory(@PathVariable long productId){
		 Product_Supplier_Category psc = pservice.getProductwithSCbyPId(productId);
		return ResponseEntity.ok(psc);
		
	}
	
	@DeleteMapping("delete-product/{productId}")
	public ResponseEntity<String> deleteProductbyId(@PathVariable long productId){
		 boolean isdeleted = pservice.deleteProductById(productId);
		return ResponseEntity.ok("deleted successfully");
		
	}
	@PutMapping("update-product")
	public ResponseEntity<String> updateProduct(@RequestBody ProductModel productmodel){
		 boolean isUpdated = pservice.updateproduct(productmodel);
		return  ResponseEntity.ok("Updated successfully");
		
	}
	
	@GetMapping("list-of-all-product")
	public ResponseEntity<List<ProductModel>> getAllproducts(){
		
		return ResponseEntity.ok(pservice.getAllProduct());
		
	}
	
	@GetMapping("list-of-product-by-des-order")
	public ResponseEntity<List<ProductModel>> getproductByDesOrder(){
		return ResponseEntity.ok(pservice.getProduuctByDesaOrder());
	}
	
//	@GetMapping("sort-product/{OrderType}/{property}")
//	public ResponseEntity<List<ProductModel>> sortproduct(@PathVariable String OrderType,@PathVariable String property){
//		return ResponseEntity.ok(pservice.sortProduct(OrderType, property));
//		
//	}
	
	@GetMapping("sort-product")
	public ResponseEntity<List<ProductModel>> sortproduct(@RequestParam String orderType,@RequestParam String propertyName){
		return ResponseEntity.ok(pservice.sortProduct(orderType, propertyName));
	
	}
	
	@GetMapping("max-price")
	public ResponseEntity<Double> getMaxPrice(){
		return ResponseEntity.ok(pservice.gettingMaxPriceProduct());
		
	}
	
	@GetMapping("max-price-product")
	public ResponseEntity<List<ProductModel>> getMaxPriceProduct(){
		return ResponseEntity.ok(pservice.getMaxPriceProduct());
		
	}
	
	@GetMapping("get-product-by-name/{productName}")
	public ResponseEntity<ProductModel> getproductByName(@PathVariable String productName){
		return ResponseEntity.ok(pservice.getproductByName(productName));
		
	}
	
	@GetMapping("get-all-products")
	public ResponseEntity<List<ProductModel>> getAllProducts(@RequestParam double lowPrice, @RequestParam double highPrice) {
		
		return ResponseEntity.ok(pservice.getAllProducts(lowPrice, highPrice));
	}
	
	
	@GetMapping("get-product-start-with")
	public ResponseEntity<List<ProductModel>> getproductStartWith(@RequestParam String expression) {
		List<ProductModel> list = pservice.getproductStartWith(expression);
		return ResponseEntity.ok(list);
		
	}
	
	@GetMapping("product-price-average")
	public ResponseEntity<Double> productPriceAverage(){
		double priceAverage = pservice.productPriceAverage();
		return ResponseEntity.ok(priceAverage);
		
		
	}
	
	@GetMapping("count-of-total-product")
	public ResponseEntity<Double> countOfTotalProduct() {
		double countOfTotalProduct = pservice.countOfTotalProduct();
		return ResponseEntity.ok(countOfTotalProduct);
	}
	
	@GetMapping("get-product-by-product-quantity/{productQuantity}")
	 public ResponseEntity<ProductModel> getproductByProductQuantity(@PathVariable int productQuantity){
		
		 ProductModel city = pservice.getproductByProductQuantity(productQuantity);
		 return ResponseEntity.ok(city);
		
	 }
	
	@PostMapping("upload-sheet")
	public ResponseEntity<Map<String, Object>> uploadSheet(@RequestParam MultipartFile file){
		 System.out.println(file.getOriginalFilename());
		Map<String, Object> finalMap = pservice.uploadSheet(file);
		return ResponseEntity.ok(finalMap);
		
	}
	
}
