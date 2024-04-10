package com.store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.models.OrderItem;
import com.store.repository.OrderItemRepository;

@Service
public class OrderItemService
{
	@Autowired
	private OrderItemRepository orderItemRepository;

	public List<OrderItem> getOrderItems()
	{
		return orderItemRepository.findAll();
	}

	public OrderItem getOrderItem(Long id)
	{
		Optional<OrderItem> item = orderItemRepository.findById(id);
		return item.get();
	}

	public List<OrderItem> getOrderItemsByOrderId(Long id)
	{
		List<OrderItem> items = orderItemRepository.findAll();
		return items.stream().filter(OrderItem -> OrderItem.getOrder().getId() == id).toList();
	}

	public void addOrder(OrderItem p)
	{
		orderItemRepository.save(p);
	}

	public void removeOrder(Long id)
	{
		orderItemRepository.deleteById(id);
	}

	public void updateOrder(Long id, OrderItem item)
	{
		item.setId(id);
		addOrder(item);
	}
}
