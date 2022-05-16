package com.abreuretto.findpharmacy;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.loopj.android.image.SmartImageView;

import android.net.Uri;
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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class TipsFSActivity extends  FragmentActivity  { 

	private ProgressDialog dialog;
	String refe = null;
	String lat = null;
	String lon = null;
	String nome = null;
	String tipo = null;
	 String url = null;
	 String id = null;
	 String tips = null;
	 
	//private WebView webView;
	 
	  AlertDialogManager alert = new AlertDialogManager();
	 
	  SharedPreferences shared_preferences;
	  SharedPreferences.Editor shared_preferences_editor;
  String latx = null;
  String lonx = null;
   int qtd = 0;
   int pegando = 0;
   int conta = 0;
   
   PegaFSTask task = new PegaFSTask(this);
	
   ArrayList<ApplicationTips> result = new ArrayList<ApplicationTips>(); 

   ConnectionDetector cd;	
   ListView listview;
   
   
	private Handler handler = new Handler();
	int conta1 = 0;
	boolean acabou = false;
   
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
		
		DisplayMetrics metrics = new DisplayMetrics();
	    getWindowManager().getDefaultDisplay().getMetrics(metrics);		
	    int heightPixels = metrics.heightPixels;
	    int widthPixels = metrics.widthPixels;
	    float density = metrics.density;
	    int densityDpi = metrics.densityDpi;
	    
	    
	 
	    
		
			
		
		
		
	
		
		
		
		cd = new ConnectionDetector(getApplicationContext());
		 
        // Check for internet connection
        if (!cd.isConnectingToInternet()) {
            // Internet Connection is not present
            alert.showAlertDialog(TipsFSActivity.this, this.getResources().getString(R.string.interneterror),
            		this.getResources().getString(R.string.interneterrorpede), false);
            // stop executing code by return
            return;
        }
		
		
		
		
		
		
		
		
		Bundle extras = getIntent().getExtras();
		
		
		
		if (extras == null)
		{
			
			 alert.showAlertDialog(TipsFSActivity.this, this.getResources().getString(R.string.interneterror),
	            		this.getResources().getString(R.string.interneterrorpede), false);
    	   return;	
			
			
		}
		
		
		
		
	   
	    	
	    	
	    	  dialog = ProgressDialog.show(this, "", this.getResources().getString(R.string.carregandoreviews));
			     
	  		
	    	
	    	
	    	
	       refe = extras.getString("ref");
	       lat = extras.getString("lat");
	       lon = extras.getString("lon");
	       nome = extras.getString("nome");
	       tipo = extras.getString("tipo");
	       id = extras.getString("id");
	       
	       shared_preferences = getSharedPreferences("abreuretto", MODE_PRIVATE);
		    tips = shared_preferences.getString("tips", "");
		 
	       
		    
			
			if (tipo.equals("4"))
			{
				setContentView(R.layout.activity_tips_yelp);

			
			} else
			{
				setContentView(R.layout.activity_tips_fs);
			}
		    
		    
		    
		    
		    
		    
		      
	       if ((tips.equals("0"))  && (!tipo.equals("4")))
	       {
	    	   dialog.dismiss();
	    	   alert.showAlertDialog(TipsFSActivity.this, this.getResources().getString(R.string.semtipscab),
	            		this.getResources().getString(R.string.semtips), false);
	    	    return; 
	    	   
	       }
	       
	       
	       if ((tips.equals("0"))  && (tipo.equals("4")))
	       {
	    	   dialog.dismiss();
	    	   url =   "http://demo.places.nlp.nokia.com/places/v1/places/"+id+"/media/reviews?size=50&app_id=al3SR82BNt_apHzbF-b9&app_code=oEW8ZxpiQU7lsr2XkYZAjg";
		       acabou = false;
		    	 conta1 = 0;
		         handler.postDelayed(runnable, 1000); 
		         ImageView yel = (ImageView) findViewById(R.id.imalike);
		     
		         yel.setImageResource(R.drawable.yelp);
		       task.execute(url,latx,lonx,Integer.toString(qtd));
	    	   return;
	       }
	       
			
			
	        shared_preferences = getSharedPreferences("abreuretto", MODE_PRIVATE);
		    latx = shared_preferences.getString("lat", "");
		    lonx = shared_preferences.getString("lon", ""); 
	       
	       
	       try {
	            FileInputStream fis1 = this.openFileInput("tips.dat");
	            ObjectInputStream is1 = new ObjectInputStream(fis1);
	            Object readObject1 = null;
	            
	            

	            
				try {
					readObject1 = is1.readObject();
	                result = (ArrayList<ApplicationTips>) readObject1;
	                PoeAdapter();   
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
	       
	       
	       
	        
	       
	       if (tipo.equals("2") )	
	       {
	             
	       url = refe + "/tips?sort=recent&limit=50&offset=" + Integer.toString(qtd) + "&client_id=WYVK3C5JYYSKWLYKMHZFQUAGZYXRBAFXMLARQHLJBFFC05IX&client_secret=GJO5FLB20ZI03LLYBEBST55NI5OMLPG4IRIHXRBPLYB1S4M3&v=20121116";
	       }
	       
	       
	       
	       if (tipo.equals("1") )	
	       
	       {
	       url = refe + "&sensor=true&key=AIzaSyBRhULv84uFwIW0JYyGonkTM5sr5dvOR9I";
	       }
	       
	       
	       if (tipo.equals("3") )	
		       
	       {
	       url =   "http://demo.places.nlp.nokia.com/places/v1/places/"+id+"/media/reviews?size=50&app_id=al3SR82BNt_apHzbF-b9&app_code=oEW8ZxpiQU7lsr2XkYZAjg";
		     ;
	       }
	       
	       
	       
	       
           if (tipo.equals("4") )	
		       
	       {
	       url =   "http://demo.places.nlp.nokia.com/places/v1/places/"+id+"/media/reviews?size=50&app_id=al3SR82BNt_apHzbF-b9&app_code=oEW8ZxpiQU7lsr2XkYZAjg";
		     ;
	       }
	       
	       
	       
	       
	       
	       acabou = false;
	    	 conta1 = 0;
	         handler.postDelayed(runnable, 1000); 
	       
	       
	       task.execute(url,latx,lonx,Integer.toString(qtd));
	       
	       
	       
	       
	       
	      
	       
	       
	       
	    

	    
	  
	


		
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
		 String url = refe +"/tips?sort=recent&limit=50&offset=" +Integer.toString(pegando)+"&client_id=WYVK3C5JYYSKWLYKMHZFQUAGZYXRBAFXMLARQHLJBFFC05IX&client_secret=GJO5FLB20ZI03LLYBEBST55NI5OMLPG4IRIHXRBPLYB1S4M3&v=20121116";
		 PegaFSTask task2 = new PegaFSTask(this);
		 task2.execute(url,latx,lonx,Integer.toString(pegando));
	}
	
	   
	   
	   
	   private class PegaFSTask extends AsyncTask<String, Integer, String> {
		     
		   public PegaFSTask(TipsFSActivity tipsFSActivity) {
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
			      
			      
			      
			   
			      
			      if (!tipo.equals("4")) {  
			      
			      
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
			     
			      }
			      else
			      {
			    	  
			    		String consumerKey = "Nm1ctpa9bkJJF42qqoWNhQ";
			    		String consumerSecret = "F0zgsBBFJuEJJkb0jZjilrwc_LQ";
			    		String token = "e9_NDDatbNk_ycth5OA2Sh0uznGdolgm";
			    		String tokenSecret = "5d_VTMdkmdhk3fdjbe185xnSxwM";

				    Yelp yelp = new Yelp(consumerKey, consumerSecret, token, tokenSecret);
				    result = yelp.detalhe(Uri.encode(id));
			    	  
			    	  
			      }
			      
			          
			        
			      return result;  

		     }

		     protected void onProgressUpdate(Integer... progress) {
		         //setProgressPercent(progress[0]);
		     }

		     protected void onPostExecute(String sJson) {
		    	 
		    
		    if (tipo.equals("2") )	 
		    { 	 
		    	 
		    	 
		    	 
		    	   JSONObject jsonF  = null;
		    	   JSONObject jArray = null;
		    	   JSONArray  jfotos = null;
		    	   JSONObject jsonR  = null;
		    	
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
		        	  jsonF  = (JSONObject) jArray.get("response");
		          } catch (JSONException e) {
		              System.err.println("erro");
		          }
		      	
		          	 
		        	  
		          	
		        	  
		        	  
		          	  
		          	  
		        	  try
		        	  {
		        		  
		              jfotos = jsonF.getJSONObject("tips").getJSONArray("items");		 
		              conta = Integer.parseInt(jsonF.getJSONObject("tips").getString("count"));
		              
		             
		        		
		              for(int i=0; i<jfotos.length(); i++) {
			              
		            	  jsonR = jfotos.getJSONObject(i);
			          		  
		            	  ApplicationTips appG = new ApplicationTips();  		  
		            	  String usu = null;
		          		  usu = jsonR.getJSONObject("user").getJSONObject("photo").getString("prefix")+"50x50"+jsonR.getJSONObject("user").getJSONObject("photo").getString("suffix");
		          		  appG.setImguser(usu);
		          		  appG.setNome(jsonR.getJSONObject("user").getString("firstName"));
		          		  long pega = 0;
		          		  pega = Long.parseLong(jsonR.getString("createdAt"));
		          		  appG.setData(DateFormat.getLongDateFormat(getApplicationContext()).format(new Date(pega * 1000L)));                 
		          		  appG.setTips(jsonR.getString("text"));
		          		  appG.setTipo("2");
		          		  appG.setRefe(refe);
		          		  appG.setId(id);
		          		  result.add(appG);
		          		
		          	  
		          	  }  
		              
		        	  

		        	  
		        	  
		        	  } catch (JSONException e) {
		              	 
		              }   	  			 
		        	  
		    }
		    
		    
		    

		    
		    
		    if (tipo.equals("4") )	 
		    { 	 
		    	 
		    	 
		    	 
		    	   JSONObject jsonF  = null;
		    	   JSONObject jArray = null;
		    	   JSONArray  jfotos = null;
		    	   JSONObject jsonR  = null;
		    	
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
		  			jfotos  =  jArray.getJSONArray("reviews");
		          } catch (JSONException e) {
		              System.err.println("erro");
		          }
		      	
		          	 
		        	  
		          	
		        	  
		        	  
		          	  
		          	  
		        	  try
		        	  {
		        		
		              for(int i=0; i<jfotos.length(); i++) {
			              
		            	  jsonR = jfotos.getJSONObject(i);
			        	  ApplicationTips appY = new ApplicationTips();  		  
		            	  String usu = null;
		          		  usu = jsonR.getJSONObject("user").getString("image_url");
		          		  appY.setImguser(usu);
		          		  appY.setNome(jsonR.getJSONObject("user").getString("name"));
		          		  long pega = 0;
		          		  pega = Long.parseLong(jsonR.getString("time_created"));
		          		  appY.setData(DateFormat.getLongDateFormat(getApplicationContext()).format(new Date(pega * 1000L)));                 
		          		  appY.setTips(jsonR.getString("excerpt"));
		          		  appY.setRefe(refe);
		          		  appY.setTipo("4");
		          		  appY.setId(id);
		          		  result.add(appY);
		          		
		          	  
		          	  }  
		              
		              ApplicationTips appY = new ApplicationTips();  		  
	            	  String usu = null;
	          		  
	          		  appY.setImguser("http://www.abreuretto.com/privacidade/yelp.png");
	          		  appY.setNome("Yelp");
	          		  
	          			
	          		  SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("EEEE dd MMMM yyyy HH:mm:ss");  
	          		

	          		SimpleDateFormat s = new SimpleDateFormat("ddMMyyyyhhmmss");
	          		String format = s.format(new Date(0));  
	          				  
	          		  appY.setData("");                 
	          		  appY.setTips("READ MORE REVIEWS FROM Yelp...");
	          		  appY.setTipo("4");
	                   appY.setRefe(refe);
	                   appY.setId(id);
	                   
	          		  result.add(appY);
		              
		              
		              
		              
		        	  

		        	  
		        	  
		        	  } catch (JSONException e) {
		              	 
		              }   	  			 
		        	  
		    }
		    

		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    
		    if (tipo.equals("1") )	
		    	
		    {
		    	
		    	   JSONObject jsonF  = null;
		    	   JSONObject jArray = null;
		    	   JSONObject jsonR = null;
		    	   JSONArray aJson = null;
		    	   
		    	if(sJson == null) {
		            return;
		        }        
		         
		        
		        
		    
		    	try
		        {   
		        String four =(String) sJson.toString();
		  		jArray = new JSONObject(four);
		          
		        } catch (JSONException e) {
		              System.err.println("erro");       }
		      	
		  		  
		  		  
		  		  
		  		  try 
		         
		          {
		        	  jsonF  = (JSONObject) jArray.get("result");
		          } catch (JSONException e) {
		              System.err.println("erro");
		          }
		      	
		  		  
		  		  
		  		 
		  		  
		  		  
		  		  
		  		  
		          	 			     	    
			     	    
	                    
		        	  try
		        	  {
		              
		        		  aJson = jsonF.getJSONArray("reviews");
		        		  int tama = aJson.length();
		        		  
		        		  
		        		  for(int i=0; i<tama; i++) {
				              
			            	  jsonR = aJson.getJSONObject(i);
			            	  ApplicationTips appG = new ApplicationTips();
		        		  
			              String teste =  jsonR.getString("text");
			              if (teste.length() == 0) {
			              
			              teste = "...";
			              }
			              
			            	  
			            	  
		        		  appG.setTips(teste);
		        		  appG.setNome(jsonR.getString("author_name"));
		        		  appG.setImguser("http://www.abreuretto.com/talk.png");
		        		  
		        		  
		         		  long pega = 0;
		          		  pega = Long.parseLong(jsonR.getString("time"));
		          		  appG.setData(DateFormat.getLongDateFormat(getApplicationContext()).format(new Date(pega * 1000L)));                 
		          		  appG.setRefe(refe);
		          		  appG.setTipo("1");
		          		  appG.setId(id);
		          		  
		          		  
		          		  result.add(appG);
		        		  
		        		  
		        		  }
		        		  
		        		  
		        		  
		               
		        	  } catch (JSONException e) {
		        		  
		        		return;
		              }    
		
		    	
		    	
		    	
		    	
		    	
		    	
		    }
		        	  
		    if (tipo.equals("3") )	
		    {
		    	
		    	
		    	 JSONObject jsonF  = null;
		    	   JSONObject jArray = null;
		    	   JSONObject jsonR = null;
		    	   JSONArray aJson = null;
		    	   
		    	if(sJson == null) {
		            return;
		        }        
		    	
		    	
		    	
		        try
	        	  {
	              
		        	 String four =(String) sJson.toString();
				  		jArray = new JSONObject(four);
		        	
		        	
                    aJson = jArray.getJSONArray("items");
	        		  int tama = aJson.length();
	        		  conta = tama;
	        		  
                       for(int i=0; i<tama; i++) {
                    	   ApplicationTips appG = new ApplicationTips();
                      	  jsonR = aJson.getJSONObject(i);
                    
                            String nome = jsonR.getJSONObject("user").getString("name");
                            String usu = jsonR.getJSONObject("supplier").getString("icon");
                            String data = jsonR.getString("date");                            
                            appG.setTips(jsonR.getString("description"));
                            appG.setImguser(usu);
                            appG.setNome(nome);
                            appG.setData(data);
                            appG.setTipo("3");
                            appG.setRefe(refe);
                            appG.setId(id);
                            result.add(appG);
	        	   }
	        		  
                       
                      
                       
                       
	        		  
	        		  
	                
	        	  } catch (JSONException e) {
	              	  
	              }     	       
		    	
		    	
		    }
		        	  
		           
			     	PoeAdapter();
			     	
			     	
			     	
			     	try {
				  		 FileOutputStream fos1 = getBaseContext().openFileOutput("tips.dat", Context.MODE_PRIVATE);
			             ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
			             oos1.writeObject(result);
			             oos1.close();
			             
			             
			         } catch (IOException e) {
			             e.printStackTrace();
			             
			         }
			     	
			     	
		 }}
	
	
	   
	   public void PoeAdapter()
	   {
		   
		   
		   if (result.size() > 0) {
			   listview = (ListView) findViewById(R.id.listview);   
		   ApplicationAdapterTips adapter = new ApplicationAdapterTips(this, result);
		   adapter.notifyDataSetChanged();
		   listview.setAdapter(adapter);
		   }
		   else
		   {
			   finish();
		   }
		   

		   dialog.dismiss();
		   acabou = true;	
	   }
	   
	   
	   
	   private void setListAdapter(ApplicationAdapterTips adapter) {
		// TODO Auto-generated method stub
		
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
	
	 public void pegayelp(View view)   
		{

	 	
	        Intent intent= new Intent(this, WebTipsActivity.class);
	        intent.putExtra("ref",refe);
	        intent.putExtra("lat",lat);
	        intent.putExtra("lon",lon);
	        intent.putExtra("nome",nome);
	        intent.putExtra("id",id);
	        intent.putExtra("tipo","4");
	        startActivity(intent);                     
	     	               
		
		}
		

}
