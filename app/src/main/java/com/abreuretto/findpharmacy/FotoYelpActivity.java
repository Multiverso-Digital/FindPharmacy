package com.abreuretto.findpharmacy;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.abreuretto.findpharmacy.FotoNKActivity.ImageAdapter;

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
import android.util.DisplayMetrics;
import android.util.Log;
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

public class FotoYelpActivity extends  FragmentActivity  { 

	private ProgressDialog dialog;
	String refe = null;
	String lat = null;
	String lon = null;
	String nome = null;
	String id = null;
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
			
			alert.showAlertDialog(FotoYelpActivity.this, this.getResources().getString(R.string.semfotocab),
            		this.getResources().getString(R.string.semfoto), false);
    	   return;	
			
			
		}
	       refe = extras.getString("ref");
	       lat = extras.getString("lat");
	       lon = extras.getString("lon");
	       nome = extras.getString("nome");
	       id = extras.getString("id");
	       
	       
	       shared_preferences = getSharedPreferences("abreuretto", MODE_PRIVATE);
		    foto = shared_preferences.getString("fotos", "");
		 
	       
		    

		    if (refe.equals("semfoto"))
		   	       {
		   	    	   //dialog.dismiss();
		   	    	   alert.showAlertDialog(FotoYelpActivity.this, this.getResources().getString(R.string.semfotocab),
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
		    
	       
	       
	      
	       String url = "http://www.yelp.com/biz_photos/"+Uri.encode(id);
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
			appG.setImg_user(result.get(pegando).getImg_user());
			appG.setName_user(result.get(pegando).getName_user());
			
			
			
			
			
			SmartImageView place = (SmartImageView)findViewById(R.id.ima01);
		  	place.setImageUrl(appG.getImg_place());
		  	SmartImageView imau = (SmartImageView)findViewById(R.id.imausu_1);
      	  	imau.setImageUrl(appG.getImg_user());
    		   TextView tusu = (TextView)findViewById(R.id.tusu1);
      		   tusu.setText(appG.getName_user());
      		   TextView tdata = (TextView)findViewById(R.id.tdata1);
      		   tdata.setText(appG.getData());
		  	
		  	
		  	
		  		
		  	
		  	if (pegando < conta-1)
		  	{
		  	
		  	appG.setImg_place(result.get(pegando+1).getImg_place());
		  	appG.setImg_user(result.get(pegando+1).getImg_user());
			appG.setName_user(result.get(pegando+1).getName_user());
			
		  	
		  	
			SmartImageView place2 = (SmartImageView)findViewById(R.id.ima02);
		    place2.setImageUrl(appG.getImg_place());
		  	SmartImageView imau2 = (SmartImageView)findViewById(R.id.imausu_2);
      	  	imau2.setImageUrl(appG.getImg_user());
    		   TextView tusu2 = (TextView)findViewById(R.id.tusu2);
      		   tusu2.setText(appG.getName_user());
      		   TextView tdata2 = (TextView)findViewById(R.id.tdata2);
      		   tdata2.setText(appG.getData());
	
		  	} 		
			
		
	
		
	
		
  	 
     
      
     		
	}
	
	   
	   
	   
	   private class PegaFSTask extends AsyncTask<String, Integer, String> {
		     
		   public PegaFSTask(FotoYelpActivity fotoYelpActivity) {
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
		         
		        
		        
		    	String inicio = ",[{\"photo_caption\":";
		    	String fim = "\"}]";
		    	
		    	int achouini = sJson.indexOf(inicio);
		    	int achoufim = sJson.indexOf(fim);
		    	
		    	
		    	
		    	
		    	String resto =   "{\"lista\": "+ sJson.substring(achouini+1, achoufim+3)+"}"; 
		    	
		    //	Log.d("valorori", resto);
		    	
		    
		    	
		    	try
		        {   
		        String four =(String) resto.toString();
		  		jArray = new JSONObject(four);
		          
		        } catch (JSONException e) {
		              System.err.println("erro");
		        }
		      	
		    	
		    	
		    	
		    	
		    	try
		        {   
		       
		    		aJson = jArray.getJSONArray("lista");
		          
		        } catch (JSONException e) {
		              System.err.println("erro");
		        }
		      	
		  		  
		  		  
		  		  
		  		 
		   


                         try
		        	  {
		              
                         
		        		  int tama = aJson.length();
		        		  conta = tama;
		        		  foto = Integer.toString(tama) ;
                             for(int i=0; i<tama; i++) {
                            	 FotoFS appG = new FotoFS();
                            	  jsonR = aJson.getJSONObject(i);
                                  String foto = "http:"+jsonR.getString("uri");
                                  String fotogrande = "http:"+jsonR.getString("uri");
                                  String nome = jsonR.getJSONObject("user").getString("display_name");
                                  String fotouser = "http:"+jsonR.getJSONObject("user").getString("primary_photo");                                
                                  appG.setImg_user(fotouser);
                                  appG.setName_user(nome);
                                  appG.setImg_place(foto);
                                  appG.setFotogrande(fotogrande);
                                  result.add(appG);
		        	   }
		        		  
		        		  
		        		  
		                
		        	  } catch (JSONException e) {
		              	  
		              }     	       
		     	       
		     	       
		        	  			     	 
			     	
			     	
			     	
			     	
			     	poegaleria();
			     	
			     	
			     	if (result.size() > 0)
			     	{
			    	
			     	try {
				 		 FileOutputStream fos1 = getBaseContext().openFileOutput("fotos.dat", Context.MODE_PRIVATE);
			             ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
			             oos1.writeObject(result);
			             oos1.close();
			         } catch (IOException e) {
			             e.printStackTrace();
			         }
			     	
			     	}
			     	
			     	
		 }}
	
	
	   
	   
	   
	   
	   
	   
	   
	   
	   
	  
	   
	   
	   
	
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
	    	 if (foto.equals("1")) 
	    	 {
	    		 acabou = true;
	    		 dialog.dismiss();
	    	 } 
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
	             
	             /*
	             if (widthPixels == 240) {
	            	 imageView.setLayoutParams(new GridView.LayoutParams(75,75));
	             }
	             
	             
	             if (widthPixels == 320) {
	            	   imageView.setLayoutParams(new GridView.LayoutParams(100,100));
	     	            	 
	             }
	             
	             if (widthPixels == 480) {
	            	   imageView.setLayoutParams(new GridView.LayoutParams(150,150));
	     	            	 
	             }
	             
	             if (widthPixels == 720) {
	            	   imageView.setLayoutParams(new GridView.LayoutParams(225,225));
	     	            	 
	             }
	             
	             
	             if ((widthPixels == 1280) || (widthPixels == 600) || (widthPixels == 800)) {
	            	   imageView.setLayoutParams(new GridView.LayoutParams(275,275));
	     	            	 
	             }
	             */
	             
	             
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
