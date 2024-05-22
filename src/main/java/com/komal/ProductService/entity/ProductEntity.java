package com.komal.ProductService.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="Product")
public class ProductEntity  {
	
	@Id
	private long productId;
	@Column(nullable = false, unique = true)
	private String productName;
	
	private long supplierId;
	
	private long categoryId;
	private int productQuantity;
	private double productPrice;
	private int deliveryCharges;
	
	
	public ProductEntity() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ProductEntity(long productId, String productName, long supplierId, long categoryId, int productQuantity,
			double productPrice, int deliveryCharges) {
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
		return "ProductEntity [productId=" + productId + ", productName=" + productName + ", supplierId=" + supplierId
				+ ", categoryId=" + categoryId + ", productQuantity=" + productQuantity + ", productPrice="
				+ productPrice + ", deliveryCharges=" + deliveryCharges + "]";
	}
	
	
	
	

}
