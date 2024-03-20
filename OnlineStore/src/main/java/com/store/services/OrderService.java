package com.store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.models.Order;
import com.store.repository.OrderRepository;

@Service
public class OrderService
{
	@Autowired
	private OrderRepository orderRepository;
	
	public List<Order> getOrders()
	{
		return orderRepository.findAll();
	}
	
	public Order getOrder(int id)
	{
		Optional<Order> o = orderRepository.findById(id);
		return o.get();
	}
	
	public void addOrder(Order p) {
		orderRepository.save(p);
	}
	
	public void removeOrder(int id) {
		orderRepository.deleteById(id);
	}

	
}
