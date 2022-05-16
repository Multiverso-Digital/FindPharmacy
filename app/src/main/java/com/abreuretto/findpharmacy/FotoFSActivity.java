package com.abreuretto.findpharmacy;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.math.RoundingMode;
import java.sql.Date;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


import com.abreuretto.findpharmacy.PegaCidaActivity.EmploeeComparator;
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
import android.content.res.TypedArray;
import android.support.v4.app.FragmentActivity;
import android.text.format.DateFormat;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsoluteLayout;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FotoFSActivity extends  FragmentActivity  { 

	private ProgressDialog dialog;
	String refe = null;
	String lat = null;
	String lon = null;
	String nome = null;
	String foto = null;
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
   
   AlertDialogManager alert = new AlertDialogManager();
	
   ArrayList<FotoFS> result = new ArrayList<FotoFS>(); 
   FotoAdapter adapter;
   
   
	private Handler handler = new Handler();
	int conta1 = 0;
	boolean acabou = false;
	 
   
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
		
		DisplayMetrics metrics = new DisplayMetrics();
	    getWindowManager().getDefaultDisplay().getMetrics(metrics);		
	    int heightPixels = metrics.heightPixels;
	    widthPixels = metrics.widthPixels;
	    float density = metrics.density;
	    int densityDpi = metrics.densityDpi;
	    
	    
	    /*
	    
		if (heightPixels == 320) {
			
			setContentView(R.layout.detfoto_240_320);

			
		} else
		{
			setContentView(R.layout.app_foto_fs);
		}
		
		*/
		
	    setContentView(R.layout.app_foto_lis);	
		     
		
		
		Bundle extras = getIntent().getExtras();
		
		
		
		if (extras == null)
		{
			
			alert.showAlertDialog(FotoFSActivity.this, this.getResources().getString(R.string.semfotocab),
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
	    	   
	    	   
	    	   //dialog.dismiss();
	    	   
	    	   alert.showAlertDialog(FotoFSActivity.this, this.getResources().getString(R.string.semfotocab),
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
		    
	       
	       
	      
	      
	       String url = refe + "/photos?group=venue&limit=50&offset="+Integer.toString(qtd)+"&client_id=WYVK3C5JYYSKWLYKMHZFQUAGZYXRBAFXMLARQHLJBFFC05IX&client_secret=GJO5FLB20ZI03LLYBEBST55NI5OMLPG4IRIHXRBPLYB1S4M3&v=20121116";
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
	
	
	
	
public void pega(){
//	adapter = new FotoAdapter(this.getApplicationContext(), result);
//	GridView grid =  new GridView(this);
//	grid.setAdapter(adapter); 
	
	GridView gridView = (GridView) findViewById(R.id.gridview);
	adapter = new FotoAdapter(this.getApplicationContext(), result);
//	
    // Instance of ImageAdapter Class
    gridView.setAdapter(adapter);

	
	
}
	
	
	
	protected void PegaFS(int pegando)
	{
		 String url = refe + "/photos?group=venue&limit=2&offset="+Integer.toString(pegando)+"&client_id=WYVK3C5JYYSKWLYKMHZFQUAGZYXRBAFXMLARQHLJBFFC05IX&client_secret=GJO5FLB20ZI03LLYBEBST55NI5OMLPG4IRIHXRBPLYB1S4M3&v=20121116";
		 PegaFSTask task2 = new PegaFSTask(this);
		 task2.execute(url,latx,lonx,Integer.toString(pegando));
	}
	
	   
	   
	   
	   private class PegaFSTask extends AsyncTask<String, Integer, String> {
		     
		   public PegaFSTask(FotoFSActivity fotoFSActivity) {
			// TODO Auto-generated constructor stub
		}

		protected String doInBackground(String... params) {
			  
			      String urlF = params[0];
			      String result1 = null;
			      
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
			              result1 =" ";
			             // return null;        
			          }
			          InputStream isF = entityF.getContent();
			         // return 
			          
			          String convF = streamToString(isF);
			          
			         	result1 = convF;
			         	//Log.d("valorori", streamToString(isF));
			      }
			      catch(IOException e){
			          //msg = "No Network Connection";
			          result1 = " ";
			      }
			     
			      
			      
			          
			        
			      return result1;  

		     }

		     protected void onProgressUpdate(Integer... progress) {
		         //setProgressPercent(progress[0]);
		     }

		     protected void onPostExecute(String sJson) {
		    	 
		    
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
		        		  
		              jfotos = jsonF.getJSONObject("photos").getJSONArray("items");		 
		              conta = Integer.parseInt(jsonF.getJSONObject("photos").getString("count"));
		              
		              
		              
		              
		              /*
		              
		              if (conta <= 2) {
		            	  
		           	   ImageButton bini = (ImageButton)findViewById(R.id.binicio);
	          		   bini.setVisibility(4);
	          		   
	          		   ImageButton bvoltar = (ImageButton)findViewById(R.id.bvoltar);
	          		 bvoltar.setVisibility(4);
	          		   
	          		   ImageButton bnext = (ImageButton)findViewById(R.id.bnext);
	          		 bnext.setVisibility(4);
	          		   
	          		 ImageButton bfim = (ImageButton)findViewById(R.id.bfinal);
	          		bfim.setVisibility(4);
	          		   
		            	  
		            	  
		            	  
		            	  
		              }
		              
		              */
		        		
		              for(int i=0; i<jfotos.length(); i++) {
			              
		            	  jsonR = jfotos.getJSONObject(i);
			          		  
		            	  FotoFS appG = new FotoFS();
		            	
		            	  
		            	
		            	  appG.setTotal(Integer.parseInt(jsonF.getJSONObject("photos").getString("count")));		            	  
		          		  appG.setName_foto(jsonR.getJSONObject("source").getString("name"));
		          		  
		          		  String foto = null;
		          		  foto = jsonR.getString("prefix")+"250x250"+jsonR.getString("suffix");
		          		  appG.setImg_place(foto);
		          		  
		          		  
		          		  String largura = null;
		          		  String altura = null;
		          		  String fotogrande = null;
		          		  largura = jsonR.getString("width");
		          		  altura  = jsonR.getString("height");
		          		  fotogrande = jsonR.getString("prefix")+largura+"x"+altura+jsonR.getString("suffix");
		          		  appG.setFotogrande(fotogrande);
		          		  
		          		
		          		  
		        
		          		  String usu = null;
		          		  usu = jsonR.getJSONObject("user").getJSONObject("photo").getString("prefix")+"50x50"+jsonR.getJSONObject("user").getJSONObject("photo").getString("suffix");
		          		  appG.setImg_user(usu);
		          		  
		          		  appG.setName_user(jsonR.getJSONObject("user").getString("firstName"));
		          		  
		          		  long pega = 0;
		          		  pega = Long.parseLong(jsonR.getString("createdAt"));
		          		  
		          		  
		          		  
		          		  appG.setData(DateFormat.getLongDateFormat(getApplicationContext()).format(new Date(pega * 1000L)));                 
		          		 
		          		  
		          		 
		          		  
		          		 /* 
		          		  
		          		if (i==0) {  
		          		SmartImageView place = (SmartImageView)findViewById(R.id.ima01);
		          		place.setImageUrl(appG.getImg_place());
		          		SmartImageView imau = (SmartImageView)findViewById(R.id.imausu_1);
		          	  	imau.setImageUrl(appG.getImg_user());

		          		   TextView tusu = (TextView)findViewById(R.id.tusu1);
		          		   tusu.setText(appG.getName_user());
		          		   TextView tdata = (TextView)findViewById(R.id.tdata1);
		          		   tdata.setText(appG.getData());
		          		}
		          		
		          		if (i==1) {  
			          		SmartImageView place = (SmartImageView)findViewById(R.id.ima02);
			          		place.setImageUrl(appG.getImg_place());
			          		SmartImageView imau = (SmartImageView)findViewById(R.id.imausu_2);
			          	  	imau.setImageUrl(appG.getImg_user());
		
			          		   TextView tusu2 = (TextView)findViewById(R.id.tusu2);
			          		   tusu2.setText(appG.getName_user());
			          		   TextView tdata2 = (TextView)findViewById(R.id.tdata2);
			          		   tdata2.setText(appG.getData());
			          		}
			          		
		          		
		          		
		          		
		          		  */
		          		  

		          		  result.add(appG);
		          		
		          	  
		          	  }  
		              
		        	  

		        	  
		        	  
		        	  } catch (JSONException e) {
		              	 
		              }    
	               
		        	  
		        	  
		        
		     	       
		     	       
		     	       
		        	  			     	 
			     	
			     	
			     	
			     	
			     	
			     	
			     	
			     	
			     	poegaleria();
			     	
			     	
			     	try {
				  		 
				  		

				  		 
				  		 FileOutputStream fos1 = getBaseContext().openFileOutput("fotos.dat", Context.MODE_PRIVATE);
			             ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
			             oos1.writeObject(result);
			             oos1.close();
			             
			             
			         } catch (IOException e) {
			             e.printStackTrace();
			             
			         }
	

 

			     	
		 }
		     
	   

	   
	   
	   }
	
	
	   
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
	               // i.putExtra("ende", result.get(position).getName_user()+" "+ result.get(position).getData());
	                startActivity(i);
	            }
	        });
  
        
	     //   dialog.dismiss();
	     //   acabou = true;	
        
        
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
	             imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
	             imageView.setPadding(5, 5, 5, 5);
	         } 
	         else{
	             imageView = (SmartImageView) convertView;
	         }
	         
	         imageView.setImageUrl(result.get(position).getImg_place() ) ;        
	         
	     if (position >= 0) 
	    	 
	     {
	    	 acabou = true;
	    	 dialog.dismiss();
	     }   
	         return imageView;
	     }


		
	     
	     }
}
