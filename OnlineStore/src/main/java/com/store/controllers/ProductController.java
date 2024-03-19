package com.store.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.store.models.Product;
import com.store.services.ProductService;


@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@RequestMapping("/")
	public String getProdusts(Model model)
	{
		
		model.addAttribute("products", productService.getProducts());
		
		return "index.html";
	}
	

}
