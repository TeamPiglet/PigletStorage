package com.example.TelerikBackend;

import java.util.ArrayList;
import java.util.List;

import com.example.models.Product;
import com.telerik.everlive.sdk.core.EverliveApp;
import com.telerik.everlive.sdk.core.facades.read.GetByFilterFacade;
import com.telerik.everlive.sdk.core.result.RequestResult;

public class TelerikDataSource {
	static final String API_KEY = "15RD0gvz65GYsNwk";
	static final String LOGIN_NAME = "pigletstorage_user";
	static final String PASSWORD = "pigletstorage_password";
	
	EverliveApp everliveApp;
	
	public TelerikDataSource() {
		everliveApp = new EverliveApp(API_KEY);
		everliveApp.workWith()
				.authentication()
				.login(LOGIN_NAME, PASSWORD)
				.executeSync();
	}	
	
	public Boolean saveProduct(String name, String price, String type, String quantity, byte[] imageInByte, String longitude, String latitude) {
		Product product = new Product();
		product.setName(name);
		product.setPrice(price);
		product.setType(type);
		product.setQuantity(quantity);
		product.setImage(imageInByte);
		product.setLongitude(longitude);
		product.setLatitude(latitude);
		
		everliveApp.workWith().data(Product.class).create(product).executeAsync();
	
		return true;
	}
	
	public void deleteProduct(Product product) {	
		everliveApp.workWith().data(Product.class).deleteById(product.getId()).executeAsync();
	}
	
	public List<Product> getAllProducts() {		
		//must be in new thread
		//RequestResult request = everliveApp.workWith().data(Product.class).getAll().executeSync();
				
		return null;
	}
}
