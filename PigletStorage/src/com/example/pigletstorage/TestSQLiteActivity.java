package com.example.pigletstorage;


import java.util.List;
import java.util.Random;

import android.app.ListActivity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.SQLite.*;
import com.example.SQLite.models.*;


public class TestSQLiteActivity extends ListActivity {
	  private ProductDataSource datasource;
	  private Bitmap imageBitmap;
	  private ImageView imageview;

		private Context context = this;
		
	  @Override
	  public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_test_sqlite);

	    datasource = new ProductDataSource(this);
	    datasource.open();
	    List<Product> values = datasource.getAllProducts();

	    /*--------------Display image------------------*/
	    imageview  = (ImageView) findViewById(R.id.image);	    
	    byte[] imgInArr = values.get(0).getImage();
	    imageBitmap = BitmapFactory.decodeByteArray(imgInArr, 0, imgInArr.length);	     
        imageview.setImageBitmap(imageBitmap);
	    /*---------------------------------------------*/

	    ArrayAdapter<Product> adapter = new ArrayAdapter<Product>(this,
	        android.R.layout.simple_list_item_1, values);
	    setListAdapter(adapter);
	  }

	  public void onClick(View view) {
		  Toast toast = Toast.makeText(context, "Ve4e ne raboti", Toast.LENGTH_SHORT);
			toast.show();
	    /*@SuppressWarnings("unchecked")
	    ArrayAdapter<Product> adapter = (ArrayAdapter<Product>) getListAdapter();
	    Product product = null;
	    switch (view.getId()) {
	    case R.id.add:
	      int nextInt = new Random().nextInt(3);
	      product = datasource.createProduct("product"+nextInt, "price"+nextInt, "type"+nextInt, "quantity"+nextInt);
	      adapter.add(product);
	      break;
	    }
	    adapter.notifyDataSetChanged();*/
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