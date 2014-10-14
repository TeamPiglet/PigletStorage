package com.example.SQLite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.example.models.*;

public class SQLiteDataSource {
	private SQLiteDatabase database;
	private SQLiteHelper dbHelper;
	private String[] allColumns = { SQLiteHelper.COLUMN_ID,
			SQLiteHelper.COLUMN_NAME, SQLiteHelper.COLUMN_PRICE,
			SQLiteHelper.COLUMN_TYPE, SQLiteHelper.COLUMN_QUANTITY, 
			SQLiteHelper.COLUMN_IMAGE, SQLiteHelper.COLUMN_LONGITUDE,
			SQLiteHelper.COLUMN_LATITUDE};

	public SQLiteDataSource(Context context) {
		dbHelper = new SQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public long createProduct(String name, String price, String type,
			String quantity, byte[] image, String longitude, String latitude) {
		ContentValues values = new ContentValues();
		values.put(SQLiteHelper.COLUMN_NAME, name);
		values.put(SQLiteHelper.COLUMN_PRICE, price);
		values.put(SQLiteHelper.COLUMN_TYPE, type);
		values.put(SQLiteHelper.COLUMN_QUANTITY, quantity);
		values.put(SQLiteHelper.COLUMN_IMAGE, image);
		values.put(SQLiteHelper.COLUMN_LONGITUDE, longitude);
		values.put(SQLiteHelper.COLUMN_LATITUDE, latitude);

		long result = database.insert(SQLiteHelper.TABLE_PRODUCTS, null,
				values);
		
		return result;
		/*Cursor cursor = database.query(SQLiteHelper.TABLE_PRODUCTS, allColumns,
				SQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null,
				null);*/		
		/*cursor.moveToFirst();
		Product newProduct = cursorToProduct(cursor);
		cursor.close();
		return newProduct;*/
	}

	public void deleteProduct(Product product) {
		long id = product.getIdSQLite();
		System.out.println("Comment deleted with id: " + id);
		database.delete(SQLiteHelper.TABLE_PRODUCTS, SQLiteHelper.COLUMN_ID
				+ " = " + id, null);
	}

	public List<Product> getAllProducts() {
		List<Product> products = new ArrayList<Product>();

		Cursor cursor = database.query(SQLiteHelper.TABLE_PRODUCTS, allColumns,
				null, null, null, null, null);

		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Product product = cursorToProduct(cursor);
			products.add(product);
			cursor.moveToNext();
		}

		cursor.close();
		return products;
	}

	private Product cursorToProduct(Cursor cursor) {
		Product product = new Product();
		product.setIdSQLite(cursor.getLong(0));
		product.setName(cursor.getString(1));
		product.setPrice(cursor.getString(2));
		product.setType(cursor.getString(3));
		product.setQuantity(cursor.getString(4));
		product.setImage(cursor.getBlob(5));
		product.setLongitude(cursor.getString(6));
		product.setLatitude(cursor.getString(7));

		return product;
	}
}
