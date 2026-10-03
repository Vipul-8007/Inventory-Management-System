package com.inventory;

public class Product {

	private int productId;
	private String productName;
	private Category category;
	private double price;
	private int stock;

	public Product(int productId, String productName, Category category, double price, int stock) {

		if (productId <= 0) {
			throw new IllegalArgumentException("Product ID must be greater than zero");
		}

		if (productName == null || productName.trim().isEmpty()) {
			throw new IllegalArgumentException("Product name cannot be empty");
		}

		if (category == null) {
			throw new IllegalArgumentException("Category cannot be null");
		}

		if (price <= 0) {
			throw new IllegalArgumentException("Price must be greater than zero");
		}

		if (stock < 0) {
			throw new IllegalArgumentException("Stock cannot be negative");
		}

		this.productId = productId;
		this.productName = productName;
		this.category = category;
		this.price = price;
		this.stock = stock;
	}

	// Getters

	public int getProductId() {
		return productId;
	}

	public String getProductName() {
		return productName;
	}

	public Category getCategory() {
		return category;
	}

	public double getPrice() {
		return price;
	}

	public int getStock() {
		return stock;
	}

	// Update Price

	public void setPrice(double price) {
		if (price <= 0) {
			throw new IllegalArgumentException("Price must be greater than zero");
		}

		this.price = price;
	}

	// Add Stock

	public void addStock(int quantity) {
		if (quantity <= 0) {
			throw new IllegalArgumentException("Quantity must be greater than zero");
		}

		stock += quantity;
	}

	// Sell Product

	public void sellProduct(int quantity) {
		if (quantity <= 0) {
			throw new IllegalArgumentException("Quantity must be greater than zero");
		}

		if (stock == 0) {
			throw new IllegalArgumentException("Product is out of stock");
		}

		if (quantity > stock) {
			throw new IllegalArgumentException("Insufficient stock");
		}

		stock -= quantity;
	}
}