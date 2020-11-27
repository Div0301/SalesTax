package com.example.Makkajai.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.Makkajai.entity.GoodsMetadata;
import com.example.Makkajai.entity.ResponseData;

/**
 * 
 * @author DivyanshiChaturvedi
 *
 */
@Service
public class TaxServiceImpl {

	public ResponseData calculateTax(List<GoodsMetadata> productInfo) {
		
		double salesTaxTotal = 0;
		double total = 0;

		for (GoodsMetadata product : productInfo) {
			double salesTax;
			double tax = 0;
			if (product.isImported() && (!product.getCategory().equals("books") || !product.getCategory().equals("food")
					|| !product.getCategory().equals("medical"))) {
				salesTax = 0;
				tax=0;
				salesTax = product.getProductPrice() * 0.15;
				salesTaxTotal += salesTax;
				tax = salesTax + product.getProductPrice();
			}
			else if(product.isImported() && (product.getCategory().equals("books") || product.getCategory().equals("food")
					|| product.getCategory().equals("medical"))) {
				salesTax = 0;
				tax=0;
				salesTax = product.getProductPrice() * 0.05;
				salesTaxTotal += salesTax;
				tax = salesTax + product.getProductPrice();
			}else if(!product.getCategory().equals("books") && !product.getCategory().equals("food")
					&& !product.getCategory().equals("medical")){
				salesTax = 0;
				tax=0;
				salesTax = product.getProductPrice() * 0.1;
				salesTaxTotal += salesTax;
				tax = salesTax + product.getProductPrice();
			}else {
				total += product.getProductPrice();
			}
			tax = new BigDecimal(tax).setScale(2, RoundingMode.HALF_UP).doubleValue();
			
			
			if(tax>0) {
				total += tax;
			product.setProductPrice(new BigDecimal(tax).setScale(2, RoundingMode.HALF_UP).doubleValue());
			}
		}
		ResponseData response = new ResponseData();
	
		response.setSalesTax(new BigDecimal(salesTaxTotal).setScale(2, RoundingMode.HALF_UP).doubleValue());
		response.setTotal(new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue());
		

		return response;
	}
}
