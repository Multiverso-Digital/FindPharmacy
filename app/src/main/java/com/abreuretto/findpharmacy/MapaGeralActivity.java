package com.abreuretto.findpharmacy;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collection;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;



public class MapaGeralActivity extends FragmentActivity  {

	  SharedPreferences shared_preferences;
	  SharedPreferences.Editor shared_preferences_editor;
    String latx = null;
    String lonx = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mapa_geral);	
		
		
		
        shared_preferences = getSharedPreferences("abreuretto", MODE_PRIVATE);
	    latx = shared_preferences.getString("lat", "");
	    lonx = shared_preferences.getString("lon", ""); 
		
		//ArrayList<AppMapa> georef = (ArrayList<AppMapa>) getIntent().getSerializableExtra("StudentObject");
		
		
		Intent intent=this.getIntent();
		Bundle bundle=intent.getExtras();

		ArrayList<AppMapa> georef =  (ArrayList<AppMapa>)bundle.getSerializable("StudentObject");
		
		
		
		GoogleMap googleMap;
        googleMap = ((SupportMapFragment)(getSupportFragmentManager().findFragmentById(R.id.map))).getMap();
       
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        
        
        
        
        LatLng latLng = null;
        
        for(int i=0; i<georef.size(); i++) {
        
        	
        latLng = new LatLng(georef.get(i).getaLat() , georef.get(i).getaLon());
        
        NumberFormat nf = NumberFormat.getNumberInstance();
        
        double valor    = 0.621371192;
        double dista    = georef.get(i).getDistance();
        double distakm  = dista/1000; 
        double distamil = distakm*valor;
        
         
        
        
        
        
        
        googleMap.addMarker(new MarkerOptions()
                .position(latLng)
                .title(georef.get(i).getName())
                
                .snippet(georef.get(i).getVicinity()+" \n "+ nf.format(distakm)+" km  "+nf.format(distamil)+" mil")
                .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE)));
        
        }
        
        
        
        latLng = new LatLng(Double.parseDouble(latx) , Double.parseDouble(lonx));
        googleMap.addMarker(new MarkerOptions()
        .position(latLng)
        .title("YOU")
        .snippet("Here")
        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW)));
        
        
        
        googleMap.getUiSettings().setCompassEnabled(true);
        googleMap.getUiSettings().setZoomControlsEnabled(true);
        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 13));
		
	}

	
	
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mapa_geral, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	     

	       
	        case R.id.action_about:
	            pegaAbout();
	            return true;
	            
	            
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
    
	
	public void pegaAbout()
	{
        Intent intent = new Intent(this, SobreActivity.class);
        startActivity(intent);
	}	
	

	

}
