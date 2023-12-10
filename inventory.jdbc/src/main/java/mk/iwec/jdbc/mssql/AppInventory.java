package mk.iwec.jdbc.mssql;

import java.util.List;
import java.util.Scanner;
import mk.iwec.jdbc.mssql.domain.Inventory;
import mk.iwec.jdbc.mssql.repository.InventoryRepository;
import mk.iwec.jdbc.mssql.repository.InventoryRepositoryImpl;


// TODO implement it as Menu, use Scanner for input
public class AppInventory {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		InventoryRepository repo = new InventoryRepositoryImpl();
		
		while (true) {
            System.out.println("Menu:");
            System.out.println("1. Find Product by Product ID");
            System.out.println("2. List all Products");
            System.out.println("3. Add Product");
            System.out.println("4. Update Product");
            System.out.println("5. Delete Product");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    findProductById(scanner, repo);
                    break;
                case 2:
                    listAllProducts(repo);
                    break;
                case 3:
                    addProduct(scanner, repo);
                    break;
                case 4:
                    updateProduct(scanner, repo);
                    break;
                case 5:
                    deleteProduct(scanner, repo);
                    break;
                case 6:
                    System.out.println("Exiting the program. Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }

    private static void findProductById(Scanner scanner, InventoryRepository repo) {
        System.out.print("Enter Product ID to find: ");
        Integer productId = scanner.nextInt();
        scanner.nextLine(); 

        Inventory foundProduct = repo.findByProductId(productId);

        if (foundProduct != null) {
            System.out.println("Product found: " + foundProduct);
        } else {
            System.out.println("Product not found for ID: " + productId);
        }
    }

    private static void listAllProducts(InventoryRepository repo) {
        List<Inventory> products = repo.findAll();
        if (products.isEmpty()) {
            System.out.println("No products found.");
        } else {
            System.out.println("List of Products:");
            for (Inventory product : products) {
                System.out.println(product);
            }
        }
    }

    private static void addProduct(Scanner scanner, InventoryRepository repo) {
        System.out.println("Enter product details:");
        System.out.print("Product ID: ");
        Integer productId = scanner.nextInt();
        scanner.nextLine();  
        System.out.print("Category: ");
        String category = scanner.nextLine();
        System.out.print("Product Name: ");
        String productName = scanner.nextLine();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        System.out.print("Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();  

        int result = repo.insert(new Inventory(productId, category, productName, price, quantity));
        if (result > 0) {
            System.out.println("Product added successfully!");
        } else {
            System.out.println("Failed to add the product.");
        }
    }

    private static void updateProduct(Scanner scanner, InventoryRepository repo) {
        System.out.print("Enter Product ID to update: ");
        Integer productId = scanner.nextInt();
        scanner.nextLine();  

        Inventory existingProduct = repo.findByProductId(productId);

        if (existingProduct != null) {
            System.out.println("Enter new product details:");
            System.out.print("Category: ");
            String category = scanner.nextLine();
            System.out.print("Product Name: ");
            String productName = scanner.nextLine();
            System.out.print("Price: ");
            double price = scanner.nextDouble();
            System.out.print("Quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();  

            Inventory updatedProduct = new Inventory(productId, category, productName, price, quantity);
            int result = repo.update(updatedProduct);

            if (result > 0) {
                System.out.println("Product updated successfully!");
            } else {
                System.out.println("Failed to update the product.");
            }
        } else {
            System.out.println("Product not found for ID: " + productId);
        }
    }

    private static void deleteProduct(Scanner scanner, InventoryRepository repo) {
        System.out.print("Enter Product ID to delete: ");
        Integer productId = scanner.nextInt();
        scanner.nextLine();  

        int result = repo.deleteById(productId);

        if (result > 0) {
            System.out.println("Product deleted successfully!");
        } else {
            System.out.println("Failed to delete the product.");
        }
    }
}

