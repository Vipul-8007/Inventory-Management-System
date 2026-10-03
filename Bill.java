package com.inventory;

import java.util.ArrayList;
import java.util.List;

public class Bill {

	// Stores one products details inside the Bill class
	
	private static class Item {
		private int productId;
		private String productName;
		private int quantity;
		private double price;
		private double totalAmount;

		public Item(int productId, String productName, int quantity, double price) {

			this.productId = productId;
			this.productName = productName;
			this.quantity = quantity;
			this.price = price;
			this.totalAmount = quantity * price;
		}
	}

	private List<Item> items = new ArrayList<>();

	// Add Product to Bill

	public void addItem(Product product, int quantity) {
		Item item = new Item(product.getProductId(), product.getProductName(), quantity, product.getPrice());

		items.add(item);
	}

	// Calculate Bill Total

	public double calculateTotal() {
		double total = 0;

		for (Item item : items) {
			total += item.totalAmount;
		}

		return total;
	}

	// Display Bill

	public void displayBill() {
		System.out.println("\n==================================");
		System.out.println("           CUSTOMER BILL");
		System.out.println("==================================");

		if (items.isEmpty()) {
			System.out.println("No products in bill.");
			return;
		}

		for (Item item : items) {
			System.out.println("Product ID   : " + item.productId);
			System.out.println("Product Name : " + item.productName);
			System.out.println("Quantity     : " + item.quantity);
			System.out.printf("Price        : %.2f%n", item.price);
			System.out.printf("Total Amount : %.2f%n", item.totalAmount);
			System.out.println("----------------------------------");
		}

		System.out.printf("Bill Total  : %.2f%n", calculateTotal());

		System.out.println("==================================");
	}
}