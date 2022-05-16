package com.abreuretto.findpharmacy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

public class FetchDataTaskFS extends AsyncTask<String, Void, ArrayList<String>>{

	  private final FetchDataListenerFS listener;
    private String msg;
    private String latx;
    private String lonx;
;




  public FetchDataTaskFS(FetchDataListenerFS listener) {
      this.listener = listener;
      

     
      
  }
   
  @Override
  protected ArrayList<String> doInBackground(String... params) {
      if(params == null) return null;
       
      // get url from params
      String urlF = params[0];
      //String result = null;
      
      latx =  params[1];
      lonx =  params[2];       
      
      
      ArrayList<String> result = new ArrayList<String>(2); 
      
      result.add(" ");
      result.add(" ");
      
      
      
      if (isCancelled()) 
      	
      {
      	msg = "No response from server";	
      	return result;
      }
      
      
      
      
      try {
          HttpClient clientF = new DefaultHttpClient();
          HttpGet httpgetF = new HttpGet(urlF);
          HttpResponse responseF = clientF.execute(httpgetF);
          HttpEntity entityF = responseF.getEntity();
      
          if(entityF == null) {
              msg = "No response from server";
              result.set(0," ");
             // return null;        
          }
          InputStream isF = entityF.getContent();
         // return 
          
          String convF = streamToString(isF);
          
         	result.set(0, convF);
         	//Log.d("valorori", streamToString(isF));
      }
      catch(IOException e){
          msg = "No Network Connection";
          result.set(0," ");
      }
     
      
      
      
      String consumerKey = "Nm1ctpa9bkJJF42qqoWNhQ";
      String consumerSecret = "F0zgsBBFJuEJJkb0jZjilrwc_LQ";
      String token = "e9_NDDatbNk_ycth5OA2Sh0uznGdolgm";
      String tokenSecret = "5d_VTMdkmdhk3fdjbe185xnSxwM";
      Yelp yelp = new Yelp(consumerKey, consumerSecret, token, tokenSecret);
      String response = yelp.searchCat("pharmacy", Double.parseDouble(latx), Double.parseDouble(lonx));
      result.set(1, response);
      
      
      
          
        
      return result;  

      
      
      //return result.toArray(result);
      
      //return null;
      
      
      
  }
   
  @Override
  protected void onPostExecute(ArrayList<String> sJson) {
    
  	
  	   List<ApplicationFS> apps = new ArrayList<ApplicationFS>();
  	   
  	   JSONArray  returnedVenues = null;
  	   JSONArray  returneditems = null;
  	   JSONObject respo = null;
  	   JSONArray groups = null;
  	   JSONObject group = null;
  	
  	if(sJson == null) {
          if(listener != null) listener.onFetchFailureFS(msg);
          return;
      }        
       
      
   
    	
    	
    	try
      {   
      	String four =(String)  sJson.get(0).toString();
		  JSONObject jArray = new JSONObject(four);
        try 
        {
    		respo = (JSONObject) jArray.get("response");
        } catch (JSONException e) {
            System.err.println("erro");
        }
    	
        try
        {
    		
    		
    		groups = (JSONArray) respo.get("groups");
        } catch (JSONException e) {
            System.err.println("erro1");
        }
    
    		
    	try
    	{
    		
    		group = (JSONObject)groups.get(0);
        } catch (JSONException e) {
            System.err.println("erro2");
        }
    		
    		
    		
    	
    	try
    	{
    		returneditems = (JSONArray)group.get("items");
    		
    		

        } catch (JSONException e) {
                System.err.println("erro3");
        }
        
             
          for(int i=0; i<returneditems.length(); i++) {
              JSONObject jsonF = returneditems.getJSONObject(i);
              ApplicationFS app = new ApplicationFS();
             
              
              app.setName(jsonF.getJSONObject("venue").getString("name"));
              app.setId(jsonF.getJSONObject("venue").getString("id")); 
              
              app.setReference("https://api.foursquare.com/v2/venues/"+jsonF.getJSONObject("venue").getString("id"));
             
              
              try
              {
              JSONArray foto = jsonF.getJSONObject("venue").getJSONObject("photos").getJSONArray("groups")
          	    	  .getJSONObject(0)
          	    	  .getJSONArray("items");                
              String fotox = foto.getJSONObject(0).getString("url");
              app.setUrlfoto(fotox);
              } catch (JSONException e) {
            	  app.setUrlfoto("https://irs2.4sqi.net/img/general/original/Y3TQGP054VVZKGFZUAD0CY2IY4Q3IQCMTGMO01VM4FZ05QKF.jpg");
              }
              
              try
              {
              JSONArray tips = jsonF.getJSONArray("tips");                
              String tipsx = tips.getJSONObject(0).getString("text");
              app.setTip(tipsx.trim());
              } catch (JSONException e) {
            	  app.setTip(" ");
              }              
              
              
              int rat = 0;
              int testa = 0;
              try
              {
              testa = (int)Math.round(Double.parseDouble(jsonF.getJSONObject("venue").getString("rating"))); 
              rat= (int)Math.round(testa/2);
              if (rat == 0) {rat = 1;}
              app.setRating(rat);
              } catch (JSONException e) {
            	  app.setRating(1);
              }            
              
              
              
              
              
              try
              {
              JSONArray cat = jsonF.getJSONObject("venue").getJSONArray("categories");
              JSONObject catF = cat.getJSONObject(0);
                  String iconex = catF.getJSONObject("icon").getString("prefix")+"32.png";
                  String iconey = iconex.replace("https://ss1.4sqi.net/img/categories_v2/", "https://foursquare.com/img/categories_v2/");
                  String iconew = iconey.replace("_32.png", "_bg_32.png");



                  app.setIcon(iconew);

              } catch (JSONException e) {
            	  app.setIcon("https://foursquare.com/img/categories/food/bakery_32.png");
              }
              
              
              
              
              
              
              
              String cida;
              String pais;
              String ende;
              
              
              
              
              try
              {
              cida = jsonF.getJSONObject("venue").getJSONObject("location").getString("city");
              }
              catch (JSONException e) {
              cida = " ";	
              }
              
              	
              
              try
              {
              pais = jsonF.getJSONObject("venue").getJSONObject("location").getString("cc");
              }
              catch (JSONException e) {
              pais = " ";	
              }
              
              try
              {
              ende = jsonF.getJSONObject("venue").getJSONObject("location").getString("address");
              }
              catch (JSONException e) {
              ende = " ";	
              }
              
              
              app.setVicinity(ende+" "+cida+" "+pais );
              
              
              app.setaLat(jsonF.getJSONObject("venue").getJSONObject("location").getDouble("lat"));
              app.setaLon(jsonF.getJSONObject("venue").getJSONObject("location").getDouble("lng"));
              Location locationA = new Location("point A");
              locationA.setLatitude(Double.parseDouble(latx));
              locationA.setLongitude(Double.parseDouble(lonx));
              Location locationB = new Location("point B");
              locationB.setLatitude(app.getaLat());
              locationB.setLongitude(app.getaLon());
              float distance = locationA.distanceTo(locationB);
              app.setDistance(distance);  
              app.setTipo("2");
              //app.setTipo(2);
              apps.add(app);
       
          }
          
          
      } 
  	catch (JSONException e) 
{
    msg = "Foursquare unavailable...";
    if(listener != null) listener.onFetchFailureFS(msg);
    //return;
}  
          
    
      
  	try
    {
    
    String yel = sJson.get(1);
    
    

  if (yel.indexOf("One or more parameters are invalid in request") > 0) {
    	//dialog.dismiss();
    	 //poedados();
    	return;}
    
    
    JSONObject jsonObjY = new JSONObject(yel);
    JSONArray response = jsonObjY.getJSONArray("businesses");

     
    for(int i=0; i<response.length(); i++) {
        JSONObject jsonY = response.getJSONObject(i);
       
        
        ApplicationFS appY = new ApplicationFS();
        
        
        
        try
        {
        appY.setId(jsonY.getString("id"));
        }
        catch (JSONException e) 
        {
        	appY.setId("0");
       	 }
        
       		 
        
        try {
        appY.setName(jsonY.getString("name"));
        }
        catch (JSONException e) 
        {
        	appY.setName("no name");
       	 }
        
        try {
        appY.setReference(jsonY.getString("mobile_url"));
        }
        catch (JSONException e) 
        {
        	 appY.setReference(" ");
       	 }
        
        try {
            
            int ratN = 0;
            double rat = 0;
            if (jsonY.has("rating")) {
            ratN = (int)Math.round(Double.parseDouble(jsonY.getString("rating")));
            rat = Double.parseDouble(jsonY.getString("rating"));
            
            	appY.setRating(ratN);
            	appY.setRatyelp(rat);
            } else {
            	appY.setRating(0);
            	appY.setRatyelp(0);
            }
            
            }
            catch (JSONException e) 
            {
            	appY.setRating(0);
           	 }
          try {
            
            int revi = 0;
            if (jsonY.has("review_count")) {
            revi = (int)Math.round(Integer.parseInt(jsonY.getString("review_count")));
            	appY.setReview(revi);
            	
            } else {
           	appY.setReview(0);
                   
            }
            
            }
            catch (JSONException e) 
            {
           	 appY.setReview(0);
           	 }  
        
        
        try {
        	
        
        appY.setIcon(jsonY.getString("image_url"));
        }
        catch (JSONException e) 
        {
        	appY.setIcon("http://www.abreuretto.com/privacidade/semfoto.png");
        	appY.setReference("semfoto");
       	 }
        
        try {
        	
        
        appY.setVicinity(jsonY.getJSONObject("location").getString("address")+" "+jsonY.getJSONObject("location").getString("city"));
        }
        catch (JSONException e) 
        {
        	appY.setVicinity(" ");
       	 }
        
        
        try {
        	
        
        appY.setaLat(jsonY.getJSONObject("location").getJSONObject("coordinate").getDouble("latitude"));
        appY.setaLon(jsonY.getJSONObject("location").getJSONObject("coordinate").getDouble("longitude"));
        }
        catch (JSONException e) 
        {
        	appY.setaLat(0);
        	appY.setaLon(0);
        	
       	 }
        
        
        try
        {
        String tips = jsonY.getString("snippet_text");                
        
        appY.setTip(tips.trim());
        } catch (JSONException e) {
      	  appY.setTip(" ");
        }              
        
        
        
        
        
        
        
        Location locationA = new Location("point A");
        locationA.setLatitude(Double.parseDouble(latx));
        locationA.setLongitude(Double.parseDouble(lonx));
        Location locationB = new Location("point B");
        locationB.setLatitude(appY.getaLat());
        locationB.setLongitude(appY.getaLon());
        float distance = locationA.distanceTo(locationB);
        appY.setDistance(distance); 
        appY.setTipo("4");
        
        apps.add(appY);
    }




  	
  	
      } 
  	catch (JSONException e) 
{
    msg = "Yelp unavailable...";
    if(listener != null) listener.onFetchFailureFS(msg);
    //return;
}  
  	
  	
  	
  	
      
  	
          
          
          
  	 if(listener != null) listener.onFetchCompleteFS(apps);     
          
          
          
          
          
          
          
          
          
      
      
      
      
      
      
      
  }
   
  /**
   * This function will convert response stream into json string
   * @param is respons string
   * @return json string
   * @throws IOException
   */
  
  
  
  
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
}

