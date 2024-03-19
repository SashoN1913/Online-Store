package com.store.services;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.store.models.Product;

@Service
public class ProductService {
	
	private List<Product> products = Arrays.asList(
			new Product(1, "TV", 100, 1021.23),
			new Product(2, "PC", 10, 1021.23),
			new Product(3, "ATV", 14, 1021.23),
			new Product(4, "APC", 56, 1021.23)
			);

	
	public List<Product> getProducts() {
		return products;
	}

	
}
