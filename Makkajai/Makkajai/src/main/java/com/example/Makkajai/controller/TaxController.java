package com.example.Makkajai.controller;

import org.springframework.web.bind.annotation.RestController;

import com.example.Makkajai.entity.GoodsMetadata;
import com.example.Makkajai.entity.ResponseData;
import com.example.Makkajai.service.TaxServiceImpl;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

import java.text.DecimalFormat;
import java.text.Format;
import java.util.List;

import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 
 * @author DivyanshiChaturvedi
 *
 */

@RestController
@RequestMapping("/taxCalculator")
public class TaxController {
	
	@Autowired 
	private TaxServiceImpl taxServiceImpl;
	
	private static final Format FORMATTER = new DecimalFormat("0.00");
	
	@PostMapping(value = "/save", consumes = MediaType.APPLICATION_JSON_VALUE)
	@Produces(MediaType.ALL_VALUE)
	@ApiOperation(value = "This is used to calculate Tax")
	public String updateChannelConfigurationWithStatus(
			@ApiParam(value = "Product Info sent as body", required = true) @RequestBody(required = true) List<GoodsMetadata> productInfo) {
		ResponseData res = taxServiceImpl.calculateTax(productInfo);
		StringBuilder result = new StringBuilder();
		productInfo.stream().forEach(product->{
			result.append(product.getProductQuantity() + " " + product.getProductName() + ": " + FORMATTER.format(product.getProductPrice()) + "\n");
		});
		result.append("Sales Taxes: "+ FORMATTER.format(res.getSalesTax()) +"\n");
		result.append("Total: "+ FORMATTER.format(res.getTotal()));
		return result.toString();
	}

}
