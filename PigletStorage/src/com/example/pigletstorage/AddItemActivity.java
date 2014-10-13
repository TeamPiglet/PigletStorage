package com.example.pigletstorage;

import java.io.ByteArrayOutputStream;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.SQLite.ProductDataSource;

public class AddItemActivity extends ActionBarActivity implements
		OnSeekBarChangeListener {
	static final int REQUEST_IMAGE_CAPTURE = 1;

	String latitude = "";
	String longitude = "";

	private ImageView imageview;
	private Bitmap imageBitmap;

	private Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.add_item);

		imageview = (ImageView) findViewById(R.id.image);
		imageview.setVisibility(View.GONE);

		SeekBar sb = (SeekBar) findViewById(R.id.slider);
		sb.setMax(100);
		sb.setOnSeekBarChangeListener(this);

		LocationManager locationManager = (LocationManager) this
				.getSystemService(Context.LOCATION_SERVICE);
		
		LocationListener li = new PigletLocationListener();

		locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0,
				0, li);
	}

	public void getCameraPhoto(View v) {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
			startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
		}
	}

	public void uploadPhoto(View v) {
		printMessage("Not implemented!");
	}

	public void getLocation(View v) {
		if(longitude.equals("") || latitude.equals(""))
		{
			printMessage("Something wrong with retrieving location");
			return;
		}

		printMessage("Succesfully retrieved location");
	}

	public void saveProduct(View v) {
		if (imageBitmap == null) {
			printMessage("You must add a photo");
			return;
		}

		String name = ((EditText) findViewById(R.id.product_name_preview))
				.getText().toString();

		if (name.equals("")) {
			printMessage("You must add a name");
			return;
		}

		String type = ((EditText) findViewById(R.id.product_type_preview))
				.getText().toString();

		if (type.equals("")) {
			printMessage("You must add a type");
			return;
		}

		String price = ((EditText) findViewById(R.id.product_price_preview))
				.getText().toString();

		if (price.equals("")) {
			printMessage("You must add a price");
			return;
		}
		
		if(longitude.equals("") || latitude.equals(""))
		{
			printMessage("Something wrong with retrieving location");
			return;
		}

		String quantity = ((TextView) findViewById(R.id.product_quantity_current))
				.getText().toString();

		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
		byte imageInByte[] = stream.toByteArray();

		ProductDataSource datasource = new ProductDataSource(this);
		datasource.open();
		datasource.createProduct(name, price, type, quantity, imageInByte, longitude, latitude);
		datasource.close();
		
		printMessage("Succesfully saved.");
	}

	private void printMessage(String message) {
		Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		toast.show();
		return;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
			Bundle extras = data.getExtras();
			imageBitmap = (Bitmap) extras.get("data");
			imageview.setImageBitmap(imageBitmap);
			imageview.setVisibility(View.VISIBLE);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.add_item, menu);
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
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		TextView tv = (TextView) findViewById(R.id.product_quantity_current);
		tv.setText(Integer.toString(progress));
	}

	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
	}

	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
	}

	public class PigletLocationListener implements LocationListener {

		@Override
		public void onLocationChanged(Location location) {
			longitude = String.valueOf(location.getLongitude());
			latitude = String.valueOf(location.getLatitude());

		}

		@Override
		public void onProviderDisabled(String provider) {
		}

		@Override
		public void onProviderEnabled(String provider) {
		}

		@Override
		public void onStatusChanged(String provider, int status, Bundle extras) {
		}
	}
}
