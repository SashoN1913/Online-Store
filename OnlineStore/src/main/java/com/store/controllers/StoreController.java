package com.store.controllers;

import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.store.models.Product;
import com.store.services.ProductService;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StoreController
{
	@Autowired
	private ProductService productService;
	
	@GetMapping("/store")
	public String view(Model model)
	{
		model.addAttribute("products", productService.getProducts());
		return "store.html";
	}
	
//	@PostMapping("/productDetails")
//	public String details(@Validated Product product, BindingResult bindingResult, Model model)
//	{
//		model.addAttribute("product", productService.getProduct(product.getId()));
//		return "productView.html";
//	}

	@RequestMapping("/productDetails")
	public String articleDetail(@PathParam("id") Long id, Model model) {
		Product product = productService.getProduct(id);
		model.addAttribute("article", product);
		//model.addAttribute("notEnoughStock", model.asMap().get("notEnoughStock"));
		//model.addAttribute("addArticleSuccess", model.asMap().get("addArticleSuccess"));
		return "productDetails";
	}
}

