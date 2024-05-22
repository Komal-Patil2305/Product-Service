package com.komal.ProductService.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class SupplierModel {
		
		@Min(value=1,message= "invalid supplier id")
		private long supplierId;
		@NotBlank(message="supplier name should not be blank")
		 @Pattern(regexp = "^[a-zA-Z ]+$", message = "Name must contain only letters")
		private String supplierName;
		@NotBlank(message="supplier city should not be blank")
		@Pattern(regexp = "^[a-zA-Z]+$", message = "Name must contain only letters")
		private String city;
		@Min(value=100000)
		@Max(value=999999)
		private int postalCode;
		@NotBlank(message="supplier country should not be blank")
		private String country;
		@NotBlank(message="supplier mobile no should not be blank")
		@Pattern(regexp = "^[1-9]\\d{9}$",  message = "mobile number contain 10 numbers")
		private String mobileNo;
		
		public SupplierModel() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		public SupplierModel(long supplierId, String supplierName, String city, int postalCode, String country,
				String mobileNo) {
			super();
			this.supplierId = supplierId;
			this.supplierName = supplierName;
			this.city = city;
			this.postalCode = postalCode;
			this.country = country;
			this.mobileNo = mobileNo;
		}
		
		
		public long getSupplierId() {
			return supplierId;
		}

		public void setSupplierId(long supplierId) {
			this.supplierId = supplierId;
		}

		public String getSupplierName() {
			return supplierName;
		}

		public void setSupplierName(String supplierName) {
			this.supplierName = supplierName;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public int getPostalCode() {
			return postalCode;
		}

		public void setPostalCode(int postalCode) {
			this.postalCode = postalCode;
		}

		public String getCountry() {
			return country;
		}

		public void setCountry(String country) {
			this.country = country;
		}

		public String getMobileNo() {
			return mobileNo;
		}

		public void setMobileNo(String mobileNo) {
			this.mobileNo = mobileNo;
		}

		@Override
		public String toString() {
			return "SupplierModel [supplierId=" + supplierId + ", supplierName=" + supplierName + ", city=" + city
					+ ", postalCode=" + postalCode + ", country=" + country + ", MobileNo=" + mobileNo + "]";
		}

		
		
		
}
