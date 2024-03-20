package com.store.controllers;

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
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/products")
	public String getProdusts(Model model)
	{
		model.addAttribute("products", productService.getProducts());
		
		return "index.html";
	}
	
	@GetMapping("/addProduct")
	public String addProduct(Model model)
	{
		model.addAttribute("product", new Product());
		
		return "addProduct.html";
	}
	
	@PostMapping("/addNew")
	public String addNew(@Validated Product product, BindingResult bindingResult, Model model)
	{
		productService.addProduct(product);
		model.addAttribute("products", productService.getProducts());
		return "index.html";
	}
}
