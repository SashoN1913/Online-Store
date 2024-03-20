package com.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.store.services.ProductService;


@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public String getProdusts(Model model)
	{
		System.out.println(productService.getProducts());
		model.addAttribute("products", productService.getProducts());
		
		return "index.html";
	}
}
