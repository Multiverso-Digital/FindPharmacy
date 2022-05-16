package com.abreuretto.findpharmacy;

import java.text.NumberFormat;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TabHost;
import android.widget.TextView;
import android.widget.Toast;


import com.loopj.android.image.SmartImageView;

public class ApplicationAdapterFS extends ArrayAdapter<ApplicationFS>{
    private List<ApplicationFS> items;
    private int selectedPos = -1;
     
    public ApplicationAdapterFS(Context context, List<ApplicationFS> items) {
        super(context, R.layout.app_custom_listfs, items);
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
            v = li.inflate(R.layout.app_custom_listfs, null);    
            v.setClickable(true); 
           
        }
         
        
        

        
        
        
        ApplicationFS app = items.get(position);
         
        if(app != null) {
           // ImageView icon = (ImageView)v.findViewById(R.id.appIcon);
            
            SmartImageView icon = (SmartImageView) v.findViewById(R.id.appIconfs); 
            SmartImageView foto = (SmartImageView) v.findViewById(R.id.appfotofs); 
            
            
            
            TextView titleText = (TextView)v.findViewById(R.id.titleTxtfs);
            LinearLayout ratingCntr = (LinearLayout)v.findViewById(R.id.ratingCntrfs);
            TextView dlText = (TextView)v.findViewById(R.id.dlTxtfs);
            TextView tpText = (TextView)v.findViewById(R.id.tipTxtfs);
            
            TextView edText = (TextView)v.findViewById(R.id.endeTxtfs);
            
            if(icon != null) {
            	icon.setImageUrl(app.getIcon());
            }
            
            if(foto != null) {
         //   	foto.setImageUrl(app.getUrlfoto());
            }
            
            
            
            if(tpText != null) tpText.setText(app.getTip());
             
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
                    	
                    	if (app.getTipo().equals("2")) {
                    		iv.setImageDrawable(getContext().getResources().getDrawable(R.drawable.start_checked));
                    	} else
                    	
                    		if (app.getTipo().equals("1")) {
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
            
            
            if (app.getTipo().equals("4")) {            	
            	
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
            	
            	
            	//ratingCntr.addView(iv);
            }
            
            
        }
       
            
            
            
            
            
        
         
        
        
        


        
        
        
        v.setTag(String.valueOf(position));
        //add listener to the button also and also to the row view
        v.setOnClickListener( new View.OnClickListener()
        {
                public void onClick(View v)
                {
                        //now get the tag of View v and convert it to integer.
                	int pos = Integer.parseInt(v.getTag().toString());
                    
                    ApplicationFS appw = items.get(pos);               
                    
  
                    if (appw.getTipo().equals("2")) {
                        Intent intent= new Intent(getContext(), WebTipsActivity.class);
                        intent.putExtra("ref",appw.getReference());
                        intent.putExtra("lat",Double.toString(appw.getaLat()));
                        intent.putExtra("lon",Double.toString(appw.getaLon()));
                        intent.putExtra("nome",appw.getName());
                        intent.putExtra("id",appw.getId());
                        intent.putExtra("tipo","2");
                        intent.putExtra("rat",appw.getRating());
                        
                        getContext().startActivity(intent);                     
        		     	}
                        
        
        		     	if (appw.getTipo().equals("4")) {
                            Intent intent= new Intent(getContext(), WebTipsActivity.class);
                            intent.putExtra("ref",appw.getReference());
                            intent.putExtra("lat",Double.toString(appw.getaLat()));
                            intent.putExtra("lon",Double.toString(appw.getaLon()));
                            intent.putExtra("nome",appw.getName());
                            intent.putExtra("id",appw.getId());
                            intent.putExtra("tipo","4");
                            intent.putExtra("rat",appw.getRating());
                            getContext().startActivity(intent);                     
            		     	}               
    		     	
    		     	
    		     	
    		     	
                    
                    
                    
                    
            }});

        
        
        
        return v;
    }







}

