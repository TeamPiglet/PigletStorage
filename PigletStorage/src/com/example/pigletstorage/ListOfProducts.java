package com.example.pigletstorage;

import java.util.ArrayList;
import java.util.List;

import com.example.SQLite.ProductDataSource;
import com.example.SQLite.models.Product;

import android.support.v7.app.ActionBarActivity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class ListOfProducts extends ActionBarActivity {

	ListView list;
    CustomAdapter adapter;
    public  ListOfProducts CustomListView = null;
	private ProductDataSource datasource;
	public List<Product> values;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
	
		  CustomListView = this;
		  datasource = new ProductDataSource(CustomListView);
		  datasource.open();
		  
		  values = datasource.getAllProducts();
		  	   
		  Resources res = getResources();
		  list = ( ListView )findViewById( R.id.listView1 );  // List defined in XML ( See Below )
		   
		  /**************** Create Custom Adapter *********/
		  adapter=new CustomAdapter( CustomListView, values,res );
		  list.setAdapter( adapter );
	
	}

	  /****** Function to set data in ArrayList *************/
 
	
    public void onItemClick(int mPosition){
        Product tempValues = ( Product ) values.get(mPosition);

       // SHOW ALERT          

        Toast.makeText(CustomListView,
                	"Name: " + tempValues.getName()
                  + "\r\nPrice: " + tempValues.getPrice()
              	  +"\r\nType: " + tempValues.getType(),
					                Toast.LENGTH_LONG)
        .show();
    }
    
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list, menu);
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