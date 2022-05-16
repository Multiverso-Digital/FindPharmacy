package com.abreuretto.findpharmacy;

import java.io.File;
import java.util.Locale;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

public class MostraWebActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mostra_web);
		
		 WebView lWebView = (WebView)findViewById(R.id.webView1);
		 // File lFile = new File("assets/politica_privacidade_en-US.html");
		 
		 Locale l = Locale.getDefault();

		 String lang = l.getISO3Language().toString();
		 
		 if (lang.equals("eng"))  {
		 
		  lWebView.loadUrl("file:///android_asset/politica_privacidade_en-US.html");		
		  
		 }
		  
		 if (lang.equals("por")) {
			 
			  lWebView.loadUrl("file:///android_asset/politica_privacidade_pt-BR.html");
			  
			 }
			 
		 if (lang.equals("spa")) {
			 
			  lWebView.loadUrl("file:///android_asset/politica_privacidade_es-ES.html");
			  
			 }
		 
		 if (lang.equals("zho")) {
			 
			  lWebView.loadUrl("file:///android_asset/politica_privacidade_zh-CH.html");
			  
			 }
		 
		 if (lang.equals("jpn")) {
			 
			  lWebView.loadUrl("file:///android_asset/politica_privacidade_ja-JP.html");
			  
			 }
		 if (lang.equals("fra")) {
			 
			  lWebView.loadUrl("file:///android_asset/politica_privacidade_fr-FR.html");
			  
			 }
		 if (lang.equals("rus")) {
			 
			  lWebView.loadUrl("file:///android_asset/politica_privacidade_ru-RU.html");
			  
			 }
		 
		 if (lang.equals("ara")) {
			 
			  lWebView.loadUrl("file:///android_asset/politica_privacidade_ar-AR.html");
			  
			 }
		 
		 if (lang.equals("hin")) {
			 
			  lWebView.loadUrl("file:///android_asset/politica_privacidade_hi-HI.html");
			  
			 }
		 
		 
		 if (lang.equals("kor")) {
			 
			  lWebView.loadUrl("file:///android_asset/politica_privacidade_ko-KO.html");
			  
			 }
		 if (lang.equals("deu")) {
			 
			  lWebView.loadUrl("file:///android_asset/politica_privacidade_de-DE.html");
			  
			 }
		 
		 
		 
if ((lang.equals("spa")) && (lang.equals("por")) && (lang.equals("eng")) && (lang.equals("zho")) && (lang.equals("jpn")) && (lang.equals("fra")) && (lang.equals("rus")) && (lang.equals("deu"))&& (lang.equals("kor")) )  {
		 
			 lWebView.loadUrl("file:///android_asset/politica_privacidade_en-US.html");	
		 
		 }
		 
		 
		 
		
	}

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
