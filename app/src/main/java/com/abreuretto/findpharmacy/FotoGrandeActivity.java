package com.abreuretto.findpharmacy;

import com.loopj.android.image.SmartImageView;

import android.os.Bundle;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class FotoGrandeActivity extends Activity {

	private ProgressDialog dialog;
	String foto = null;
	String ende = null;
	String lon = null;
	String nome = null;
	//private WebView webView;
	  SharedPreferences shared_preferences;
	  SharedPreferences.Editor shared_preferences_editor;
  String latx = null;
  String lonx = null;
   int qtd = 0;
   int pegando = 0;
   int conta = 0;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_foto_grande);
		
		
		  dialog = ProgressDialog.show(this, "", this.getResources().getString(R.string.carregandofotos));
		     
			
			
			Bundle extras = getIntent().getExtras();
			
			
		    if (extras != null)
		    {
		       foto = extras.getString("foto");
		       nome = extras.getString("nome");
		     //  ende = extras.getString("ende");
		       
		       TextView titleText = (TextView)findViewById(R.id.tnome);
		       titleText.setText(nome);
		       
		     //  TextView titleText2 = (TextView)findViewById(R.id.tende);
		     //  titleText2.setText(ende);
		       
		       
		       
		    }
		    else
		    {
		    	dialog.dismiss();
		    }

		    
		    SmartImageView icon = (SmartImageView)findViewById(R.id.imagem);
		    icon.setImageUrl(foto);
		    dialog.dismiss();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.foto_grande, menu);
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
