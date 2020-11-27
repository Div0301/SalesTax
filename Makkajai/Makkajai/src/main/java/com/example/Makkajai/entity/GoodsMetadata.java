package com.example.Makkajai.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * 
 * @author DivyanshiChaturvedi
 *
 */
@JsonAutoDetect
public class GoodsMetadata {

	private String productName;
	private double productPrice;
	private int productQuantity;
	private boolean isImported;
	private String category;

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public int getProductQuantity() {
		return productQuantity;
	}

	public void setProductQuantity(int productQuantity) {
		this.productQuantity = productQuantity;
	}

	public boolean isImported() {
		return isImported;
	}

	public void setImported(boolean isImported) {
		this.isImported = isImported;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
}
