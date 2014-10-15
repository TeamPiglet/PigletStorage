package com.example.pigletstorage;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ProductsFromServerActivity extends ActionBarActivity {

	ListView list;
	ArrayList<String> stringList;
	ArrayAdapter<String> adapter;
	MyReceiver myReceiver;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_products_from_server);
		stringList = new ArrayList<String>();		
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,stringList);
		list = (ListView)findViewById(R.id.listProducts);
		list.setAdapter(adapter);		
		
		myReceiver = new MyReceiver();
		
		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(FetchFromDBService.MY_ACTION);
		registerReceiver(myReceiver, intentFilter);
		
		Intent intent = new Intent(ProductsFromServerActivity.this, FetchFromDBService.class);
		startService(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.products_from_server, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onStop() {
		unregisterReceiver(myReceiver);
		super.onStop();		
	}
	
	private class MyReceiver extends BroadcastReceiver
	{
		@Override
		public void onReceive(Context arg0, Intent arg1) {			
			String datapassed = arg1.getStringExtra("DATAPASSED");						
			if(!stringList.contains(datapassed))
			{
				stringList.add(datapassed);				
				adapter.notifyDataSetChanged();
				Toast.makeText(
				        ProductsFromServerActivity.this,
				        "Successfully receive products!", Toast.LENGTH_LONG)
				        .show();
			}		
		}
	}
}
