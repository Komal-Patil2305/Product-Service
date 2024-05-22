package com.komal.ProductService.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class CategoryModel {

	@Min(value=1, message="invalid categoryId")
	private long categoryId;
	@NotBlank(message="supplier name should not be blank")
	@Pattern(regexp = "^[a-zA-Z ]+$", message = "Name must contain only letters")
	private String categoryName;
	@NotBlank(message="supplier description should not be blank")
	private String description;
	@Min(value=1)
	private int discount;
	@Min(value=1)
	private int gst;
	
	
	
	public CategoryModel() {
		super();
		// TODO Auto-generated constructor stub
	}



	public CategoryModel(long categoryId, String categoryName, String description, int discount, int gst) {
		super();
		this.categoryId = categoryId;
		this.categoryName = categoryName;
		this.description = description;
		this.discount = discount;
		this.gst = gst;
	}



	public long getCategoryId() {
		return categoryId;
	}



	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}



	public String getCategoryName() {
		return categoryName;
	}



	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public int getDiscount() {
		return discount;
	}



	public void setDiscount(int discount) {
		this.discount = discount;
	}



	public int getGst() {
		return gst;
	}



	public void setGst(int gst) {
		this.gst = gst;
	}



	@Override
	public String toString() {
		return "CategoryModel [categortId=" + categoryId + ", categoryName=" + categoryName + ", description="
				+ description + ", discount=" + discount + ", gst=" + gst + "]";
	}
	
	

}
