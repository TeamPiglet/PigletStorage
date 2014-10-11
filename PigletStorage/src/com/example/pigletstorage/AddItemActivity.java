package com.example.pigletstorage;

import com.example.SQLite.ProductDataSource;

import android.support.v7.app.ActionBarActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class AddItemActivity extends ActionBarActivity implements OnSeekBarChangeListener {

	private Context context = this;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_item);
		
		SeekBar sb = (SeekBar)findViewById(R.id.slider);
		sb.setMax(100);
		sb.setOnSeekBarChangeListener(this);
	}
	
	public void getCameraPhoto(View v) {
		Toast toast = Toast.makeText(context, "Not implemented!", Toast.LENGTH_SHORT);
		toast.show();
	}
	
	public void uploadPhoto(View v) {
		Toast toast = Toast.makeText(context, "Not implemented!", Toast.LENGTH_SHORT);
		toast.show();
	}

	public void getLocation(View v) {
		Toast toast = Toast.makeText(context, "Not implemented!", Toast.LENGTH_SHORT);
		toast.show();
	}
	
	public void saveProduct(View v) {
		String name = ((EditText)findViewById(R.id.product_name_preview)).getText().toString();
		String type = ((EditText)findViewById(R.id.product_type_preview)).getText().toString();
		String price = ((EditText)findViewById(R.id.product_price_preview)).getText().toString();
		String quantity = ((TextView)findViewById(R.id.product_quantity_current)).getText().toString();
		String width = "";
		String height = "";
		
		ProductDataSource datasource = new ProductDataSource(this);
	    datasource.open();
		datasource.createProduct(name, price, type, quantity);
	      
		
		Toast toast = Toast.makeText(context, "Succesfully saved.", Toast.LENGTH_SHORT);
		toast.show();     
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.add_item, menu);
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

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		TextView tv = (TextView)findViewById(R.id.product_quantity_current);
		tv.setText(Integer.toString(progress));		
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub
		
	}
}
