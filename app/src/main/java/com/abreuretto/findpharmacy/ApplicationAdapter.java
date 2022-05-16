package com.abreuretto.findpharmacy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectOutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


import com.loopj.android.image.SmartImageView;
 
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewDebug.IntToString;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
 
public class ApplicationAdapter extends ArrayAdapter<Application>{
    private List<Application> items;
    private int selectedPos = -1;
     
    public ApplicationAdapter(Context context, List<Application> items) {
        super(context, R.layout.app_custom_list, items);
        this.items = items;
    }
     
    
    public void setSelectedPosition(int pos){
    selectedPos = pos;
    // inform the view of this change
    notifyDataSetChanged();
    }

    public int getSelectedPosition(){
    return selectedPos;
    }


    
    
    @Override
    public int getCount() {
        return items.size();
    }
    
    
    
    
    
    
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
         
        if(v == null) {
            LayoutInflater li = LayoutInflater.from(getContext());
            v = li.inflate(R.layout.app_custom_list, null);    
            v.setClickable(true); 
           
        }
         
        
        

        
        
        
        Application app = items.get(position);
        
        
        
        
         
        if(app != null) {
        	
            SmartImageView icon = (SmartImageView) v.findViewById(R.id.appIcon);
            
            
            TextView titleText = (TextView)v.findViewById(R.id.titleTxt);
            LinearLayout ratingCntr = (LinearLayout)v.findViewById(R.id.ratingCntr);
            
            
            
            TextView dlText = (TextView)v.findViewById(R.id.dlTxt);
            TextView edText = (TextView)v.findViewById(R.id.endeTxt);
            
            if(icon != null) {
            	icon.setImageUrl(app.getIcon());
            }
             
            if(titleText != null) titleText.setText(app.getName());
             
            if(dlText != null) {
               NumberFormat nf = NumberFormat.getNumberInstance();
               
               double valor    = 0.621371192;
               double dista    = app.getDistance();
               double distakm  = dista/1000; 
               double distamil = distakm*valor;
               
                dlText.setText(nf.format(distakm)+" km  "+nf.format(distamil)+" mil");
                edText.setText(app.getVicinity());
                edText.setTextSize(12);
                
                
          
                
            }
             
            
            ratingCntr.removeAllViewsInLayout();
            
            if(ratingCntr != null && ratingCntr.getChildCount() == 0) 
            {        
                for(int i=1; i<=5; i++) 
                {
                    ImageView iv = new ImageView(getContext());
                    if(i <= app.getRating()) 
                    {
                    	
                    	if (app.getTipo() == 2) {
                    		iv.setImageDrawable(getContext().getResources().getDrawable(R.drawable.checkfs));
                    	} else
                    	
                    		if (app.getTipo() == 1) {
                        		iv.setImageDrawable(getContext().getResources().getDrawable(R.drawable.start_checked_blue));
                        	} else
                    	{
                    	
                    	iv.setImageDrawable(getContext().getResources().getDrawable(R.drawable.start_checked));
                    	}
                    }
                    else 
                    {                
                        iv.setImageDrawable(getContext().getResources().getDrawable(R.drawable.start_unchecked));
                    }
                    ratingCntr.addView(iv);
                }
            }
            if (app.getTipo() == 2) {
              	 NumberFormat nf = NumberFormat.getNumberInstance();
              	 ratingCntr.removeAllViewsInLayout();
              	
              	ImageView iv = new ImageView(getContext());
              	TextView  tv = new TextView(getContext());
              	
              	iv.setImageDrawable(getContext().getResources().getDrawable(R.drawable.checkfs));
              	tv.setText(nf.format(app.getRating()));
              	tv.setTextSize(12);
              	tv.setTextColor(getContext().getResources().getColor(R.color.texto));
              	
              	if (app.getRating() > 0) {
              		ratingCntr.addView(tv);
                  	ratingCntr.addView(iv);	
              	}
              	
              }
            
            if (app.getTipo() == 4) {          
            	
            	TextView  revi = new TextView(getContext());
            	
            	 NumberFormat nf = NumberFormat.getNumberInstance();
            	ratingCntr.removeAllViewsInLayout();
            	ImageView iv = new ImageView(getContext());
            	if (app.getRatyelp() == 0) {  iv.setImageDrawable(getContext().getResources().getDrawable(R.drawable.tab00));}
            	if (app.getRatyelp() == 1) {  iv.setImageDrawable(getContext().getResources().getDrawable(R.drawable.tab10));}
            	if (app.getRatyelp() == 1.5) {  iv.setImageDrawable(getContext().getResources().getDrawable(R.drawable.tab15));}
            	if (app.getRatyelp() == 2) {  iv.setImageDrawable(getContext().getResources().getDrawable(R.drawable.tab20));}
            	if (app.getRatyelp() == 2.5) {  iv.setImageDrawable(getContext().getResources().getDrawable(R.drawable.tab25));}
            	if (app.getRatyelp() == 3) {  iv.setImageDrawable(getContext().getResources().getDrawable(R.drawable.tab30));}
            	if (app.getRatyelp() == 3.5) {  iv.setImageDrawable(getContext().getResources().getDrawable(R.drawable.tab35));}
            	if (app.getRatyelp() == 4) {  iv.setImageDrawable(getContext().getResources().getDrawable(R.drawable.tab40));}
            	if (app.getRatyelp() == 4.5) {  iv.setImageDrawable(getContext().getResources().getDrawable(R.drawable.tab45));}
            	if (app.getRatyelp() == 5) {  iv.setImageDrawable(getContext().getResources().getDrawable(R.drawable.tab50));} 
            	
            	revi.setText("  "+nf.format(app.getReview())+" Rev.");
            	revi.setTextSize(12);
            	revi.setTextColor(getContext().getResources().getColor(R.color.texto));
            	
            	ratingCntr.addView(iv);
            	ratingCntr.addView(revi);
            	
            }
            
        }
         
       
        
        
        
        
        
        v.setTag(String.valueOf(position));
        //add listener to the button also and also to the row view
        v.setOnClickListener( new View.OnClickListener()
        {
                public void onClick(View v)
                {
                       
                	
                	
                	int pos = Integer.parseInt(v.getTag().toString());
                        
                        Application appw = items.get(pos);
   
                    	
                        
                        
                        Intent intent= new Intent(getContext(), TabDetActivity.class);
                        intent.putExtra("ref",appw.getReference());
                        intent.putExtra("lat",Double.toString(appw.getaLat()));
                        intent.putExtra("lon",Double.toString(appw.getaLon()));
                        intent.putExtra("nome",appw.getName());
                        intent.putExtra("id",appw.getId());
                        intent.putExtra("tipo", Integer.toString(appw.getTipo()));
                        intent.putExtra("rating", Integer.toString(appw.getRating()));
                        
                        getContext().startActivity(intent);  
                        
                        
                        /*
                        
                        
                        if (appw.getTipo() == 1) {
                            Intent intent= new Intent(getContext(), DetalheGOActivity.class);
                            intent.putExtra("ref",appw.getReference());
                            intent.putExtra("lat",Double.toString(appw.getaLat()));
                            intent.putExtra("lon",Double.toString(appw.getaLon()));
                            intent.putExtra("nome",appw.getName());
                            intent.putExtra("id",appw.getId());
                            getContext().startActivity(intent);                        
                            }
                        
                        
                        if (appw.getTipo() == 2) {
                        Intent intent= new Intent(getContext(), DetalheFSActivity.class);
                        intent.putExtra("ref",appw.getReference());
                        intent.putExtra("lat",Double.toString(appw.getaLat()));
                        intent.putExtra("lon",Double.toString(appw.getaLon()));
                        intent.putExtra("nome",appw.getName());
                        intent.putExtra("id",appw.getId());
                        getContext().startActivity(intent);                        
                        }
                        
                        
                        
                        
                        if (appw.getTipo() == 3) {
                            Intent intent= new Intent(getContext(), DetalheNKActivity.class);
                            intent.putExtra("ref",appw.getReference());
                            intent.putExtra("lat",Double.toString(appw.getaLat()));
                            intent.putExtra("lon",Double.toString(appw.getaLon()));
                            intent.putExtra("nome",appw.getName());
                            intent.putExtra("id",appw.getId());                            
                            getContext().startActivity(intent);                        
                            }
                        
                        */
                        
                        //Toast.makeText(getContext(),"Item in position " + pos + "  "+appw.getName()+ " Lat: "+Double.toString(appw.getaLat())+" clicked", Toast.LENGTH_LONG).show();
                }});
        
        
        return v;
       
    }







}

