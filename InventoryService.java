package com.inventory;

import java.util.HashMap;
import java.util.Map;

public class InventoryService {

	private Map<Integer, Product> products = new HashMap<>();

	// Add Product

	public void addProduct(Product product) {
		if (product == null) {
			throw new IllegalArgumentException("Product cannot be null");
		}

		int productId = product.getProductId();

		if (products.containsKey(productId)) {
			throw new IllegalArgumentException("Product ID already exists");
		}

		products.put(productId, product);

		System.out.println("Product added successfully");
	}

	// Search Product by ID

	public Product searchProductById(int productId) {
		Product product = products.get(productId);

		if (product == null) {
			throw new IllegalArgumentException("Product not found");
		}

		return product;
	}

	// Update Product Price

	public void updateProductPrice(int productId, double newPrice) {
		Product product = searchProductById(productId);

		product.setPrice(newPrice);

		System.out.println("Product price updated successfully");
	}

	// Add Stock

	public void addStock(int productId, int quantity) {
		Product product = searchProductById(productId);

		product.addStock(quantity);

		System.out.println("Stock added successfully");
	}

	// Sell One Product

	public void sellProduct(int productId, int quantity) {
		Product product = searchProductById(productId);

		product.sellProduct(quantity);

		System.out.println("Product sold successfully");
	}

	// Show All Products

	public void showAllProducts() {
		if (products.isEmpty()) {
			System.out.println("Inventory is empty");
			return;
		}

		for (Product product : products.values()) {
			System.out.println("------------------------------");
			System.out.println("ID       : " + product.getProductId());
			System.out.println("Name     : " + product.getProductName());
			System.out.println("Category : " + product.getCategory());
			System.out.printf("Price    : %.2f%n", product.getPrice());
			System.out.println("Stock    : " + product.getStock());
		}

		System.out.println("------------------------------");
	}

	// Show Products with Stock Below Minimum

	public void showLowStock(int minimumStock) {
		boolean found = false;

		for (Product product : products.values()) {
			if (product.getStock() < minimumStock) {
				System.out.println("------------------------------");
				System.out.println("ID    : " + product.getProductId());
				System.out.println("Name  : " + product.getProductName());
				System.out.println("Stock : " + product.getStock());

				found = true;
			}
		}

		if (!found) {
			System.out.println("No low-stock products found");
		}
	}

	// Generate Bill for Multiple Products

	public Bill generateMultipleProductBill(Map<Integer, Integer> cart) {

		if (cart == null || cart.isEmpty()) {
			throw new IllegalArgumentException("Cart cannot be empty");
		}

		// First, validate every product and quantity
		// No stock is deducted in this loop

		for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
			Integer productId = entry.getKey();
			Integer quantity = entry.getValue();

			if (productId == null || quantity == null) {
				throw new IllegalArgumentException("Product ID and quantity cannot be null");
			}

			Product product = searchProductById(productId);

			if (quantity <= 0) {
				throw new IllegalArgumentException("Quantity must be greater than zero");
			}

			if (quantity > product.getStock()) {
				throw new IllegalArgumentException("Insufficient stock for " + product.getProductName());
			}
		}

		// After all validations pass, process the sale

		Bill bill = new Bill();

		for (Map.Entry<Integer, Integer> entry : cart.entrySet()) {
			int productId = entry.getKey();
			int quantity = entry.getValue();

			Product product = searchProductById(productId);

			product.sellProduct(quantity);
			bill.addItem(product, quantity);
		}

		System.out.println("Bill generated successfully");

		return bill;
	}

	// Delete Product

	public void deleteProduct(int productId) {
		Product product = products.remove(productId);

		if (product == null) {
			throw new IllegalArgumentException("Product not found");
		}

		System.out.println("Product deleted successfully");
	}
}