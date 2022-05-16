package com.abreuretto.findpharmacy;

import java.text.NumberFormat;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

public class FotoAdapter extends ArrayAdapter<FotoFS>{
    private List<FotoFS> items;
    private int selectedPos = -1;
     
    public FotoAdapter(Context context, List<FotoFS> items) {
        super(context, R.layout.app_foto_lis, items);
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
            v = li.inflate(R.layout.app_foto_lis, null);    
            v.setClickable(true); 
           
        }
        
        FotoFS app = items.get(position);
        
        
        SmartImageView icon = (SmartImageView) v.findViewById(R.id.imagem);
        icon.setImageUrl(app.getImg_place()) ;   icon.setImageUrl(app.getImg_place()) ;  
        
        
       // SmartImageView imageView;
        if(convertView == null){
            //imageView = new SmartImageView(context);
            v.setLayoutParams(new GridView.LayoutParams(100,100));
           // ((SmartImageView) v).setScaleType(SmartImageView.ScaleType.CENTER_CROP);
            v.setPadding(5, 5, 5, 5);
        } 
        else{
            v = (SmartImageView) convertView;
        }
        
          
        
        
        
        
        v.setTag(String.valueOf(position));
        //add listener to the button also and also to the row view
        v.setOnClickListener( new View.OnClickListener()
        {
                public void onClick(View v)
                {
                       
                	
                	int pos = Integer.parseInt(v.getTag().toString());
                        
                        FotoFS appw = items.get(pos);
   
                        
                        
                   
                            Intent intent= new Intent(getContext(), DetalheGOActivity.class);
                            intent.putExtra("foto",appw.getImg_place());
              
                            intent.putExtra("nome",appw.getName_user());
                   
                            getContext().startActivity(intent);                        
                            
                        
                       
                        
                        
                        
                        //Toast.makeText(getContext(),"Item in position " + pos + "  "+appw.getName()+ " Lat: "+Double.toString(appw.getaLat())+" clicked", Toast.LENGTH_LONG).show();
                }});
        
        
        return v;
       
    }







}

