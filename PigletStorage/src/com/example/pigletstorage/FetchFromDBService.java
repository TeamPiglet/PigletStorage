package com.example.pigletstorage;

import java.util.ArrayList;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import com.example.models.Product;
import com.telerik.everlive.sdk.core.EverliveApp;
import com.telerik.everlive.sdk.core.result.RequestResult;

public class FetchFromDBService extends Service
{
	static final String API_KEY = "15RD0gvz65GYsNwk";
	static final String LOGIN_NAME = "pigletstorage_user";
	static final String PASSWORD = "pigletstorage_password";
	final static String MY_ACTION = "MY_ACTION";
	EverliveApp app;
	
	@Override
	public void onCreate() {
		super.onCreate();
		app = new EverliveApp(API_KEY);
		app.workWith().authentication().login(LOGIN_NAME, PASSWORD)
		        .executeSync();
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		Check test = new Check();
		test.start();
		return super.onStartCommand(intent, flags, startId);
	}
	
	private class Check extends Thread
	{
		@Override
		public void run() {
			super.run();
			while (true) {
				try {
					Thread.sleep(100);
					Intent intent = new Intent();
					intent.setAction(MY_ACTION);
					
					RequestResult allItems = app.workWith().data(Product.class)
					        .getAll().executeSync();
					
					if (allItems.getSuccess()) {
						ArrayList products = (ArrayList) allItems.getValue();
						for (Object item : products) {
							Product product = (Product) item;
							intent.putExtra("DATAPASSED", product.getName());
						}
					}
					
					sendBroadcast(intent);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		
	}
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public void onDestroy() {
		super.onDestroy();
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onStart(Intent intent, int startId) {
		super.onStart(intent, startId);
	}
}
