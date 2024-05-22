package com.komal.ProductService.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class ProductModel  {
	@Min(value=1,message= "invalid supplier id")
	private long productId;
	@NotBlank(message="supplier name should not be blank")
    @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name must contain only letters")
	private String productName;
	private long supplierId;
	private long categoryId;
	@Min(value=1)
	private int productQuantity;
	@Min(value=1)
	private double productPrice;
	@Min(value=1)
	private int deliveryCharges;
	
	public ProductModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ProductModel(@Min(value = 1, message = "invalid supplier id") long productId,
			@NotBlank(message = "supplier name should not be blank") @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name must contain only letters") String productName,
			long supplierId, long categoryId, @Min(1) int productQuantity, @Min(1) double productPrice,
			@Min(1) int deliveryCharges) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.supplierId = supplierId;
		this.categoryId = categoryId;
		this.productQuantity = productQuantity;
		this.productPrice = productPrice;
		this.deliveryCharges = deliveryCharges;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public long getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(long supplierId) {
		this.supplierId = supplierId;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getDeliveryCharges() {
		return deliveryCharges;
	}

	public void setDeliveryCharges(int deliveryCharges) {
		this.deliveryCharges = deliveryCharges;
	}

	@Override
	public String toString() {
		return "ProductModel [productId=" + productId + ", productName=" + productName + ", supplierId=" + supplierId
				+ ", categoryId=" + categoryId + ", productQuantity=" + productQuantity + ", productPrice="
				+ productPrice + ", deliveryCharges=" + deliveryCharges + "]";
	}
	
	
	

	
	

}
