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
 
public class FetchDataTask extends AsyncTask<String, Void, ArrayList<String>>{

	  private final FetchDataListener listener;
      private String msg;
      private String latx;
      private String lonx;
;
  
  

  
    public FetchDataTask(FetchDataListener listener) {
        this.listener = listener;
        

       
        
    }
     
    @Override
    protected ArrayList<String> doInBackground(String... params) {
        if(params == null) return null;
         
        // get url from params
        String urlG = params[0];
        String urlF = params[1];
        String urlN = params[2];
        
        latx =  params[3];
        lonx =  params[4];
        
        ArrayList<String> result = new ArrayList<String>(4); 
       
        
        if (isCancelled()) 
        	
        {
        	msg = "No response from server";	
        	return result;
        }
        
        
        result.add(" ");
        result.add(" ");
        result.add(" ");
        result.add(" ");
        
        
        
        
       
        
        
        


        
        
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
            
           	result.set(0,convF);
           	//Log.d("valorori", streamToString(isF));
        }
        catch(IOException e){
            msg = "No Network Connection";
            result.set(0," ");
        }
       
        
        
        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet httpget = new HttpGet(urlG);
            HttpResponse response = client.execute(httpget);
            HttpEntity entity = response.getEntity();
        
            if(entity == null) {
                msg = "No response from server";
                result.set(1," ");
               // return null;        
            }
            InputStream is = entity.getContent();
           // return 
            String conv = streamToString(is);
           	result.set(1,conv);
        }
        catch(IOException e){
            msg = "No Network Connection";
            result.set(1," ");
        }
        
       
         
        
        
        
        
        
        try {
            HttpClient clientN = new DefaultHttpClient();
            HttpGet httpgetN = new HttpGet(urlN);
            HttpResponse responseN = clientN.execute(httpgetN);
            HttpEntity entityN = responseN.getEntity();
        
            if(entityN == null) {
                msg = "No response from server";
                result.set(2," ");
               // return null;        
            }
            InputStream isN = entityN.getContent();
           // return 
           	result.set(2,streamToString(isN));
        }
        catch(IOException e){
            msg = "No Network Connection";
            result.set(2," ");
        }
        
        
           
        
        
        
        String consumerKey = "Nm1ctpa9bkJJF42qqoWNhQ";
        String consumerSecret = "F0zgsBBFJuEJJkb0jZjilrwc_LQ";
        String token = "e9_NDDatbNk_ycth5OA2Sh0uznGdolgm";
        String tokenSecret = "5d_VTMdkmdhk3fdjbe185xnSxwM";
        Yelp yelp = new Yelp(consumerKey, consumerSecret, token, tokenSecret);
        String response = yelp.searchCat("drugstores", Double.parseDouble(latx), Double.parseDouble(lonx));        
        result.set(3, response);
        
        
        
        
        
        
        
        
        
        
          
        return result;  

        
        
        //return result.toArray(result);
        
        //return null;
        
        
        
    }
     
    @Override
    protected void onPostExecute(ArrayList<String> sJson) {
        
    	
  	   List<Application> apps = new ArrayList<Application>();
  	   JSONArray  returnedVenues = null;
  	   JSONArray  returnedVenuesN = null;
  	   
  	   String Tipox = "no category";
  	   
  	
  	if(sJson == null) {
          if(listener != null) listener.onFetchFailure(msg);
          return;
      }        
       
      
      
  
  	try
      {   
      	String four =(String) sJson.get(0).toString();
      	Tipox = "no category";
 		
 		//  Log.d( "Valor", four );
 		
 		  
 		  JSONObject jArray = new JSONObject(four);

        JSONObject response = jArray.getJSONObject("response");
        try {
      	    returnedVenues = response.getJSONArray("venues");
        } catch (JSONException e) {
                System.err.println(response.toString());
        }

 		  
      	
           
          for(int i=0; i<returnedVenues.length(); i++) {
  
         	 
         	 
         	 JSONObject jsonF = returnedVenues.getJSONObject(i);
             
              Application appF = new Application();
              
              
              try {
              appF.setId(jsonF.getString("id"));
              }
              catch (JSONException e) {
             	 appF.setId("0");	
              }
              
              
         try{     
              appF.setName(jsonF.getString("name"));
          }
          catch (JSONException e) {
         	 appF.setName("no name");	
          }
         
              
              try
              {
              appF.setReference("https://api.foursquare.com/v2/venues/"+jsonF.getString("id"));
              }
              catch (JSONException e) {
             	 appF.setReference("https://api.foursquare.com/v2/venues/");	
              }
                 
              
              
              try
              {
              
              if (jsonF.has("stats")) {
              int testa = (jsonF.getJSONObject("stats").getInt("checkinsCount"));
              
              
              int ratF=0;
              if (testa <= 1) {	ratF = 0;}
              if ((testa > 1) && (testa <= 1000)) {	ratF = 1;}
              if ((testa > 1000) && (testa <= 5000)) {	ratF = 2;}
              if ((testa > 5000) && (testa <= 15000)) {	ratF = 3;}
              if ((testa > 15000) && (testa <= 50000)) {	ratF = 4;}
              if (testa > 50000)  {	ratF = 5;}
              
              
              appF.setRating(testa);
              
              
              
              } else
              {
              	appF.setRating(0);
              }
              }
              catch (JSONException e) {
             	 appF.setRating(0);	
              }
              
              
              
              
              
              try
              {
              
              int espe = (jsonF.getJSONObject("specials").getInt("count"));
              appF.setEspecial(espe);
              }
              catch (JSONException e) {
             	 appF.setEspecial(0);	
              }
              
              
              try
              {
              
              JSONArray cat = jsonF.getJSONArray("categories");
              JSONObject catF = cat.getJSONObject(0);



                  String iconex = catF.getJSONObject("icon").getString("prefix")+"32.png";
                  String iconey = iconex.replace("https://ss1.4sqi.net/img/categories_v2/", "https://foursquare.com/img/categories_v2/");
                  String iconew = iconey.replace("_32.png", "_bg_32.png");
                  appF.setIcon(iconew);
              
              }
              catch (JSONException e) {
             	 appF.setIcon(" ");	
              }
              
              
              
              
              String cida;
              String pais;
              String ende;
              
              
              
              
              try
              {
              cida = jsonF.getJSONObject("location").getString("city");
              }
              catch (JSONException e) {
              cida = " ";	
              }
              
              	
              
              try
              {
              pais = jsonF.getJSONObject("location").getString("cc");
              }
              catch (JSONException e) {
              pais = " ";	
              }
              
              try
              {
              ende = jsonF.getJSONObject("location").getString("address");
              }
              catch (JSONException e) {
              ende = " ";	
              }
              
              
              
              try
              {
              
              
              JSONArray aJsona = jsonF.getJSONArray("categories");
              Tipox = "no category";
              
              for(int j=0; j<aJsona.length(); j++) {
                  
              	 JSONObject jsona = aJsona.getJSONObject(0);
              
              Tipox = jsona.getString("name");
              
              }
              	
              
              }
              catch (JSONException e) {
             	 Tipox = "no category";	
              }
              
              
              
              
              
              
              
              appF.setVicinity(ende+" "+cida+" "+pais );
              
              
              
              try
              {
              appF.setaLat(jsonF.getJSONObject("location").getDouble("lat"));
              appF.setaLon(jsonF.getJSONObject("location").getDouble("lng"));
              }
              catch (JSONException e) {
             	 appF.setaLat(0);
             	 appF.setaLon(0);
             	 
              }
              
              
              
              
              
              Location locationA = new Location("point A");
              locationA.setLatitude(Double.parseDouble(latx));
              locationA.setLongitude(Double.parseDouble(lonx));
              Location locationB = new Location("point B");
              locationB.setLatitude(appF.getaLat());
              locationB.setLongitude(appF.getaLon());
              float distance = locationA.distanceTo(locationB);
              appF.setDistance(distance);  
              appF.setTipo(2);
              
              
               String tipo = Tipox.toLowerCase();
              
               if  (0==0)
                     { 

              
              apps.add(appF);
       
              
                   }
              
              
            
              
              
              
              
              
              
          }
          
          
       //   if(listener != null) listener.onFetchComplete(apps);
          
      } catch (JSONException e) {
          msg = "Foursquare unavailable...";
          if(listener != null) listener.onFetchFailure(msg);
          //return;
      }   

      
      
      
      try {
          
      	String google = sJson.get(1).toString();
      	
      	JSONObject jsonObj = new JSONObject(google);
      	JSONArray aJson = jsonObj.getJSONArray("results");
       
           
          for(int i=0; i<aJson.length(); i++) {
             
         	 
         	 JSONObject json = aJson.getJSONObject(i);
             
              
              Application appG = new Application();
              
              
              try
              {
              appG.setId(json.getString("id"));
              }
              catch (JSONException e) {
             	 appG.setId("0"); 
              }
              
              
              
              
              try
              {
              appG.setName(json.getString("name"));
              }
              catch (JSONException e) {
             	 appG.setName("no name"); 
              }
              
              
              try
              {
              appG.setReference("https://maps.googleapis.com/maps/api/place/details/json?reference="+json.getString("reference"));
              }
              catch (JSONException e) {
             	 appG.setReference("https://maps.googleapis.com/maps/api/place/details/json?reference=0");
              }
              
              
              
              try
              {
              
              
              int ratG = 0;
              if (json.has("rating")) {
              ratG = (int)Math.round(Double.parseDouble(json.getString("rating")));                	
              	appG.setRating(ratG);
              } else {
              	appG.setRating(0);
              }
              }
              catch (JSONException e) {
             	 appG.setRating(0);
              }
         
         
              
              
              
              try
              {
              
              JSONArray aJsona = json.getJSONArray("types");
              Tipox = "no category";
              
              for(int j=0; j<aJsona.length(); j++) {
                  String jsona = aJsona.getString(0);
              
              Tipox = jsona;
              
              }
              }
              catch (JSONException e) {
             	 Tipox = "no category";
              }
              
              
              
              try
              {
              		
              appG.setIcon(json.getString("icon"));
              }
              catch (JSONException e) {
             	 appG.setIcon(" ");
              }
              
              try {
              appG.setVicinity(json.getString("vicinity"));
              }
              catch (JSONException e) {
             	 appG.setVicinity(" ");
              }
              
              
              try
              {
              
              appG.setaLat(json.getJSONObject("geometry").getJSONObject("location").getDouble("lat"));
              appG.setaLon(json.getJSONObject("geometry").getJSONObject("location").getDouble("lng"));
              }
              catch (JSONException e) {
             	 appG.setaLat(0);
             	 appG.setaLon(0);
             	 
              }
              
              
              
              
              Location locationA = new Location("point A");
              locationA.setLatitude(Double.parseDouble(latx));
              locationA.setLongitude(Double.parseDouble(lonx));
              Location locationB = new Location("point B");
              locationB.setLatitude(appG.getaLat());
              locationB.setLongitude(appG.getaLon());
              float distance = locationA.distanceTo(locationB);
              appG.setDistance(distance);  
              appG.setTipo(1);
              
              
              
               String tipo = Tipox.toLowerCase();
              
               if  (0==0)
                     { 


              
              
              apps.add(appG);
              
              
              
                   }
              
              
          }
          
          
        //  if(listener != null) listener.onFetchComplete(apps);
          
          } catch (JSONException e) {
            msg = "Google Places unavailable...";
             if(listener != null) listener.onFetchFailure(msg);
             //return;
          }   
      
      
              
          
          try
          {
          
          String nokia = sJson.get(2);
          JSONObject jsonObjN = new JSONObject(nokia);
          JSONObject response = jsonObjN.getJSONObject("results");
         
          
          
          
          try {
        	    returnedVenuesN = response.getJSONArray("items");
          } catch (JSONException e) {
                  System.err.println(response.toString());
          }
         // List<Application> apps = new ArrayList<Application>();
           
          for(int i=0; i<returnedVenuesN.length(); i++) {
              
         	 
         	 JSONObject jsonN = returnedVenuesN.getJSONObject(i);
             
              
              Application app = new Application();
              
              
              try {
              
             	 app.setId(jsonN.getString("id"));
              
              } catch (JSONException e) 
              {
             	 app.setId("0");
              }
             		 
              
              try {
                  
              app.setName(jsonN.getString("title"));
              } catch (JSONException e) 
              {
             	 app.setName("no name");
              }
             
              
              
              
              try {
              app.setReference(jsonN.getString("href"));
              } catch (JSONException e) 
              {
             	 app.setReference("no href");
              }
             		 
             		 
             		 
              try {
             	 
              
              int ratN = 0;
              if (jsonN.has("averageRating")) {
              ratN = (int)Math.round(Double.parseDouble(jsonN.getString("averageRating")));                	
              	app.setRating(ratN);
              } else {
              	app.setRating(0);
              }
              
              } 
              
              catch (JSONException e) 
              {
              app.setRating(0);
              }
             
              
              try
              {             
              app.setIcon(jsonN.getString("icon"));
              }
              catch (JSONException e) 
              {
             	 app.setIcon("");
              }
              
              
              
              try
              {
                            String testa = jsonN.getString("vicinity");
              String testax = testa.replaceAll("<br/>", " ");
              app.setVicinity(testax);
              }
              catch (JSONException e) 
              {
             	 app.setVicinity("");
              }
              
              
              Tipox = "no category";
              
              try
              {
              Tipox = jsonN.getJSONObject("category").getString("id");
              }
              catch (JSONException e) 
              {
              	Tipox = "no category";	
              }
              
              
              
              try{
              app.setaLat(Double.parseDouble(jsonN.getJSONArray("position").getString(0)));
              app.setaLon(Double.parseDouble(jsonN.getJSONArray("position").getString(1)));
              }
              catch (JSONException e) 
              {
             	 app.setaLat(0);
             	 app.setaLon(0);
             	 
              }
              
             		 
             		 
             		 
              
              String tipo = Tipox.toLowerCase();
              
              if  (0==0)
                     { 


                       
              
              
              
              Location locationA = new Location("point A");
              locationA.setLatitude(Double.parseDouble(latx));
              locationA.setLongitude(Double.parseDouble(lonx));
              Location locationB = new Location("point B");
              locationB.setLatitude(app.getaLat());
              locationB.setLongitude(app.getaLon());
              float distance = locationA.distanceTo(locationB);
              app.setDistance(distance);  
              app.setTipo(3);
              apps.add(app);
              
                   }
          }
         
         } catch (JSONException e) {
             msg = "Nokia Here unavailable...";
             if(listener != null) listener.onFetchFailure(msg);
             //return;
         }   
         

             
             
             
             try
             {
             
             String yel = sJson.get(3);
             JSONObject jsonObjY = new JSONObject(yel);
             JSONArray response = jsonObjY.getJSONArray("businesses");
     
              
             for(int i=0; i<response.length(); i++) {
                 JSONObject jsonY = response.getJSONObject(i);
                
                 
                 Application appY = new Application();
                 
                 
                 
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
                 	
                	 
                String monta = jsonY.getJSONObject("location").getString("address")+" "+jsonY.getJSONObject("location").getString("city");	 
                String montax = monta.replace("[\"", " ");
                String montay = montax.replace("\"]", " ");
                
                 
                 appY.setVicinity(montay);
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
                 
                 
                 
                 Location locationA = new Location("point A");
                 locationA.setLatitude(Double.parseDouble(latx));
                 locationA.setLongitude(Double.parseDouble(lonx));
                 Location locationB = new Location("point B");
                 locationB.setLatitude(appY.getaLat());
                 locationB.setLongitude(appY.getaLon());
                 float distance = locationA.distanceTo(locationB);
                 appY.setDistance(distance);  
                 appY.setTipo(4);
                 apps.add(appY);
             }
         
         
         } catch (JSONException e) {
             msg = "Yelp unavailable...";
             if(listener != null) listener.onFetchFailure(msg);
             //return;
         }   
         
         
             if(listener != null) listener.onFetchComplete(apps);
         
         
         
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
}
