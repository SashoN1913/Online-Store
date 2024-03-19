package com.store.models;

public class Product {
	private int id;
	private String title;
	private int stock;
	private double price;
	

	public Product(){
	}


	public Product(int id, String title, int stock, double price) {
		super();
		this.id = id;
		this.title = title;
		this.stock = stock;
		this.price = price;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public int getStock() {
		return stock;
	}


	public void setStock(int stock) {
		this.stock = stock;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", title=" + title + ", stock=" + stock + ", price=" + price + "]";
	}
	
	
}
