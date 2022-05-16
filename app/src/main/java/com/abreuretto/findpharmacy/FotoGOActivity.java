package com.abreuretto.findpharmacy;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.sql.Date;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.abreuretto.findpharmacy.FotoFSActivity.ImageAdapter;
import com.loopj.android.image.SmartImageView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class FotoGOActivity extends  FragmentActivity  { 

	private ProgressDialog dialog;
	String refe = null;
	String lat = null;
	String lon = null;
	String nome = null;
	String foto = null;
	String ende = null;
	
	   AlertDialogManager alert = new AlertDialogManager();
	
	//private WebView webView;
	  SharedPreferences shared_preferences;
	  SharedPreferences.Editor shared_preferences_editor;
  String latx = null;
  String lonx = null;
   int qtd = 0;
   int pegando = 0;
   int conta = 0;
   int widthPixels = 0;
   
   PegaFSTask task = new PegaFSTask(this);
	
   ArrayList<FotoFS> result = new ArrayList<FotoFS>(); 
   
   
  	private Handler handler = new Handler();
  	int conta1 = 0;
  	boolean acabou = false;
   
   
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.app_foto_lis);
		
		DisplayMetrics metrics = new DisplayMetrics();
	    getWindowManager().getDefaultDisplay().getMetrics(metrics);		
	    int heightPixels = metrics.heightPixels;
	    widthPixels = metrics.widthPixels;
	    float density = metrics.density;
	    int densityDpi = metrics.densityDpi;
		
		//  dialog = ProgressDialog.show(this, "", this.getResources().getString(R.string.carregandoselecionado));
		     
		
		
		Bundle extras = getIntent().getExtras();
		
		
		if (extras == null)
		{
			
			alert.showAlertDialog(FotoGOActivity.this, this.getResources().getString(R.string.semfotocab),
            		this.getResources().getString(R.string.semfoto), false);
    	   return;	
			
			
		}
	       refe = extras.getString("ref");
	       lat = extras.getString("lat");
	       lon = extras.getString("lon");
	       nome = extras.getString("nome");
	       
	       
	       shared_preferences = getSharedPreferences("abreuretto", MODE_PRIVATE);
		    foto = shared_preferences.getString("fotos", "");
		 
	       
		      
	       if (foto.equals("0"))
	       {
	    	  // dialog.dismiss();
	    	   alert.showAlertDialog(FotoGOActivity.this, this.getResources().getString(R.string.semfotocab),
	            		this.getResources().getString(R.string.semfoto), false);
	    	   return;
	    	   
	    	   
	       }
	       
	       
             dialog = ProgressDialog.show(this, "", this.getResources().getString(R.string.carregandofotos));  
	       
     		
             shared_preferences = getSharedPreferences("abreuretto", MODE_PRIVATE);
     	    latx = shared_preferences.getString("lat", "");
     	    lonx = shared_preferences.getString("lon", ""); 
     	    
	       try {
	            FileInputStream fis1 = this.openFileInput("fotos.dat");
	            ObjectInputStream is1 = new ObjectInputStream(fis1);
	            Object readObject1 = null;
	            
	            

	            
				try {
					readObject1 = is1.readObject();
	                result = (ArrayList<FotoFS>) readObject1;
	                poegaleria();   
	                return;
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	            is1.close();

	            
	            
	        } catch (IOException e)
	        {
	            //
	        
	        }
	       
	       
	       
	       
	       acabou = false;
	    	 conta1 = 0;
	         handler.postDelayed(runnable, 1000); 
		    
	      
	       String url = refe + "&sensor=true&key=AIzaSyBRhULv84uFwIW0JYyGonkTM5sr5dvOR9I";
	       task.execute(url,latx,lonx);
	       
	  

		

	    
	  
	


		
	}

	
private Runnable runnable = new Runnable() {
    	
    	@Override
    	   public void run() {
    	      /* do what you need to do */
    	      conta1 = conta1 + 1;
    	      if (acabou == true) {return;}
    	      if ((conta1 > 30) && (acabou == false)) 
    	      
    	      {    	      
    	    	  if (dialog != null) dialog.dismiss();
    	    	  task.cancel(true);
    	    	 // finish();
    	      }	
    	    	  else
    	      {
    	      handler.postDelayed(this, 1000);
              }
    	   }
    	};
	
	
	
	protected void PegaFS(int pegando)
	{
		
		
		
		
			FotoFS appG = new FotoFS();
			
			appG.setImg_place(result.get(pegando).getImg_place());
			SmartImageView place = (SmartImageView)findViewById(R.id.ima01);
		  	place.setImageUrl(appG.getImg_place());
		  		
		  	
		  	if (pegando < conta-1)
		  	{
		  	
		  	appG.setImg_place(result.get(pegando+1).getImg_place());
			SmartImageView place2 = (SmartImageView)findViewById(R.id.ima02);
		    place2.setImageUrl(appG.getImg_place());
		  	} 		
			
		
	
		
	
		
  	 
      
      
		
     
  		  
  		  
  		  
  	  		
  		
  		
  		  
  		  

  		  
  		
	}
	
	   
	   
	   
	   private class PegaFSTask extends AsyncTask<String, Integer, String> {
		     
		   public PegaFSTask(FotoGOActivity fotoGOActivity) {
			// TODO Auto-generated constructor stub
		}

		protected String doInBackground(String... params) {
			  
			      String urlF = params[0];
			      String result = null;
			      
			      latx =  params[1];
			      lonx =  params[2];
			   
			      
			      if (isCancelled()) 
			        	
			        {
			  		    acabou = true;	
			        	return null;
			        }
			      
			      
			   
			   try {
			          HttpClient clientF = new DefaultHttpClient();
			          HttpGet httpgetF = new HttpGet(urlF);
			          HttpResponse responseF = clientF.execute(httpgetF);
			          HttpEntity entityF = responseF.getEntity();
			      
			          if(entityF == null) {
			              //msg = "No response from server";
			              result =" ";
			             // return null;        
			          }
			          InputStream isF = entityF.getContent();
			         // return 
			          
			          String convF = streamToString(isF);
			          
			         	result = convF;
			         	//Log.d("valorori", streamToString(isF));
			      }
			      catch(IOException e){
			          //msg = "No Network Connection";
			          result = " ";
			      }
			     
			      
			      
			          
			        
			      return result;  

		     }

		     protected void onProgressUpdate(Integer... progress) {
		         //setProgressPercent(progress[0]);
		     }

		     protected void onPostExecute(String sJson) {
		    	 
		    
		    	   JSONObject jsonF  = null;
		    	   JSONObject jArray = null;
		    	   JSONArray aJson = null;
		    	   JSONObject jsonR = null;
		    	
		    	if(sJson == null) {
		            return;
		        }        
		         
		        
		        
		    
		    	try
		        {   
		        String four =(String) sJson.toString();
		  		jArray = new JSONObject(four);
		          
		        } catch (JSONException e) {
		              System.err.println("erro");
		        }
		      	
		  		  
		  		  
		  		  
		  		  try 
		         
		          {
		        	  jsonF  = (JSONObject) jArray.get("result");
		          } catch (JSONException e) {
		              System.err.println("erro");
		          }
		   


                         try
		        	  {
		              
		        		  aJson = jsonF.getJSONArray("photos");
		        		  int tama = aJson.length();
		        		  conta = tama;
		        		  
                             for(int i=0; i<tama; i++) {
                            	 FotoFS appG = new FotoFS();
                            	 jsonR = aJson.getJSONObject(i);
                                  String foto = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=280&photoreference="+jsonR.getString("photo_reference")+"&sensor=true&key=AIzaSyBRhULv84uFwIW0JYyGonkTM5sr5dvOR9I";
                                  String fotogrande = "https://maps.googleapis.com/maps/api/place/photo?maxwidth=1280&photoreference="+jsonR.getString("photo_reference")+"&sensor=true&key=AIzaSyBRhULv84uFwIW0JYyGonkTM5sr5dvOR9I";
                                  
                                  appG.setImg_place(foto);
                          		  appG.setFotogrande(fotogrande);
                                  
                                  
                                  
                                  result.add(appG);
		        	   }
		        		  
		        		  
		        		  
		                
		        	  } catch (JSONException e) {
		              	  
		              }     	       
		     	       
		     	       
		        	  			     	 
			     	
			     	
			     	
			     	
				    
			     	
			     	poegaleria();
		 }}
	
	
	   
	   
	   
	   
	   
	   
	   
	   
	   
	   public void Start_Click(View view)
       {
           pegando = 0;
           PegaFS(pegando);
       }

	   public void End_click(View view)
       {
           pegando = conta - 2;
           PegaFS(pegando);

       }

	   public void next_click(View view)
       {
           pegando = pegando + 2;
           if (pegando >= conta)
           {

               pegando = 0;
               PegaFS(pegando);

           }
           else
           {
               PegaFS(pegando);

           }
       }

	   public void prior_click(View view)
       {
           pegando = pegando - 2;
           if (pegando <= 2)
           {

               pegando = 0;
               PegaFS(pegando);

           }
           else
           {
               PegaFS(pegando);

           }
       }

	   
	   
	   
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.detalhe_f, menu);
		return true;
	}
	
	public String streamToString(final InputStream is) throws IOException{
	      BufferedReader reader = new BufferedReader(new InputStreamReader(is));
	      StringBuilder sb = new StringBuilder(); 
	      String line = null;
	       
	      try {
	          while ((line = reader.readLine()) != null) {
	              sb.append(line + "\n");
	          }
	      } 
	      catch (IOException e) {
	          throw e;
	      } 
	      finally {           
	          try {
	              is.close();
	          } 
	          catch (IOException e) {
	              throw e;
	          }
	      }
	       
	      return sb.toString();
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
	
	public void poegaleria()
	{
		   GridView gridview = (GridView) findViewById(R.id.gridview);
	       gridview.setAdapter(new ImageAdapter(this));
	       
	       
	    
	       
	        gridview.setOnItemClickListener(new OnItemClickListener() {
	            
	            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	 
	                // Sending image id to FullScreenActivity
	                Intent i = new Intent(getApplicationContext(), FotoGrandeActivity.class);
	                // passing array index
	                i.putExtra("foto", result.get(position).getFotogrande());
	                i.putExtra("nome", nome);
	                i.putExtra("ende", result.get(position).getName_user()+" "+ result.get(position).getData());
	                startActivity(i);
	            }
	        });
  
        
	        dialog.dismiss();
	        acabou = true;	
        
        
	}






	
	public class ImageAdapter extends BaseAdapter
	 {
	     private Context context;
	     
	     public ImageAdapter(Context c)
	     {
	         context = c;
	     }
	     
	     
	    // Returns the number of images
	     public int getCount(){
	      return result.size();
	     }
	     
	     // Returns the ID of an item
	     public Object getItem(int position) {
	         return position;
	     }
	     
	     // Returns the ID of an item
	     public long getItemId(int position){
	         return position;
	     }
	     
	     // Returns an ImageView View
	     public View getView(int position, View convertView, ViewGroup parent){
	    	 SmartImageView imageView;
	         if(convertView == null){
	             imageView = new SmartImageView(context);
	            
	             
	             int tot =  (int)Math.round((widthPixels/4)-5);
	             imageView.setLayoutParams(new GridView.LayoutParams(tot,tot));
	             
	             
	             
	             imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	             imageView.setPadding(5, 5, 5, 5);
	         } 
	         else{
	             imageView = (SmartImageView) convertView;
	         }
	         
	         imageView.setImageUrl(result.get(position).getImg_place() ) ;    
	         
	         
	         
	         return imageView;
	     }

	 }

}
