package com.abreuretto.findpharmacy;

import java.util.Locale;




import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebTipsActivity extends Activity {

	
	String refe = null;
	String lat = null;
	String lon = null;
	String nome = null;
	String id = null;
	String foto = null;
	String tipo = null;
	String url;
	int conta = 0;
	boolean acabou = false; 
	private WebView webview ;
	AlertDialogManager alert = new AlertDialogManager();
	 ConnectionDetector cd;
	 private ProgressDialog dialog;
		private Handler handler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
		
		setContentView(R.layout.activity_web_tips); 
		//WebView lWebView = (WebView)findViewById(R.id.webView1);
		//WebView.setWebViewClient(new WebViewClient());
		 // File lFile = new File("assets/politica_privacidade_en-US.html");
		 
		
		
		
		cd = new ConnectionDetector(getApplicationContext());
		 
        // Check for internet connection
        if (!cd.isConnectingToInternet()) {
            // Internet Connection is not present
            alert.showAlertDialog(WebTipsActivity.this, this.getResources().getString(R.string.interneterror),
            		this.getResources().getString(R.string.interneterrorpede), false);
            // stop executing code by return
            return;
        }
		
		
        Bundle extras = getIntent().getExtras();
		
		
		if (extras == null)
		{
			
			
    	   return;	
			
			
		}
	       refe = extras.getString("ref");
	       id = extras.getString("id");
	       tipo = extras.getString("tipo");
		
		if (tipo.equals("1")) setTitle(R.string.google);
		if (tipo.equals("2")) setTitle(R.string.foursquare);
		if (tipo.equals("3")) setTitle(R.string.nokia);
		if (tipo.equals("4")) setTitle(R.string.yelp);
		

		
		 
		
		
		
		webview =(WebView)findViewById(R.id.webView1);

		webview.setWebViewClient(new WebViewClient()    
		
		
		
		

		
		
		
		
		{

			@Override

			public void onPageFinished(WebView view, String url) {

			super.onPageFinished(view, url);
			acabou = true;
			dialog.dismiss();

			}

			});
		
		
				
		
		
		webview.getSettings().setJavaScriptEnabled(true);
		webview.getSettings().setDomStorageEnabled(true);      
		//WebView.loadUrl("http://www.google.com"); 

		
		
		dialog = ProgressDialog.show(this, "", "Wait..."); 
		
		 acabou = false;
	   	 conta = 0;
	        handler.postDelayed(runnable, 1000); 
		
		
		
		 Locale l = Locale.getDefault();

		 String lang = l.getISO3Language().toString();
		 
		/*
		 Bundle extras = getIntent().getExtras();
			
			
			if (extras == null)
			{
				
				
	    	   return;	
				
				
			}
		       refe = extras.getString("ref");
		       id = extras.getString("id");
		       tipo = extras.getString("tipo");
		 
		 */
		 
		 if (tipo.equals("2")) 
		 {
			 url = "https://foursquare.com/v/"+id+"?client_id=WYVK3C5JYYSKWLYKMHZFQUAGZYXRBAFXMLARQHLJBFFC05IX&client_secret=GJO5FLB20ZI03LLYBEBST55NI5OMLPG4IRIHXRBPLYB1S4M3&v=20121116";
		 	 webview.loadUrl("https://foursquare.com/v/"+id+"?client_id=WYVK3C5JYYSKWLYKMHZFQUAGZYXRBAFXMLARQHLJBFFC05IX&client_secret=GJO5FLB20ZI03LLYBEBST55NI5OMLPG4IRIHXRBPLYB1S4M3&v=20121116");		
		 }
		 
		 if (tipo.equals("4")) 
		 {
			 
		     url = refe;
			 webview.loadUrl(refe);		
		 }
		 
		 if (tipo.equals("3")) 
		 {
			 
		     url = "http://m.nokia.me/p?pid="+id;
			 webview.loadUrl(url);		
		 }
		 
		
		 if (tipo.equals("1")) 
		 {
			 
		     url = refe;
		     
		     String monta = url.replace("https://plus.google.com/", "https://plus.google.com/app/basic/local/");
		     
		     
			 webview.loadUrl(monta);		
		 }
		 
		 
		 
		 
		
	}
	
	
	
	
	
	
	
	
	

	
	
private Runnable runnable = new Runnable() {
    	
    	
    	
    	@Override
    	   public void run() {
    	      /* do what you need to do */
    	      conta = conta + 1;
    	      if (acabou == true) {return;}
    	      if ((conta > 30) && (acabou == false)) 
    	      
    	      {    	      
    	    	  dialog.dismiss();
    	    	  //task.cancel(true);
    	    		 //dialog = ProgressDialog.show(DetalheFSActivity, "", DetalheFSActivity.getResources().getString(R.string.interneterror));
    	    		 finish() ;
    	    	  finish();
    	      }	
    	    	  else
    	      {
    	      handler.postDelayed(this, 1000);
              }
    	   }
    	};
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mostra_web, menu);
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
