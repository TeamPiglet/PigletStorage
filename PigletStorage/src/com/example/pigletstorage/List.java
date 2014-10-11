package com.example.pigletstorage;

import java.util.ArrayList;

import android.support.v7.app.ActionBarActivity;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class List extends ActionBarActivity {

	ListView list;
    CustomAdapter adapter;
    public  List CustomListView = null;
    public  ArrayList<ListModel> CustomListViewValuesArr = new ArrayList<ListModel>();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list);
	
		  CustomListView = this;
		  
		  /******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
		  setListData();
		   
		  Resources res = getResources();
		  list= ( ListView )findViewById( R.id.listView1 );  // List defined in XML ( See Below )
		   
		  /**************** Create Custom Adapter *********/
		  adapter=new CustomAdapter( CustomListView, CustomListViewValuesArr,res );
		  list.setAdapter( adapter );
	
	}

	  /****** Function to set data in ArrayList *************/
    public void setListData()
    {
         
        for (int i = 0; i < 11; i++) {
             
            final ListModel sched = new ListModel();
                 
              /******* Firstly take data in model object ******/
               sched.setCompanyName("Company "+i);
               sched.setImage("ic_launcher");
               sched.setUrl("http:\\www."+i+".com");
                
            /******** Take Model Object in ArrayList **********/
            CustomListViewValuesArr.add( sched );
        }
         
    }	
	
    public void onItemClick(int mPosition){
        ListModel tempValues = ( ListModel ) CustomListViewValuesArr.get(mPosition);

       // SHOW ALERT          

        Toast.makeText(CustomListView,
                ""+tempValues.getCompanyName()
                  + "Image:"+tempValues.getImage()
              	  +" Url:"+tempValues.getUrl(),
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
