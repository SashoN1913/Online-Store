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
public class ProductController
{

	@Autowired
	private ProductService productService;
	
	@GetMapping("/productView")
	public String view(Model model)
	{
		model.addAttribute("products", productService.getProducts());
		return "productView.html";
	}

	@GetMapping("/productAdd")
	public String addProduct(Model model)
	{
		model.addAttribute("product", new Product());
		return "productAdd.html";
	}

	@PostMapping("/productAddUpdate")
	public String addNew(@Validated Product product, BindingResult bindingResult, Model model)
	{
		productService.addProduct(product);
		model.addAttribute("products", productService.getProducts());
		return "productView.html";
	}
	

	@PostMapping("/productEdit")
	public String edit(@Validated Product product, BindingResult bindingResult, Model model)
	{
		model.addAttribute("product", productService.getProduct(product.getId()));
		return "productEdit.html";
	}

	@PostMapping("/productUpdate")
	public String update(@Validated Product product, BindingResult bindingResult, Model model)
	{
		productService.updateProduct(product.getId(), product);
		model.addAttribute("products", productService.getProducts());
		return "productView.html";
	}

	@PostMapping("/productDelete")
	public String delete(@Validated Product product, BindingResult bindingResult, Model model)
	{
		productService.removeProduct(product.getId());
		model.addAttribute("products", productService.getProducts());
		return "productView.html";
	}

//	@RequestMapping("/delete")
//	public String deleteArticle(@RequestParam("id") int id)
//	{
//		productService.removeProduct(id);
//		model.addAttribute("products", productService.getProducts());
//		return "productsAdmin.html";
//	}
}
