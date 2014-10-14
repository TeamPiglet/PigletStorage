package com.example.models;

import java.util.UUID;

import com.telerik.everlive.sdk.core.model.base.DataItem;
import com.telerik.everlive.sdk.core.serialization.ServerIgnore;
import com.telerik.everlive.sdk.core.serialization.ServerProperty;
import com.telerik.everlive.sdk.core.serialization.ServerType;

@ServerType("Product")
public class Product extends DataItem {
	private UUID idTBS;
	@ServerIgnore
	private long idSQLite;	
	@ServerProperty("Name")
	private String name;
	@ServerProperty("Type")
	private String type;
	@ServerProperty("Price")
	private String price;
	@ServerProperty("Quantity")
	private String quantity;
	@ServerProperty("Image")
	private byte[] image;
	@ServerProperty("Longitude")
	private String longitude;
	@ServerProperty("Latitude")
	private String latitude;

	public long getIdSQLite() {
		return idSQLite;
	}

	public void setIdSQLite(long id) {
		this.idSQLite = id;
	}

	//for Telerik Backend
	public UUID getId() {
		return idTBS;
	}
	
	//for Telerik Backend
	public void setId(UUID id) {
		this.idTBS = id;
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
	
	public byte[] getImage() {
		return image;
	}

	public void setImage(byte[] image) {
		this.image = image;
	}	
	
	public String getLogitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	@Override
	public String toString() {
		return "Name: " + name 
				+ "\n  Price: "+ price 
				+ "\n  Type: " + type 
				+ "\n  Quantity " + quantity
				+ "\n  ID: " + idSQLite
				+ "\n  Longitude: " + longitude
				+ "\n  Latitude: " + latitude;
	}
}
