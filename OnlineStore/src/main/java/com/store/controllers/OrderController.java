package com.store.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.store.services.OrderService;

@Controller
public class OrderController
{

	@Autowired
	private OrderService orderService;

	@GetMapping("/orders")
	public String getOrders(Model model)
	{
		System.out.println(orderService.getOrders());
		model.addAttribute("orders", orderService.getOrders());

		return "orders.html";
	}
}
