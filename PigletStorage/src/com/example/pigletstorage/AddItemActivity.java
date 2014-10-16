package com.example.pigletstorage;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
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

import com.example.SQLite.SQLiteDataSource;
import com.example.TelerikBackend.TelerikDataSource;

public class AddItemActivity extends ActionBarActivity implements
		OnSeekBarChangeListener {
	static final int REQUEST_IMAGE_CAPTURE = 1;
	static final int PICK_FROM_GALLERY = 2;

	String latitude = "";
	String longitude = "";

	private ImageView imageview;
	private Bitmap imageBitmap;
	private Notifications notifications;

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

		notifications = new Notifications();
	}

	public void getCameraPhoto(View v) {
		Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
		if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
			startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
		}
	}

	public void uploadPhoto(View v) {
		Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
		photoPickerIntent.setType("image/*");
		startActivityForResult(photoPickerIntent, PICK_FROM_GALLERY);
	}

	public void getLocation(View v) {
		if (longitude.equals("") || latitude.equals("")) {
			printMessage("Something wrong with retrieving location");
			return;
		}

		printMessage("Succesfully retrieved location");
	}

	public void saveProduct(View v) {
		String name = ((EditText) findViewById(R.id.product_name_preview))
				.getText().toString();
		String type = ((EditText) findViewById(R.id.product_type_preview))
				.getText().toString();
		String price = ((EditText) findViewById(R.id.product_price_preview))
				.getText().toString();
		String quantity = ((TextView) findViewById(R.id.product_quantity_current))
				.getText().toString();
		

		if (!chekInputData(name, type, price)) {
			return;
		}
		byte[] imageInByte = getImageInByteArray();
		
		String networkType = Connection.getNetworkClass(this);
		if(networkType == null) {
			printMessage("You are not connected to internet!");
			return;
		}

		if (!saveItemToSQLite(name, price, type, quantity, imageInByte,
				longitude, latitude)) {
			printMessage("Error while saving in SQLite!");
			return;
		}

		if (!saveItemToTelerikBackend(name, price, type, quantity, imageInByte,
				longitude, latitude)) {
			printMessage("Error while saving in Telerik Backend Services!");
			return;
		}

		notifications.createAddedNewItemNotification(this, imageBitmap, name,
				price, type, quantity);

		printMessage("Succesfully saved via " + networkType + "!");
	}

	private byte[] getImageInByteArray() {
		ByteArrayOutputStream stream = new ByteArrayOutputStream();
		imageBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
		byte imageInByte[] = stream.toByteArray();

		return imageInByte;
	}

	private Boolean saveItemToSQLite(String name, String price, String type,
			String quantity, byte[] imageInByte, String longitude,
			String latitude) {
		SQLiteDataSource datasource = new SQLiteDataSource(this);
		datasource.open();
		long result = datasource.createProduct(name, price, type, quantity,
				imageInByte, longitude, latitude);
		datasource.close();

		return result != -1;
	}

	private Boolean saveItemToTelerikBackend(String name, String price,
			String type, String quantity, byte[] imageInByte, String longitude,
			String latitude) {
		TelerikDataSource dataSource = new TelerikDataSource();
		Boolean result = dataSource.saveProduct(name, price, type, quantity,
				imageInByte, longitude, latitude);

		return result;
	}

	private Boolean chekInputData(String name, String type, String price) {
		if (imageBitmap == null) {
			printMessage("You must add a photo");
			return false;
		}

		if (name.equals("")) {
			printMessage("You must add a name");
			return false;
		}

		if (type.equals("")) {
			printMessage("You must add a type");
			return false;
		}

		if (price.equals("")) {
			printMessage("You must add a price");
			return false;
		}

		if (longitude.equals("") || latitude.equals("")) {
			printMessage("Something wrong with retrieving location");
			return false;
		}

		return true;
	}

	private void printMessage(String message) {
		Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
		toast.show();
		return;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode != RESULT_OK) {
			return;
		}
		imageBitmap = null;

		switch (requestCode) {
		case REQUEST_IMAGE_CAPTURE:
			Bundle extras = data.getExtras();
			
			imageBitmap = (Bitmap) extras.get("data");
			imageview.setImageBitmap(imageBitmap);
			imageview.setVisibility(View.VISIBLE);
			
			break;
		case PICK_FROM_GALLERY:
			Uri selectedImage = data.getData();
			InputStream imageStream;
			
			try {
				imageStream = getContentResolver().openInputStream(
						selectedImage);
				imageBitmap = BitmapFactory.decodeStream(imageStream);
			} catch (FileNotFoundException e) {
				printMessage("File not found!");
				e.printStackTrace();
			}
			
			imageview.setImageBitmap(imageBitmap);
			imageview.setVisibility(View.VISIBLE);
			
			break;
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
