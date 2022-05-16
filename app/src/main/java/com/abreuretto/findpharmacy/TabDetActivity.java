package com.abreuretto.findpharmacy;

import android.os.Bundle;
import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TabHost.TabSpec;

public class TabDetActivity extends TabActivity {
    /** Called when the activity is first created. */
	
	
	  SharedPreferences shared_preferences;
	  SharedPreferences.Editor shared_preferences_editor;
	
	
	String refe = null;
	String lat = null;
	String lon = null;
	String nome = null;
	String id = null;
	String tipo = null;
	String atual = null;
	String anterior = null;
	String rat = null;
	
	int heightPixels = 0;
	
	TabHost tabHost;
	
	
	
    



	
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        
Bundle extras = getIntent().getExtras();
		
		
	    if (extras != null)
	    {
	       refe = extras.getString("ref");
	       lat = extras.getString("lat");
	       lon = extras.getString("lon");
	       nome = extras.getString("nome");
	       id = extras.getString("id");
	       tipo = extras.getString("tipo");
	       rat  = extras.getString("rating");
	       
	    }
        
	    if (tipo.equals("1"))
        {
	    	setTitle(R.string.google);
        }
        
	    if (tipo.equals("2"))
        {
	    	setTitle(R.string.foursquare);
        }
        
	    if (tipo.equals("3"))
        {
	    	setTitle(R.string.nokia);
        }
        
	    if (tipo.equals("4"))
        {
	    	setTitle(R.string.yelp);
        }
        
	    
        
        setContentView(R.layout.activity_tab_det);
        
        

        
	    
	    
        shared_preferences = getSharedPreferences("abreuretto", MODE_PRIVATE);
	    atual = shared_preferences.getString("atual", "");
	    anterior = shared_preferences.getString("anterior", ""); 
	    
	    
        
	    if (id.equals(atual)) {
	    	
	    	
	    } else
	    	
	    {
	    	excluir();
	    }
	    
	    
         
        tabHost = getTabHost();
         
        String tmapa = getResources().getString(R.string.mapatab);
        String tfoto = getResources().getString(R.string.fototab);
        String ttips = getResources().getString(R.string.tipstab);
        String tsuge = getResources().getString(R.string.sugetab);
        
        
        // Tab for Photos
        TabSpec mapspec = tabHost.newTabSpec(tmapa);
        mapspec.setIndicator(tmapa, getResources().getDrawable(R.drawable.tabmapb));
        Intent mapsIntent = null;
        if (tipo.equals("1"))
        {
        	mapsIntent = new Intent(this, DetalheGOActivity.class); 	
        }
        if (tipo.equals("2"))
        {
        	mapsIntent = new Intent(this, DetalheFSActivity.class); 	
        }
        if (tipo.equals("3"))
        {
        	mapsIntent = new Intent(this, DetalheNKActivity.class); 	
        }
        if (tipo.equals("4"))
        {
        	mapsIntent = new Intent(this, DetalheYelpActivity.class); 	
        }
        
        
        
        mapsIntent.putExtra("ref",refe);
        mapsIntent.putExtra("lat",lat);
        mapsIntent.putExtra("lon",lon);
        mapsIntent.putExtra("nome",nome);
        mapsIntent.putExtra("id",id);
        mapsIntent.putExtra("tipo", tipo);
        mapsIntent.putExtra("rating", rat);
        
        mapspec.setContent(mapsIntent);
         
        
    
        
        
        
        
        // Tab for Songs
        TabSpec photosspec = tabHost.newTabSpec(tfoto);        
        photosspec.setIndicator(tfoto, getResources().getDrawable(R.drawable.tabfotob));
 
        Intent photosIntent = null;
        
        
        if (tipo.equals("1")) {
        	photosIntent = new Intent(this, FotoGOActivity.class);
        }
        if (tipo.equals("2")) {
        	photosIntent = new Intent(this, FotoFSActivity.class);
        }
        if (tipo.equals("3")) {
        	photosIntent = new Intent(this, FotoNKActivity.class);
        }
        if (tipo.equals("4")) {
        	photosIntent = new Intent(this, FotoYelpActivity.class);
        }
        
        
        photosIntent.putExtra("ref",refe);
        photosIntent.putExtra("lat",lat);
        photosIntent.putExtra("lon",lon);
        photosIntent.putExtra("nome",nome);
        photosIntent.putExtra("id",id);
        photosIntent.putExtra("tipo", tipo);
        photosIntent.putExtra("rating", rat);
        photosspec.setContent(photosIntent);
        
        
        
        
        // Tab for Videos
        TabSpec reviewspec = tabHost.newTabSpec(ttips);
        reviewspec.setIndicator(ttips, getResources().getDrawable(R.drawable.tabreviewb));
        Intent reviewIntent = new Intent(this, TipsFSActivity.class);
        reviewIntent.putExtra("ref",refe);
        reviewIntent.putExtra("lat",lat);
        reviewIntent.putExtra("lon",lon);
        reviewIntent.putExtra("nome",nome);
        reviewIntent.putExtra("id",id);
        reviewIntent.putExtra("tipo", tipo);
        reviewIntent.putExtra("rating", rat);
        
        reviewspec.setContent(reviewIntent);
        
        
        

        TabSpec sugespec = tabHost.newTabSpec(tsuge);
        sugespec.setIndicator(tsuge, getResources().getDrawable(R.drawable.tabsugeb));
        Intent sugeIntent = new Intent(this, PegaSugestaoActivity.class);
        sugeIntent.putExtra("ref",refe);
        sugeIntent.putExtra("lat",lat);
        sugeIntent.putExtra("lon",lon);
        sugeIntent.putExtra("nome",nome);
        sugeIntent.putExtra("id",id);
        sugeIntent.putExtra("tipo", tipo);
        sugeIntent.putExtra("rating", rat);
        
        sugespec.setContent(sugeIntent);
        
        
        
           
        
        
         
        // Adding all TabSpec to TabHost
        tabHost.addTab(mapspec); // Adding photos tab
        tabHost.addTab(photosspec); // Adding songs tab
        tabHost.addTab(reviewspec); // Adding videos tab
        tabHost.addTab(sugespec); // Adding videos tab

        //tabHost.setOnTabChangedListener((OnTabChangeListener) this); 
        
        
        
        setTabColor(tabHost);
        
        
        
       
        
        //tabHost.setOnTabChangedListener((OnTabChangeListener) this);

        tabHost.setOnTabChangedListener (new OnTabChangeListener () 
        { 
        @ Override 
        public void onTabChanged (String tabId) 
        { 
        	
        	setTabColor(tabHost);
        	
        } 
        }); 
         
         
        
        
        
        
        
    }
    
    
    
    
    
    
    public static void setTabColor(TabHost tabhost) {
        for(int i=0;i<tabhost.getTabWidget().getChildCount();i++)
        {
            tabhost.getTabWidget().getChildAt(i).setBackgroundResource(R.color.abanormal); //unselected
            tabhost.getTabWidget().setDividerDrawable(null);
        }
        
        
        tabhost.getTabWidget().getChildAt(tabhost.getCurrentTab()).setBackgroundResource(R.color.texto); // selected
    }
    
    
   public void excluir()
   {
	   try
   	{
   	deleteFile("suges.dat");
   	deleteFile("fotos.dat");
   	deleteFile("tips.dat");
   	deleteFile("det.dat");   	
   	
   	
    shared_preferences_editor = shared_preferences.edit();
    shared_preferences_editor.putString("atual", id);
    shared_preferences_editor.commit();
    shared_preferences_editor = shared_preferences.edit();
    shared_preferences_editor.putString("anterior", atual);
    shared_preferences_editor.commit();
   	
   	
   	
   	
   	}
       catch (Exception e) 
   	{
   		
   	}
   }
    
    
   
    
    
}
