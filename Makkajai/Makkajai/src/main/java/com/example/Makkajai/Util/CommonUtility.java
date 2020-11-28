package com.example.Makkajai.Util;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.springframework.stereotype.Service;

import com.example.Makkajai.entity.GoodsMetadata;

@Service
public class CommonUtility {

	/**
	 * 
	 * @param product
	 * @return
	 */
	public boolean taxOnGoods(GoodsMetadata product) {
		return !product.getCategory().equals("books") && !product.getCategory().equals("food")
				&& !product.getCategory().equals("medical");
	}

	/**
	 * 
	 * @param product
	 * @return
	 */
	public boolean impNeccesaryPt(GoodsMetadata product) {
		return product.isImported() && (product.getCategory().equals("books") || product.getCategory().equals("food")
				|| product.getCategory().equals("medical"));
	}

	/**
	 * 
	 * @param product
	 * @return
	 */
	public boolean importedProduct(GoodsMetadata product) {
		return product.isImported() && taxOnGoods(product);
	}

	/**
	 * 
	 * @param total
	 * @param product
	 * @param tax
	 * @return
	 */
	public double taxOnProduct(double total, GoodsMetadata product, double tax) {
		tax = new BigDecimal(tax).setScale(2, RoundingMode.HALF_UP).doubleValue();

		if (tax > 0) {
			total += tax;
			product.setProductPrice(new BigDecimal(tax).setScale(2, RoundingMode.HALF_UP).doubleValue());
		}
		return total;
	}

}
