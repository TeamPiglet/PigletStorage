package com.example.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper{
	public static final String TABLE_PRODUCTS = "products";
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_NAME = "name";
	  public static final String COLUMN_TYPE = "type";
	  public static final String COLUMN_PRICE = "price";
	  public static final String COLUMN_QUANTITY = "quantity";
	  public static final String COLUMN_IMAGE = "image";

	  private static final String DATABASE_NAME = "products.db";
	  private static final int DATABASE_VERSION = 1;

	  private static final String DATABASE_CREATE = "create table "
	      + TABLE_PRODUCTS + "(" 
		      + COLUMN_ID + " integer primary key autoincrement, " 
		      + COLUMN_NAME + " text not null,"
		      + COLUMN_TYPE + " text not null,"
		      + COLUMN_PRICE + " text not null,"
		      + COLUMN_QUANTITY + " integer not null, "
		      + COLUMN_IMAGE + " blob);";

	  public SQLiteHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  }

	  @Override
	  public void onCreate(SQLiteDatabase database) {
	    database.execSQL(DATABASE_CREATE);
	  }

	  @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	    Log.w(SQLiteHelper.class.getName(),
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_PRODUCTS);
	    onCreate(db);
	  }
}
