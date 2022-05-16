package com.abreuretto.findpharmacy;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Parcelable;

import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.LayoutInflater.Factory;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class PegaCidaActivity extends ListActivity implements FetchDataListener{
    private ProgressDialog dialog;
	  SharedPreferences shared_preferences;
	  SharedPreferences.Editor shared_preferences_editor;
    String latx = null;
    String lonx = null;
    String passou = null;
    boolean acabou = false;
	 ConnectionDetector cd;
	// Alert dialog manager
	AlertDialogManager alert = new AlertDialogManager();
	ListView listView;
	ArrayList<AppMapa> lista = new ArrayList<AppMapa>();
	ArrayList<Application> listapp = new ArrayList<Application>();
	
	private Handler handler = new Handler();
	  FetchDataTask task = new FetchDataTask(this);
	
	int conta = 0;
	
	
	 
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        setContentView(R.layout.activity_pega_cida);     
 
        shared_preferences = getSharedPreferences("abreuretto", MODE_PRIVATE);
	    latx = shared_preferences.getString("lat", "");
	    lonx = shared_preferences.getString("lon", ""); 
	    passou =  shared_preferences.getString("detpassou", "");


	    
	    /*
	    
	    if (passou == "true") {
	    	
	    	
	    	try
	    	{
	    	
	    	ObjectInputStream ois = new ObjectInputStream(new FileInputStream("lista.dat"));
	    	listapp = (ArrayList<Application>) ois.readObject();
	    	ois.close();
	    	onFetchComplete(listapp);
	    	}
	        catch (Exception e) {
				// TODO: handle exception
			}
	        
	        
	        
	        
	    }
	    
	    */
	    
		cd = new ConnectionDetector(getApplicationContext());
		 
        // Check for internet connection
        if (!cd.isConnectingToInternet()) {
            // Internet Connection is not present
            alert.showAlertDialog(PegaCidaActivity.this, this.getResources().getString(R.string.interneterror),
            		this.getResources().getString(R.string.interneterrorpede), false);
            // stop executing code by return
            return;
        }
	    
	    
       
        
        
        
        
        
        
        initView();  
        

       


        
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
    	    	  task.cancel(true);
    	    	  finish();
    	      }	
    	    	  else
    	      {
    	      handler.postDelayed(this, 1000);
              }
    	   }
    	};
    
    
    
    
    
    
    
    
    
    private void initView() {
        // show progress dialog
    	//https://api.foursquare.com/v2/venues/search?ll=" + latxCel + "," + lonxCel + "&categoryId=4bf58dd8d48988d1ca941735&client_id=WYVK3C5JYYSKWLYKMHZFQUAGZYXRBAFXMLARQHLJBFFC05IX&client_secret=GJO5FLB20ZI03LLYBEBST55NI5OMLPG4IRIHXRBPLYB1S4M3&v=20120321&radius=" + App.wRadius.ToString()));

    	
    	
    	
    	
    	 dialog = ProgressDialog.show(this, "", this.getResources().getString(R.string.carregandoplaces));
         
    	 
    	 
    	 
    	 
    	 
    	 try {
             FileInputStream fis2 = this.openFileInput("mapa.dat");
             ObjectInputStream is2 = new ObjectInputStream(fis2);
             Object readObject2 = null;
             try {
 				readObject2 = is2.readObject();
                 lista = (ArrayList<AppMapa>) readObject2;
                
 			} catch (ClassNotFoundException e) {
 				e.printStackTrace();
 			}
             is2.close();
         } catch (IOException e)
         {
         }     
    	 
    	 
    	 
    	 
    	 
    	 
    	 
    	 
    	 
         
         try {
             FileInputStream fis = this.openFileInput("places.dat");
             ObjectInputStream is = new ObjectInputStream(fis);
             Object readObject = null;
             
             

             
 			try {
 				
 				readObject = is.readObject();
                 listapp = (ArrayList<Application>) readObject;
                 ApplicationAdapter adapter = new ApplicationAdapter(this, listapp);
                 Collections.sort(listapp , new EmploeeComparator ()); 	
                 adapter.notifyDataSetChanged();         
                 setListAdapter(adapter);  
                 if(dialog != null)  dialog.dismiss();
                // dialog.dismiss();
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
    	
       
       	StringBuffer sb=new StringBuffer();
        sb.append("https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + latx + "," + lonx + "&radius=5000&sensor=true&types=pharmacy&key=AIzaSyBRhULv84uFwIW0JYyGonkTM5sr5dvOR9I");
        String url=sb.toString();  
        url = url.replaceAll("\\|", "%A6");


        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.US);
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        String datax = sdf.format(cal.getTime());
         
    	
       // dialog = ProgressDialog.show(this, "", this.getResources().getString(R.string.carregandoplaces));
       // url  = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?location=" + latx + "," + lonx + "&radius=5000&sensor=true&types=lodging&key=AIzaSyBRhULv84uFwIW0JYyGonkTM5sr5dvOR9I";
        String url1 = "https://api.foursquare.com/v2/venues/search?ll=" + latx + "," + lonx + "&categoryId=4bf58dd8d48988d10f951735&client_id=WYVK3C5JYYSKWLYKMHZFQUAGZYXRBAFXMLARQHLJBFFC05IX&client_secret=GJO5FLB20ZI03LLYBEBST55NI5OMLPG4IRIHXRBPLYB1S4M3&v="+datax+"&radius=5000";
        String url2 = "http://demo.places.nlp.nokia.com/places/v1/discover/explore?in=" + latx + "," + lonx + ";r=5000&tf=plain&pretty=y&app_id=al3SR82BNt_apHzbF-b9&app_code=oEW8ZxpiQU7lsr2XkYZAjg&cat=pharmacy";
        
        
        
        Log.d("URL", url1);
        
      
        task.execute(url,url1,url2,latx,lonx);
        
     
        
        
        
    }
    
    
    private void oncantask() {
		// TODO Auto-generated method stub

	}
    
    
    protected void onListItemClick(ListView l, View v, int position, long id) {
      //  String item = (String) getListAdapter().getItem(position);
      //  Toast.makeText(this, item + " selected", Toast.LENGTH_LONG).show();
      }

   
    

    
    class EmploeeComparator implements Comparator<Application> {

        public int compare(Application e1, Application e2) {
        	
        	   double lhsDistance = e1.getDistance();
        	    double rhsDistance = e2.getDistance();
        		    if (lhsDistance < rhsDistance) return -1;
        	    else if (lhsDistance > rhsDistance) return 1;
            else return 0;

        }

    }

    
    
    
    
    
    
    
  
    public void onFetchComplete(List<Application> data) {
        
    	
    	
    	acabou = true;
    	
    	
    	if (task.isCancelled()) {
    		
    		 dialog = ProgressDialog.show(this, "", this.getResources().getString(R.string.interneterror));
    		 finish() ;
    	       	
    			
    	}
    	
    	
    	// dismiss the progress dialog
    
        // create new adapter
        
        
        
        for(int i=0; i<data.size(); i++) {
        
        	
        	
        	
      	AppMapa appM = new AppMapa();        	
        appM.setaLat(data.get(i).getaLat());
        appM.setaLon(data.get(i).getaLon());
        appM.setDistance(data.get(i).getDistance());
        appM.setIcon(data.get(i).getIcon());
        appM.setId(data.get(i).getId());
        appM.setName(data.get(i).getName());
        appM.setRating(data.get(i).getRating());
        appM.setReference(data.get(i).getReference());
        appM.setTipo(data.get(i).getTipo());
        appM.setVicinity(data.get(i).getVicinity());
        lista.add(appM);
        
        }
        	
        ApplicationAdapter adapter = new ApplicationAdapter(this, data);
        Collections.sort(data , new EmploeeComparator ()); 	
        adapter.notifyDataSetChanged();         
        setListAdapter(adapter);   
   
   
   
        try {
            FileOutputStream fos = this.openFileOutput("places.dat", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(data);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
            
        }

        
        
        try {
            FileOutputStream fos2 = this.openFileOutput("mapa.dat", Context.MODE_PRIVATE);
            ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
            oos2.writeObject(lista);
            oos2.close();
        } catch (IOException e) {
            e.printStackTrace();
            
        }
        


        if(dialog != null)  dialog.dismiss();
   
   
    }
 

    
    

    
    
    
   

    

    
    
    

    
    
  
    public void onFetchFailure(String msg) {
        // dismiss the progress dialog
        if(dialog != null)  dialog.dismiss();
  
        
        // show failure message
        //Toast.makeText(this, msg, Toast.LENGTH_LONG).show();        
    }


    
		
		
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.

	
	     
		getMenuInflater().inflate(R.menu.pega_cida, menu);


		return true;
	}
	
	

	
	
	
	
	
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    // Handle item selection
	    switch (item.getItemId()) {
	        case R.id.action_tips:
	            pegaTips();
	            return true;
	        case R.id.action_refresh:
	            pegaRefresh();
	            return true;
	        case R.id.action_about:
	            pegaAbout();
	            return true;
	        case R.id.action_map:
	            pegaMap();
	            return true;
   
	            
	            
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}
	

    	
	public void pegaTips()
	{
		
		
  	  
		    shared_preferences_editor = shared_preferences.edit();
		    shared_preferences_editor.putString("latby", String.valueOf(latx));
		    shared_preferences_editor.commit();
		    shared_preferences_editor = shared_preferences.edit();
		    shared_preferences_editor.putString("lonby", String.valueOf(lonx));
		    shared_preferences_editor.commit();
		
		
        Intent intent = new Intent(this, PegaSugestaoActivity.class);
        startActivity(intent);
	}
	
	public void pegaRefresh()
	{
		initView();		
		
	}
	
	public void pegaAbout()
	{
        Intent intent = new Intent(this, SobreActivity.class);
        startActivity(intent);
	}	
	
	public void pegaMap()
	{
		
		

		Intent intent = new Intent(this, MapaGeralActivity.class);
		Bundle b = new Bundle();
		

			b.putSerializable("StudentObject",(Serializable)lista);	
		
		
		
		
		
		intent.putExtras(b);
		startActivity(intent);

		
		
		
		
		
       
		
        
        
        
	}	
	
	
	 @Override
	  protected void onResume() {
	    super.onResume();
	    
	    
	    
	    
	  }

	  
	  @Override
	  protected void onPause() {
	    super.onPause();
	    
	    acabou = true;
	    
	   
	   
	  }
   
	
		
	
	
	
}
