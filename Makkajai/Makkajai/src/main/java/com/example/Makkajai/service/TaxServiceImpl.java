package com.example.Makkajai.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Makkajai.Util.CommonUtility;
import com.example.Makkajai.entity.GoodsMetadata;
import com.example.Makkajai.entity.ResponseData;

/**
 * 
 * @author DivyanshiChaturvedi
 *
 */
@Service
public class TaxServiceImpl {

	@Autowired
	private CommonUtility commonUtility;

	/**
	 * 
	 * @param productInfo
	 * @return
	 */
	public ResponseData calculateTax(List<GoodsMetadata> productInfo) {

		double salesTaxTotal = 0;
		double total = 0;

		for (GoodsMetadata product : productInfo) {
			double salesTax;
			double tax = 0;
			if (commonUtility.importedProduct(product)) {
				salesTax = 0;
				tax = 0;
				salesTax = product.getProductPrice() * 0.15;
				salesTaxTotal += salesTax;
				tax = salesTax + product.getProductPrice();
			} else if (commonUtility.impNeccesaryPt(product)) {
				salesTax = 0;
				tax = 0;
				salesTax = product.getProductPrice() * 0.05;
				salesTaxTotal += salesTax;
				tax = salesTax + product.getProductPrice();
			} else if (commonUtility.taxOnGoods(product)) {
				salesTax = 0;
				tax = 0;
				salesTax = product.getProductPrice() * 0.1;
				salesTaxTotal += salesTax;
				tax = salesTax + product.getProductPrice();
			} else {
				total += product.getProductPrice();
			}
			total = commonUtility.taxOnProduct(total, product, tax);
		}
		ResponseData response = createResponse(salesTaxTotal, total);

		return response;
	}

	/**
	 * 
	 * @param salesTaxTotal
	 * @param total
	 * @return
	 */
	public ResponseData createResponse(double salesTaxTotal, double total) {
		ResponseData response = new ResponseData();

		response.setSalesTax(new BigDecimal(salesTaxTotal).setScale(2, RoundingMode.HALF_UP).doubleValue());
		response.setTotal(new BigDecimal(total).setScale(2, RoundingMode.HALF_UP).doubleValue());
		return response;
	}

}
