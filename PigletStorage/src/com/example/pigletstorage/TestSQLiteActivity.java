package com.example.pigletstorage;


import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;

import com.example.SQLite.*;
import com.example.SQLite.models.*;


public class TestSQLiteActivity extends ListActivity {
	  private ProductDataSource datasource;

	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_test_sqlite);

	    datasource = new ProductDataSource(this);
	    datasource.open();

	    List<Product> values = datasource.getAllProducts();

	    // use the SimpleCursorAdapter to show the
	    // elements in a ListView
	    ArrayAdapter<Product> adapter = new ArrayAdapter<Product>(this,
	        android.R.layout.simple_list_item_1, values);
	    setListAdapter(adapter);
	  }

	  // Will be called via the onClick attribute
	  // of the buttons in main.xml
	  public void onClick(View view) {
	    @SuppressWarnings("unchecked")
	    ArrayAdapter<Product> adapter = (ArrayAdapter<Product>) getListAdapter();
	    Product product = null;
	    switch (view.getId()) {
	    case R.id.add:
	      int nextInt = new Random().nextInt(3);
	      product = datasource.createProduct("product"+nextInt, "price"+nextInt, "type"+nextInt, "quantity"+nextInt);
	      adapter.add(product);
	      break;
	    }
	    adapter.notifyDataSetChanged();
	  }

	  @Override
	  protected void onResume() {
	    datasource.open();
	    super.onResume();
	  }

	  @Override
	  protected void onPause() {
	    datasource.close();
	    super.onPause();
	  }

	} 