package com.djr.adventure;


import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RouteData {

    private HashMap<String, Boolean> mPreferenceMap;
    private ArrayList<DirectionStep> mDirectionSteps;
    private ArrayList<String> mRoute;
    private Context myContext;

    public RouteData (HashMap<String, Boolean> preferenceMap, Context context) {
        myContext = context;
        mPreferenceMap = preferenceMap;

    }


    public ArrayList<DirectionStep> getSteps() {
        return mDirectionSteps;
    }

    public ArrayList<Business> getLocations() {
        ArrayList<Business> bs = new ArrayList();
        for (String k : mPreferenceMap.keySet()) {
            if (mPreferenceMap.get(k)) {
                try {
                    YelpAsyncTask yat = new YelpAsyncTask();
                    yat.execute(k);

                    Business b = (Business) yat.get();
                    bs.add(b);
                } catch (Exception e) {
                    Log.d("YelpSearch", e.toString());
                }

            }

        }
        return bs;
    }


    private class YelpAsyncTask extends AsyncTask<String, Void, Object> {
        private Exception exception;

        @Override
        protected Object doInBackground(String... terms) {
            Yelp y = Yelp.getYelp(RouteData.this.myContext);
            String s = y.search(terms[0], 40.769280, -74.005185);
            Log.v("YelpActivity", "Returned string is " + s);
            try {
                return getRandBusiness(s);
            } catch (JSONException e) {
                Log.d("YelpAsyncTask", "reached json exception" + e.getStackTrace().toString());
                return e.toString();
            }
        }

        protected void onPostExecute(Business result) {
            Log.w("YelpActivity", "onPostExecute called. String param 'result' is " + result.toString());

        }

        Business getRandBusiness(String jsonStuff) throws JSONException {

            int length = jsonStuff.length();
            Random rand = new Random();
            int randomBusiness = rand.nextInt(length < 5 ? length : 5);


            JSONObject json = new JSONObject(jsonStuff);
            JSONArray businesses = json.getJSONArray("businesses");


            Log.d("RAND_BUSINESS", "before getjsonobject on randomBusiness");

            JSONObject business = businesses.getJSONObject(randomBusiness);


            Log.d("RAND_BUSINESS", "before getjsonobject on location");

            JSONObject location = business.getJSONObject("location");

            Log.d("RAND_BUSINESS", "before the toStrings on the getJsonObject");

            String address = location.getJSONArray("address").get(0).toString();

            Log.d("RAND_BUSINESS", "address=" + address);

            String city = location.getString("city");

            Log.d("RAND_BUSINESS", "city=" + city);

            String zip = location.getString("postal_code");
            Log.d("RAND_BUSINESS", "zip=" + zip);

            String state = location.getString("state_code");

            Log.v("BUSINESS_LOCATION", address + " " + city + " " + state + " " + zip);

            Geocoder coder = new Geocoder(RouteData.this.myContext);
            try {
                Address bAddress = coder.getFromLocationName(address + " " + city + " " + state + " " + zip, 1).get(0);
                double lat = bAddress.getLatitude();
                double lon = bAddress.getLongitude();


                String name = business.getString("name");

                Business ret = new Business(name, lon, lat);

                return ret;

            } catch (Exception ex){
                Log.e("GEOCODING", ex.getStackTrace().toString());
            }

            return null;//PANIC
        }
    }

}