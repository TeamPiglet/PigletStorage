package com.example.pigletstorage;

import java.util.List;

import com.example.models.Product;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/********* Adapter class extends with BaseAdapter and implements with OnClickListener ************/
public class CustomAdapter extends BaseAdapter   implements OnClickListener {
          
         /*********** Declare Used Variables *********/
         private Activity activity;
         private List data;
         private static LayoutInflater inflater=null;
         public Resources res;
         Product tempValues=null;
         int i=0;
          
         /*************  CustomAdapter Constructor *****************/
         public CustomAdapter(Activity a, List d,Resources resLocal) {
              
                /********** Take passed values **********/
                 activity = a;
                 data=d;
                 res = resLocal;
              
                 /***********  Layout inflator to call external xml layout () ***********/
                 inflater = ( LayoutInflater )activity.
                                              getSystemService(Context.LAYOUT_INFLATER_SERVICE);
              
         }
      
         /******** What is the size of Passed Arraylist Size ************/
         public int getCount() {
              
             if(data.size()<=0)
                 return 1;
             return data.size();
         }
      
         public Object getItem(int position) {
             return position;
         }
      
         public long getItemId(int position) {
             return position;
         }
          
         /********* Create a holder Class to contain inflated xml file elements *********/
         public static class ViewHolder{
              
             public TextView text;
             public TextView text1;
             public ImageView image;
      
         }
      
         /****** Depends upon data size called for each row , Create each ListView row *****/
         public View getView(int position, View convertView, ViewGroup parent) {
              
             View vi = convertView; 
             ViewHolder holder;
              
             if(convertView==null){
                  
                 /****** Inflate tabitem.xml file for each row ( Defined below ) *******/
                 vi = inflater.inflate(R.layout.list_row_layout, parent,false);
                  
                 /****** View Holder Object to contain tabitem.xml file elements ******/
 
                 holder = new ViewHolder();
                 holder.text = (TextView) vi.findViewById(R.id.firstLine);
                 holder.text1 = (TextView)vi.findViewById(R.id.secondLine);
                 holder.image = (ImageView)vi.findViewById(R.id.icon);
                  
                /************  Set holder with LayoutInflater ************/
                 vi.setTag( holder );
             }
             else {
                 holder=(ViewHolder)vi.getTag();
             }
             
             if(data.size()<=0)
             {
                 holder.text.setText("No Data");
                  
             }
             else
             {
                 /***** Get each Model object from Arraylist ********/
                 tempValues=null;
                 tempValues = ( Product ) data.get( position );
                  
                 byte[] bitmapdata = tempValues.getImage();
                 
                 /************  Set Model values in Holder elements ***********/
                 Bitmap imageBitmap = BitmapFactory.decodeByteArray(bitmapdata , 0, bitmapdata .length);
                  holder.text.setText( tempValues.getName() );
                  holder.text1.setText("Price: " + tempValues.getPrice() );
                  holder.image.setImageBitmap(imageBitmap);
                   
                  /******** Set Item Click Listner for LayoutInflater for each row *******/
 
                  vi.setOnClickListener(new OnItemClickListener( position ));
             }
             return vi;
         }
          
         @Override
         public void onClick(View v) {
                 Log.v("CustomAdapter", "=====Row button clicked=====");
         }
          
         /********* Called when Item click in ListView ************/
         private class OnItemClickListener  implements OnClickListener{           
             private int mPosition;
              
             OnItemClickListener(int position){
                  mPosition = position;
             }
              
             @Override
             public void onClick(View arg0) {
 
        
               ListOfProducts sct = (ListOfProducts)activity;
 
              /****  Call  onItemClick Method inside ListOfProducts Class ( See Below )****/
 
                 sct.onItemClick(mPosition);
             }               
         }   
     }
