package com.abreuretto.findpharmacy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import com.abreuretto.findpharmacy.PegaCidaActivity.EmploeeComparator;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.ListActivity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

public class PegaSugestaoActivity extends ListActivity implements FetchDataListenerFS{
    private ProgressDialog dialog;
	  SharedPreferences shared_preferences;
	  SharedPreferences.Editor shared_preferences_editor;
    String latx = null;
    String lonx = null;
	 ConnectionDetector cd;
	// Alert dialog manager
	AlertDialogManager alert = new AlertDialogManager();
	
	ArrayList<ApplicationFS> lista = new ArrayList<ApplicationFS>();
	private Handler handler = new Handler();
	int conta = 0;
	boolean acabou = false;
	
	FetchDataTaskFS taskFS;
	ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_pega_sugestao);
        
		shared_preferences = getSharedPreferences("abreuretto", MODE_PRIVATE);
	    latx = shared_preferences.getString("latby", "");
	    lonx = shared_preferences.getString("lonby", ""); 
	    
	    cd = new ConnectionDetector(getApplicationContext());
		 
        // Check for internet connection
        if (!cd.isConnectingToInternet()) {
            // Internet Connection is not present
            alert.showAlertDialog(PegaSugestaoActivity.this, this.getResources().getString(R.string.interneterror),
            		this.getResources().getString(R.string.interneterrorpede), false);
            // stop executing code by return
            return;
        }
	    
	    
       
        
        
        initViewFS();  
        
	}
	 private Runnable runnable = new Runnable() {
	    	
	    	
	    	
	    	@Override
	    	   public void run() {
	    	      /* do what you need to do */
	    	      conta = conta + 1;
	    	      if (acabou == true) {return;}
	    	      if ((conta > 60) && (acabou == false)) 
	    	      
	    	      {    	      
	    	    	  dialog.dismiss();
	    	    	  taskFS.cancel(true);
	    	    	  finish();
	    	      }	
	    	    	  else
	    	      {
	    	      handler.postDelayed(this, 1000);
	              }
	    	   }
	    	};
	
	private void initViewFS() {
        // show progress dialog
    	//https://api.foursquare.com/v2/venues/search?ll=" + latxCel + "," + lonxCel + "&categoryId=4bf58dd8d48988d1ca941735&client_id=WYVK3C5JYYSKWLYKMHZFQUAGZYXRBAFXMLARQHLJBFFC05IX&client_secret=GJO5FLB20ZI03LLYBEBST55NI5OMLPG4IRIHXRBPLYB1S4M3&v=20120321&radius=" + App.wRadius.ToString()));

    	
        
		  dialog = ProgressDialog.show(this, "", this.getResources().getString(R.string.carregandoplacesfs));  
        
        
        
        try {
            FileInputStream fis = this.openFileInput("suges.dat");
            ObjectInputStream is = new ObjectInputStream(fis);
            Object readObject = null;
            
            

            
			try {
				
				readObject = is.readObject();
                lista = (ArrayList<ApplicationFS>) readObject;
                ApplicationAdapterFS adapter = new ApplicationAdapterFS(this, lista);
    	        // set the adapter to list 
    	        


    	   Collections.sort(lista , new EmploeeComparator ()); 	
    	   adapter.notifyDataSetChanged();         
    	   setListAdapter(adapter);   
    	   if(dialog != null)  dialog.dismiss();
                //dialog.dismiss();
                return;
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				//dialog.dismiss();
			}
            is.close();

            
            
        } catch (IOException e)
        {
        	//dialog.dismiss();
        
        }

        
        
        
      
    	acabou = false;
   	    conta = 0;
        handler.postDelayed(runnable, 1000);


        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.US);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String datax = sdf.format(cal.getTime());
        
        
        String url  = "https://api.foursquare.com/v2/venues/explore?ll=" + latx + "," + lonx + "&venuePhotos=1&section=topPicks&client_id=WYVK3C5JYYSKWLYKMHZFQUAGZYXRBAFXMLARQHLJBFFC05IX&client_secret=GJO5FLB20ZI03LLYBEBST55NI5OMLPG4IRIHXRBPLYB1S4M3&v="+datax;
        
       // Log.d("URL", url);
        
        taskFS = new FetchDataTaskFS(this);
        taskFS.execute(url,latx,lonx);
        
     
        
        
        
    }
	
	
	
	
	 class EmploeeComparator implements Comparator<ApplicationFS> {

	        public int compare(ApplicationFS e1, ApplicationFS e2) {
	        	
	        	   double lhsDistance = e1.getDistance();
	        	    double rhsDistance = e2.getDistance();
	        		    if (lhsDistance < rhsDistance) return -1;
	        	    else if (lhsDistance > rhsDistance) return 1;
	            else return 0;

	        }

	    }

	    
	    
	  
	    public void onFetchCompleteFS(List<ApplicationFS> data) {
	        // dismiss the progress dialog
	       
	        // create new adapter
	    	
	    	
	    	

	    	acabou = true;
	    	
	    	
	    	if (taskFS.isCancelled()) {
	    		
	    		 dialog = ProgressDialog.show(this, "", this.getResources().getString(R.string.interneterror));
	    		 finish() ;
	    	       	
	    			
	    	}
	    	
	        
	        for(int i=0; i<data.size(); i++) {
	            
	        	
	          	ApplicationFS appM = new ApplicationFS();        	
	            appM.setaLat(data.get(i).getaLat());
	            appM.setaLon(data.get(i).getaLon());
	            appM.setDistance(data.get(i).getDistance());
	            appM.setIcon(data.get(i).getIcon());
	            appM.setId(data.get(i).getId());
	            appM.setName(data.get(i).getName());
	            appM.setRating(data.get(i).getRating());
	            appM.setReference(data.get(i).getReference());
	    //        appM.setTipo(data.get(i).getTipo());
	            appM.setVicinity(data.get(i).getVicinity());
	            lista.add(appM);	
	            }
	        
	        ApplicationAdapterFS adapter = new ApplicationAdapterFS(this, data);
	        // set the adapter to list 
	        


	   Collections.sort(data , new EmploeeComparator ()); 	
	   adapter.notifyDataSetChanged();         
	   setListAdapter(adapter);   
	   
	   
	   try {
           FileOutputStream fos = this.openFileOutput("suges.dat", Context.MODE_PRIVATE);
           ObjectOutputStream oos = new ObjectOutputStream(fos);
           oos.writeObject(data);
           oos.close();
       } catch (IOException e) {
           e.printStackTrace();
           
       }
	   
	   
	   
	   
	   if(dialog != null)  dialog.dismiss();
	   
	   
	   
	   
	    }
	 

	
	
	
	
	
	
	
    public void onFetchFailureFS(String msg) {
        // dismiss the progress dialog
        if(dialog != null)  dialog.dismiss();
  
        
        // show failure message
        //Toast.makeText(this, msg, Toast.LENGTH_LONG).show();        
    }
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.pega_sugestao, menu);
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
