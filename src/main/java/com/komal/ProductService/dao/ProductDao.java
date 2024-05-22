package com.komal.ProductService.dao;

import java.util.List;

import com.komal.ProductService.entity.ProductEntity;

import com.komal.ProductService.model.ProductModel;

public interface ProductDao {
	
	public boolean addproduct(ProductEntity productentity) ;
//	public String addproducts(ProductEntity productentity);
	
	public ProductEntity getProductbyId(long productId);
	public boolean deleteProductById(long productId);
	public boolean updateproduct(ProductEntity productentity);
	
	public List<ProductEntity> getAllProducts();
	public List<ProductEntity> getProduuctByDesaOrder();
	
	public  List<ProductEntity> sortProduct(String OrderType, String property);
	public double gettingMaxPriceProduct();
	public List<ProductEntity> getMaxPriceProduct();
  
	public ProductEntity getproductByName(String productName);
	public List<ProductEntity> getAllProducts(double lowPrice, double highPrice);
	public List<ProductEntity> getproductStartWith(String expression);
	public double productPriceAverage();
	public double countOfTotalProduct();
	
	public ProductEntity getproductByProductQuantityall(int productQuantity);
	
	public ProductEntity getproductByProductQuantity(int productQuantity);
	//public List<ProductEntity> getAllProducts(long category, long supplier);
	//public List<ProductEntity> getAllProducts(String supplier);
	
	
	public ProductEntity getProductsByName(String productName);
}
