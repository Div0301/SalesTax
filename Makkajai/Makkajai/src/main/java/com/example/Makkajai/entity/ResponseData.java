package com.example.Makkajai.entity;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

/**
 * 
 * @author DivyanshiChaturvedi
 *
 */
@JsonAutoDetect
public class ResponseData {
	
	private double salesTax;
	private double total;

	public double getSalesTax() {
		return salesTax;
	}
	public double getTotal() {
		return total;
	}

	public void setSalesTax(double salesTax) {
		this.salesTax = salesTax;
	}
	public void setTotal(double total) {
		this.total = total;
	}
}
