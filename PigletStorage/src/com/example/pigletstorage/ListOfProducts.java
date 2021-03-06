package com.example.pigletstorage;

import java.util.List;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.SQLite.SQLiteDataSource;
import com.example.models.Product;

@SuppressLint("NewApi") 
public class ListOfProducts extends ActionBarActivity {

	ListView list;
	CustomAdapter adapter;
	public ListOfProducts CustomListView = null;
	private SQLiteDataSource datasource;
	public static List<Product> values;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);

		ActionBar ab = getActionBar();
		ab.setTitle("All Products");
		
		CustomListView = this;
		datasource = new SQLiteDataSource(CustomListView);
		datasource.open();

		values = datasource.getAllProducts();

		Resources res = getResources();
		list = (ListView) findViewById(R.id.listOfAllProducts); // List defined in XML

		/**************** Create Custom Adapter *********/
		adapter = new CustomAdapter(CustomListView, values, res);
		list.setAdapter(adapter);
    }	

	/****** Function to set data in ArrayList *************/

	public void onItemClick(int mPosition) {
		//Product tempValues = (Product) values.get(mPosition);

		Intent intent = new Intent(this, ProductDetailsActivity.class);
		intent.putExtra("mPosition", mPosition);
		startActivity(intent);
	
	}

	public static List<Product> getProducts(){
		return values;
	}
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
