package com.inventory;

import java.util.*;

public class Main {

	public static void main(String[] args) {

		InventoryService service = new InventoryService();
		Scanner sc = new Scanner(System.in);

		Bill lastBill = null;

		// Products

		service.addProduct(new Product(101, "Laptop", Category.LAPTOP, 55000, 10));

		service.addProduct(new Product(102, "Mobile", Category.MOBILE, 20000, 15));

		service.addProduct(new Product(103, "Mouse", Category.ACCESSORY, 500, 3));

		int choice;

		do {
			System.out.println("\n===== INVENTORY MANAGEMENT SYSTEM =====");
			System.out.println("1. Add Product");
			System.out.println("2. Search Product");
			System.out.println("3. Update Product Price");
			System.out.println("4. Add Stock");
			System.out.println("5. Sell Product");
			System.out.println("6. Show All Products");
			System.out.println("7. Show Low Stock");
			System.out.println("8. Generate Bill");
			System.out.println("9. Delete Product");
			System.out.println("0. Exit");

			System.out.print("Enter your choice : ");
			choice = sc.nextInt();

			switch (choice) {

			// Add Product

			case 1: {
				System.out.print("Enter Product ID : ");
				int productId = sc.nextInt();
				sc.nextLine();

				System.out.print("Enter Product Name : ");
				String productName = sc.nextLine();

				System.out.println("1. LAPTOP");
				System.out.println("2. MOBILE");
				System.out.println("3. ACCESSORY");
				System.out.println("4. HOME_APPLIANCE");

				System.out.print("Choose Category : ");
				int categoryChoice = sc.nextInt();

				if (categoryChoice < 1 || categoryChoice > 4) {
					System.out.println("Invalid category choice");
					break;
				}

				Category category = Category.values()[categoryChoice - 1];

				System.out.print("Enter Price : ");
				double price = sc.nextDouble();

				System.out.print("Enter Stock : ");
				int stock = sc.nextInt();

				try {
					Product product = new Product(productId, productName, category, price, stock);

					service.addProduct(product);

				} catch (IllegalArgumentException e) {
					System.out.println("Error : " + e.getMessage());
				}

				break;
			}

			// Search Product

			case 2: {
				System.out.print("Enter Product ID to search : ");
				int searchId = sc.nextInt();

				try {
					Product foundProduct = service.searchProductById(searchId);

					System.out.println("Product ID	: " + foundProduct.getProductId());
					System.out.println("Product Name	: " + foundProduct.getProductName());
					System.out.println("Category	: " + foundProduct.getCategory());
					System.out.println("Price		: " + foundProduct.getPrice());
					System.out.println("Stock		: " + foundProduct.getStock());

				} catch (IllegalArgumentException e) {
					System.out.println("Error : " + e.getMessage());
				}

				break;
			}

			// Update Product Price

			case 3: {
				System.out.print("Enter Product ID: ");
				int updateId = sc.nextInt();

				System.out.print("Enter New Price: ");
				double newPrice = sc.nextDouble();

				try {
					service.updateProductPrice(updateId, newPrice);

				} catch (IllegalArgumentException e) {
					System.out.println("Error : " + e.getMessage());
				}

				break;
			}

			// Add Stock

			case 4: {
				System.out.print("Enter Product ID : ");
				int stockId = sc.nextInt();

				System.out.print("Enter Quantity to Add : ");
				int addQuantity = sc.nextInt();

				try {
					service.addStock(stockId, addQuantity);

				} catch (IllegalArgumentException e) {
					System.out.println("Error : " + e.getMessage());
				}

				break;
			}

			// Sell Products

			case 5: {
				Map<Integer, Integer> cart = new HashMap<>();

				while (true) {
					System.out.print("Enter Product ID (0 to finish) : ");

					int cartProductId = sc.nextInt();

					if (cartProductId == 0) {
						break;
					}

					try {
						service.searchProductById(cartProductId);

					} catch (IllegalArgumentException e) {
						System.out.println("Error : " + e.getMessage());
						continue;
					}

					System.out.print("Enter Quantity : ");
					int quantity = sc.nextInt();

					if (quantity <= 0) {
						System.out.println("Quantity must be greater than zero");
						continue;
					}

					cart.merge(cartProductId, quantity, Integer::sum);
				}

				if (!cart.isEmpty()) {
					try {
						lastBill = service.generateMultipleProductBill(cart);

						System.out.println("Products sold successfully");
						System.out.println("Select Option 8 to view the bill");

					} catch (IllegalArgumentException e) {
						System.out.println("Error : " + e.getMessage());
					}
				} else {
					System.out.println("No products selected");
				}

				break;
			}

			// Show All Products

			case 6:
				service.showAllProducts();
				break;

			// Show Low Stock

			case 7:
				service.showLowStock(5);
				break;

			// Display Saved Bill

			case 8:
				if (lastBill != null) {
					lastBill.displayBill();
				} else {
					System.out.println("No bill generated yet \nPlease sell products first");
				}

				break;

			// Delete Product

			case 9: {
				System.out.print("Enter Product ID to delete : ");
				int deleteId = sc.nextInt();

				try {
					service.deleteProduct(deleteId);

				} catch (IllegalArgumentException e) {
					System.out.println("Error : " + e.getMessage());
				}

				break;
			}

			// Exit

			case 0:
				System.out.println("Exiting application...");
				break;

			default:
				System.out.println("Invalid choice");
			}

		} while (choice != 0);

		sc.close();
	}
}