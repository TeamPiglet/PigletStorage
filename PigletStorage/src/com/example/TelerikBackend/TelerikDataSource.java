package com.example.TelerikBackend;

import java.util.List;

import com.example.models.Product;
import com.telerik.everlive.sdk.core.EverliveApp;

public class TelerikDataSource {
	static final String API_KEY = "15RD0gvz65GYsNwk";
	static final String LOGIN_NAME = "pigletemail@abv.bg";
	static final String PASSWORD = "pigletemailpas";
	
	EverliveApp everliveApp;
	
	public TelerikDataSource() {
		everliveApp = new EverliveApp(API_KEY);
		everliveApp.workWith()
				.authentication()
				.login(LOGIN_NAME, PASSWORD)
				.executeSync();
	}	
	
	public Boolean saveProduct(String name, String price, String type, String quantity, byte[] imageInByte, String longitude, String latitude) {
		return true;
	}
	
	public void deleteProduct(Product product) {		
	}
	
	public List<Product> getAllProducts() {		
		return null;
	}
}
