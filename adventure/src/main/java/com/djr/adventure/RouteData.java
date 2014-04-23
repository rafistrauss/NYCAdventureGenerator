package com.djr.adventure;


import android.content.Context;
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
            Log.w("YelpActivity", "Returned string is " + s);
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
            JSONObject center = json.getJSONObject("region").getJSONObject("center");
            double lat = center.getDouble("latitude");
            double lon = center.getDouble("longitude");

            JSONObject business = businesses.getJSONObject(randomBusiness);

            String name = business.getString("name");

            Business ret = new Business(name, lon, lat);

            return ret;


        }
    }

}