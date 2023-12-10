package mk.iwec.jdbc.mssql.domain;

import java.util.Objects;

public class Inventory {
	private String category;
	private Integer productId;
	private String productName;
	private double price;
	private Integer quantity;
	

	
    public Inventory() {
    }

   
    public Inventory(Integer productId,String category, String productName, double price, Integer quantity) {
        this.productId = productId;
        this.category = category;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    
    public Inventory(String category,String productName, double price, Integer quantity) {
    	this.category = category;
    	this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

	
	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	@Override
	public int hashCode() {
		return Objects.hash(productId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Inventory other = (Inventory) obj;
		return Objects.equals(productId, other.productId);
	}

	@Override
	public String toString() {
		return "Inventory : Product ID = " + productId +", Category = " + category + ", Product = " + productName + ", Price = " 
	+ price + ", Quantity = " + quantity;
	}

}
