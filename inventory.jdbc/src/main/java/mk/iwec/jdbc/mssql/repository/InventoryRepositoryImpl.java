package mk.iwec.jdbc.mssql.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import mk.iwec.jdbc.mssql.db.DB;
import mk.iwec.jdbc.mssql.domain.Inventory;

public class InventoryRepositoryImpl implements InventoryRepository {

	  private static final String SQL_SELECT = "SELECT product_id, category, product_name, price, quantity FROM inventory WHERE product_id = ?";
	  private static final String SQL_SELECT_ALL = "SELECT  product_id,category, product_name, price, quantity FROM inventory";
	  private static final String SQL_INSERT = "INSERT INTO inventory (product_id, category, product_name, price, quantity) VALUES (?, ?, ?, ?, ?)";
	  private static final String SQL_DELETE = "DELETE FROM inventory WHERE product_id = ?";
	  private static final String SQL_UPDATE = "UPDATE inventory SET category = ?, product_name = ?, price = ?, quantity = ? WHERE product_id = ?";
	  private static final String SQL_CHECK_EXISTENCE = "SELECT COUNT(*) FROM inventory WHERE product_name = ? AND category = ?";

	@Override
	public Inventory findByProductId(Integer productId) {
		if (productId == null) {
			return null;
		}

		Inventory result = null;
		try (DB db = new DB(); PreparedStatement ps = db.getConnection().prepareStatement(SQL_SELECT);) {
			ps.setInt(1, productId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				result = new Inventory( rs.getString("category"), rs.getString("product_name"),
						rs.getDouble("price"), rs.getInt("quantity"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<Inventory> findAll() {
	    List<Inventory> result = new LinkedList<>();
	    try (DB db = new DB();
	            Statement s = db.getConnection().createStatement();
	            ResultSet rs = s.executeQuery(SQL_SELECT_ALL);) {

	        while (rs.next()) {
	            result.add(new Inventory(rs.getInt("product_id"), rs.getString("category"), rs.getString("product_name"),
	                    rs.getDouble("price"), rs.getInt("quantity")));
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return result;
	}
	
	@Override
	public int insert(Inventory product) {
	    if (product == null) {
	        return 0;
	    }

	    // Check if a product with the same name and category already exists
	    if (isProductExists(product.getProductName(), product.getCategory())) {
	        System.out.println("Product with the same name and category already exists.");
	        return 0;
	    }

	    int affected = 0;
	    try (DB db = new DB(); PreparedStatement ps = db.getConnection().prepareStatement(SQL_INSERT);) {
	        ps.setInt(1, product.getProductId());
	        ps.setString(2, product.getCategory());
	        ps.setString(3, product.getProductName());
	        ps.setDouble(4, product.getPrice());
	        ps.setInt(5, product.getQuantity());
	        affected = ps.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return affected;
	}

	private boolean isProductExists(String productName, String category) {
	    try (DB db = new DB(); PreparedStatement ps = db.getConnection().prepareStatement(SQL_CHECK_EXISTENCE)) {
	        ps.setString(1, productName);
	        ps.setString(2, category);
	        ResultSet rs = ps.executeQuery();
	        if (rs.next()) {
	            int count = rs.getInt(1);
	            return count > 0;
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return false;
	}

	@Override
	public int deleteById(Integer productId) {
		if (productId == null) {
			return 0;
		}

		int affected = 0;
		try (DB db = new DB(); PreparedStatement ps = db.getConnection().prepareStatement(SQL_DELETE);) {
			ps.setInt(1, productId);
			affected = ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return affected;
	}

	@Override
	public int update(Inventory product) {
	    if (product == null) {
	        return 0;
	    }

	    try (Scanner scanner = new Scanner(System.in)) {
			int affected = 0;

			try (DB db = new DB(); PreparedStatement ps = db.getConnection().prepareStatement(SQL_UPDATE);) {
			    System.out.println("Choose a field to update: ");
			    System.out.println("1. Category");
			    System.out.println("2. Product Name");
			    System.out.println("3. Price");
			    System.out.println("4. Quantity");
			    System.out.println("5. All Fields");
			    System.out.println("6. Cancel");

			    System.out.print("Enter your choice: ");
			    int choice = scanner.nextInt();
			    scanner.nextLine();  

			    switch (choice) {
			        case 1:
			            System.out.print("Enter new category: ");
			            ps.setString(1, scanner.nextLine());
			            break;
			        case 2:
			            System.out.print("Enter new product name: ");
			            ps.setString(2, scanner.nextLine());
			            break;
			        case 3:
			            System.out.print("Enter new price: ");
			            ps.setDouble(3, scanner.nextDouble());
			            scanner.nextLine();  
			            break;
			        case 4:
			            System.out.print("Enter new quantity: ");
			            ps.setInt(4, scanner.nextInt());
			            scanner.nextLine();  
			            break;
			        case 5:
			            System.out.print("Enter new category: ");
			            ps.setString(1, scanner.nextLine());

			            System.out.print("Enter new product name: ");
			            ps.setString(2, scanner.nextLine());

			            System.out.print("Enter new price: ");
			            ps.setDouble(3, scanner.nextDouble());
			            scanner.nextLine();  

			            System.out.print("Enter new quantity: ");
			            ps.setInt(4, scanner.nextInt());
			            scanner.nextLine();  
			            break;
			        case 6:
			            System.out.println("Update canceled.");
			            return 0;
			        default:
			            System.out.println("Invalid choice. No fields updated.");
			            return 0;
			    }
			    ps.setInt(5, product.getProductId());

			    affected = ps.executeUpdate();
			    System.out.println("Product updated successfully!");
			} catch (SQLException e) {
			    e.printStackTrace();
			}

			return affected;
		}
	}
}
