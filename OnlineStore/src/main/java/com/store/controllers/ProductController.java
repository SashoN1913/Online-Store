package com.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping("/productsAdmin")
	public String admin(Model model)
	{
		model.addAttribute("products", productService.getProducts());
		return "productsAdmin.html";
	}
	
	@PostMapping("/addNew")
	public String addNew(@Validated Product product, BindingResult bindingResult, Model model)
	{
		productService.addProduct(product);
		model.addAttribute("products", productService.getProducts());
		return "index.html";
	}
	
	@PostMapping("/editForm")
	public String edit(@Validated Product product, BindingResult bindingResult, Model model)
	{
		System.out.println("edit - " + product.getId());
		model.addAttribute("product", productService.getProduct(product.getId()));
		return "editProduct.html";
	}
	
	@PostMapping("/update")
	public String update(@Validated Product product, BindingResult bindingResult, Model model)
	{
		System.out.println(product);
		productService.updateProduct(product.getId(), product);
		model.addAttribute("products", productService.getProducts());
		return "productsAdmin.html";
	}
	
	
	@PostMapping("/delete")
	public String delete(@Validated Product product, BindingResult bindingResult, Model model)
	{
		System.out.println("Delete - " + product.getId());
		productService.removeProduct(product.getId());
		model.addAttribute("products", productService.getProducts());
		return "productsAdmin.html";
	}
	
//	@RequestMapping("/delete")
//	public String deleteArticle(@RequestParam("id") int id)
//	{
//		productService.removeProduct(id);
//		model.addAttribute("products", productService.getProducts());
//		return "productsAdmin.html";
//	}
}
