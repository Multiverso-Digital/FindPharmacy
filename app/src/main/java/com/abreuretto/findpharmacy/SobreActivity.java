package com.abreuretto.findpharmacy;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;

public class SobreActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sobre);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sobre, menu);
		return true;
	}
	
	
	  public void privaClick(View view) {  
	  	  //Implement image click function 
		  
	      Intent intent = new Intent(this, MostraWebActivity.class);
	        startActivity(intent);
		  
	  }

}
