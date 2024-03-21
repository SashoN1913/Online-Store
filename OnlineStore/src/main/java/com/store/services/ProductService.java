package com.store.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.store.models.Product;
import com.store.repository.ProductRepository;

@Service
public class ProductService
{
	@Autowired
	private ProductRepository productRepository;

	public List<Product> getProducts()
	{
		return productRepository.findAll();
	}

	public Product getProduct(int id)
	{
		Optional<Product> p = productRepository.findById(id);
		return p.get();
	}

	public void addProduct(Product p)
	{
		productRepository.save(p);
	}

	public void removeProduct(int id)
	{
		productRepository.deleteById(id);
	}

	public void updateProduct(int id, Product p)
	{
		p.setId(id);
		addProduct(p);
	}
}
