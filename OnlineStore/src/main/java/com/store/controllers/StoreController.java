package com.store.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.store.models.Product;
import com.store.services.ProductService;

@Controller
public class StoreController
{
	@Autowired
	private ProductService productService;

	Logger logger = LoggerFactory.getLogger(StoreController.class);


	@GetMapping("/store")
	public String view(Model model)
	{
		logger.info("someone access store");
		model.addAttribute("products", productService.getProducts());
		return "store.html";
	}
	
	@PostMapping("/storeProductDetails")
	public String details(@Validated Product product, BindingResult bindingResult, Model model)
	{
		model.addAttribute("product", productService.getProduct(product.getId()));
		return "productView.html";
	}
}

