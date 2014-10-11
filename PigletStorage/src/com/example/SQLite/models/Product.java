package com.example.SQLite.models;

public class Product {
	private long id;
	private String name;
	private String type;
	private String price;
	private String quantity;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}	
	
	// Will be used by the ArrayAdapter in the ListView
	@Override
	public String toString() {
		return "Name: " + name 
				+ "\n  Price: "+ price 
				+ "\n  Type: " + type 
				+ "\n  Quantity " + quantity;
	}
}
