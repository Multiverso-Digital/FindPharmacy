package com.abreuretto.findpharmacy;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import android.widget.TextView;

import com.loopj.android.image.SmartImageView;

public class ApplicationAdapterTips extends ArrayAdapter<ApplicationTips>{
    private List<ApplicationTips> items;
    private int selectedPos = -1;
     
    public ApplicationAdapterTips(Context context, List<ApplicationTips> items) {
        super(context, R.layout.tips_fs_custom, items);
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
            v = li.inflate(R.layout.tips_fs_custom, null);    
            v.setClickable(true); 
           
        }
         
        
        

        
        
        
        ApplicationTips app = items.get(position);
        
        
        
        
         
        if(app != null) {
            SmartImageView icon = (SmartImageView) v.findViewById(R.id.appIcon);
            TextView titleText = (TextView)v.findViewById(R.id.titleTxt);
            TextView edText = (TextView)v.findViewById(R.id.endeTxt);
            
            
            
            if (app.getImguser() != "talk.png") {
            
            if(icon != null) {
            	icon.setImageUrl(app.getImguser());
            }
            }
            
             
            if(titleText != null) titleText.setText(app.getTips());
             
                String tudo = null;    
                tudo = app.getNome()+" "+app.getData();
                
                edText.setText(tudo);
                
                
            
             
            
                 
        }
         
       
        v.setTag(String.valueOf(position));
        //add listener to the button also and also to the row view
        v.setOnClickListener( new View.OnClickListener()
        {
                public void onClick(View v)
                {
                        //now get the tag of View v and convert it to integer.
                	int pos = Integer.parseInt(v.getTag().toString());
                    
                	 ApplicationTips app = items.get(pos);               
                    
  
                    if (app.getTipo().equals("2")) {
                        Intent intent= new Intent(getContext(), WebTipsActivity.class);
                        intent.putExtra("ref",app.getRefe());
                        intent.putExtra("id",app.getId());
                        intent.putExtra("tipo","2");
                        getContext().startActivity(intent);                     
        		     	}
                        
        
        		     	if (app.getTipo().equals("4")) {
                            Intent intent= new Intent(getContext(), WebTipsActivity.class);
                            intent.putExtra("ref",app.getRefe());
                            intent.putExtra("id",app.getId());
                            intent.putExtra("tipo","4");
                            getContext().startActivity(intent);                     
            		     	}               
    		     	
    		     	
    		     	
    		     	
                    
                    
                    
                    
            }});


                
        
        return v;
       
    }







}

