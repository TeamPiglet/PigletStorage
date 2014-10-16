package com.example.pigletstorage;

import java.util.ArrayList;

import com.example.models.Product;

import android.support.v7.app.ActionBarActivity;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductsFromServerActivity extends ActionBarActivity {
	Dialog dialog;

	ListView list;
	ArrayList<String> stringList;
	ArrayAdapter<String> adapter;
	MyReceiver myReceiver;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_products_from_server);
		stringList = new ArrayList<String>();
		adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, stringList);
		list = (ListView) findViewById(R.id.listProducts);
		list.setAdapter(adapter);

		myReceiver = new MyReceiver();

		IntentFilter intentFilter = new IntentFilter();
		intentFilter.addAction(FetchFromDBService.MY_ACTION);
		registerReceiver(myReceiver, intentFilter);

		Intent intent = new Intent(ProductsFromServerActivity.this,
				FetchFromDBService.class);
		startService(intent);

		dialog = new Dialog(ProductsFromServerActivity.this);
		dialog.setContentView(R.layout.waiting_popup);
		dialog.setTitle("Fetching from server");
		dialog.show();
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

	private class MyReceiver extends BroadcastReceiver {
		@Override
		public void onReceive(Context arg0, Intent arg1) {
			stringList.clear();
			adapter.notifyDataSetChanged();
			dialog.dismiss();
			ArrayList<?> datapassed = arg1
					.getCharSequenceArrayListExtra("DATAPASSED");
			ArrayList<String> names = new ArrayList<String>();
			for (Object product : datapassed) {
				names.add(((Product) product).getName());
			}
			stringList.addAll(names);
			adapter.notifyDataSetChanged();
		}
	}
}
