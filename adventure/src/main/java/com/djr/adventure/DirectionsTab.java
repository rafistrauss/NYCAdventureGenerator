package com.djr.adventure;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Random;


public class DirectionsTab extends Fragment {

    TextView mSearchResults;


	@Override
	public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);


	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.directions_fragment, parent, false);
        mSearchResults = (TextView) v.findViewById(R.id.searchResults);
        Intent i = getActivity().getIntent();
        ArrayList<Business> businesses = (ArrayList<Business>)i.getExtras().get("EXTRA_DIRECTIONS_STEPS");

        String ret = "";
        for (Business b : businesses) {
            ret+= b.toString() + "\n";
        }
        mSearchResults.setText(ret);



/*
            HashMap<String, Boolean> params = (HashMap<String, Boolean>) i.getExtras().get("EXTRA_PREFERENCES_MAP");
            String res = "";
            for (String k : params.keySet()) {
                if(params.get(k)) {
                  try {


                      YelpAsyncTask yat = new YelpAsyncTask();
                      yat.execute(k);

                      Business b = (Business) yat.get();
                      res += b.toString() + "\n";

                  }
                  catch (Exception e) {
                      Log.d("YelpSearch", e.toString());

                  }
                }
            }

        mSearchResults.setText(res);
*/
		return v;
	}

    private class YelpAsyncTask extends AsyncTask<String, Void, Object> {
        private Exception exception;

        @Override
        protected Object doInBackground(String... terms) {
            Yelp y = Yelp.getYelp(getActivity());
            String s = y.search(terms[0], 40.769280, -74.005185);
            Log.w("YelpActivity", "Returned string is " + s);
            try {
                return getRandBusiness(s);
//                    return processJson(s);
            } catch (JSONException e) {
                Log.d("YelpAsyncTask", "reached json exception" + e.getStackTrace().toString());
                return e.toString();
            }
        }

        protected void onPostExecute(Business result) {
            Log.w("YelpActivity", "onPostExecute called. String param 'result' is " + result.toString());


        }
    }


    Business getRandBusiness (String jsonStuff) throws JSONException {

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

    String processJson(String jsonStuff) throws JSONException {
        JSONObject json = new JSONObject(jsonStuff);
        JSONArray businesses = json.getJSONArray("businesses");
        ArrayList<String> businessNames = new ArrayList<String>(businesses.length());
        for (int i = 0; i < businesses.length(); i++) {
            JSONObject business = businesses.getJSONObject(i);
            businessNames.add(business.getString("name"));
        }
        return TextUtils.join("\n", businessNames);
    }
		
	
}
