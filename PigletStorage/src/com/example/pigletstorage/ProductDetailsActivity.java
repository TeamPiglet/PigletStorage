package com.example.pigletstorage;

import com.example.models.Product;

import android.support.v7.app.ActionBarActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class ProductDetailsActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_details);
		
		Product product = (Product) getIntent().getExtras().getParcelable("product");
		
		ImageView image = (ImageView)findViewById(R.id.imageDetail);
		TextView name = (TextView)findViewById(R.id.nameValue);
		TextView type = (TextView)findViewById(R.id.typeValue);
		TextView price = (TextView)findViewById(R.id.priceValue);
		TextView quantity = (TextView)findViewById(R.id.quantityValue);

        Bitmap imageBitmap = BitmapFactory.decodeByteArray(product.getImage() , 0, product.getImage().length);
        
		image.setImageBitmap(imageBitmap);		
		name.setText(product.getName());
		type.setText(product.getType());
		price.setText(product.getPrice());
		quantity.setText(product.getQuantity());
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.product_details, menu);
		return true;
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
