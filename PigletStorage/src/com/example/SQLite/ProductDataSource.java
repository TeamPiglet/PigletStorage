package com.example.SQLite;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import com.example.SQLite.models.*;

public class ProductDataSource {
	// Database fields
	private SQLiteDatabase database;
	private SQLiteHelper dbHelper;
	private String[] allColumns = { SQLiteHelper.COLUMN_ID,
			SQLiteHelper.COLUMN_NAME, SQLiteHelper.COLUMN_PRICE,
			SQLiteHelper.COLUMN_TYPE, SQLiteHelper.COLUMN_QUANTITY, };

	public ProductDataSource(Context context) {
		dbHelper = new SQLiteHelper(context);
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Product createProduct(String name, String price, String type,
			String quantity) {
		ContentValues values = new ContentValues();
		values.put(SQLiteHelper.COLUMN_NAME, name);
		values.put(SQLiteHelper.COLUMN_PRICE, price);
		values.put(SQLiteHelper.COLUMN_TYPE, type);
		values.put(SQLiteHelper.COLUMN_QUANTITY, quantity);

		long insertId = database.insert(SQLiteHelper.TABLE_PRODUCTS, null,
				values);

		Cursor cursor = database.query(SQLiteHelper.TABLE_PRODUCTS, allColumns,
				SQLiteHelper.COLUMN_ID + " = " + insertId, null, null, null,
				null);

		cursor.moveToFirst();
		Product newProduct = cursorToComment(cursor);
		cursor.close();
		return newProduct;
	}

	public void deleteProduct(Product product) {
		long id = product.getId();
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
			Product product = cursorToComment(cursor);
			products.add(product);
			cursor.moveToNext();
		}

		cursor.close();
		return products;
	}

	private Product cursorToComment(Cursor cursor) {
		Product product = new Product();
		product.setId(cursor.getLong(0));
		product.setName(cursor.getString(1));
		product.setPrice(cursor.getString(2));
		product.setType(cursor.getString(3));
		product.setQuantity(cursor.getString(4));

		return product;
	}
}
