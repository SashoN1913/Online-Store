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
		Optional<Order> order = orderRepository.findById(id);
		return order.get();
	}

	public void addOrder(Order order)
	{
		orderRepository.save(order);
	}

	public void removeOrder(int id)
	{
		orderRepository.deleteById(id);
	}

	public void updateOrder(int id, Order order)
	{
		order.setId(id);
		addOrder(order);
	}
}
