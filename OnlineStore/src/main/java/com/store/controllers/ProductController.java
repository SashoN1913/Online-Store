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
	
	//@Autowired
	//private ProductService productService;
	
	@RequestMapping("/")
	public String getProdusts(Model model)
	{
		List<Product> products = Arrays.asList(
				new Product(1, "TV", 100, 1021.23),
				new Product(2, "PC", 10, 1021.23),
				new Product(3, "ATV", 14, 1021.23),
				new Product(4, "APC", 56, 1021.23)
				);
		
		model.addAttribute("products", products);
		
		return "index.html";
	}
	

}
