package com.djr.adventure;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
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
import java.util.HashMap;
import java.util.Random;


public class DirectionsTab extends ListFragment {

    TextView mSearchResults;
    private ArrayList<DirectionStep> steps;

	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.directions_fragment, parent, false);
        mSearchResults = (TextView) v.findViewById(R.id.searchResults);
        Intent i = getActivity().getIntent();
        // get Direction Steps
        ArrayList<DirectionStep> steps = (ArrayList<DirectionStep>)i.getExtras().get("EXTRA_DIRECTION_STEPS");


            HashMap<String, Boolean> params = (HashMap<String, Boolean>) i.getExtras().get("EXTRA_PREFERENCES_MAP");
            String res = "";
            for (String k : params.keySet()) {
                if(params.get(k)) {
                  try {


                      YelpAsyncTask yat = new YelpAsyncTask();
                      yat.execute(k);

                      String r = yat.get();
                      res += r + "\n";

                  }
                  catch (Exception e) {
                      Log.d("YelpSearch", e.toString());

                  }
//                   res += yelpSearch(k).toString();
                }
            }

        mSearchResults.setText(res);





//       mSearchResults = (TextView) v.findViewById(R.id.searchResults);
//        new AsyncTask<Void, Void, String>() {
//
//            private Exception exception;
//
//            @Override
//            protected String doInBackground(Void... urls) {
//                Yelp y = Yelp.getYelp(getActivity());
//                String s = y.search("MOMA", 40.769280, -74.005185);
//                Log.w("YelpActivity", "Returned string is " + s);
//                try {
//                      return getFirstBusiness(s).toString();
////                    return processJson(s);
//                } catch (JSONException e) {
//                    return s;
//                }
//            }
//
//            @Override
//            protected void onPostExecute(String result) {
////                mSearchResultsText.setText(result);
////                setProgressBarIndeterminateVisibility(false);
//                mSearchResults.setText(result);
//                Log.w("YelpActivity", "onPostExecute called. String param 'result' is " + result);
//            }
//        }.execute();

		return v;
	}

    private class YelpAsyncTask extends AsyncTask<String, Void, String> {
        private Exception exception;

        @Override
        protected String doInBackground(String... terms) {
            Yelp y = Yelp.getYelp(getActivity());
            String s = y.search(terms[0], 40.769280, -74.005185);
            Log.w("YelpActivity", "Returned string is " + s);
            try {
                return getRandBusiness(s).toString();
//                    return processJson(s);
            } catch (JSONException e) {
                return s;
            }
        }

        @Override
        protected void onPostExecute(String result) {
//                mSearchResultsText.setText(result);
//                setProgressBarIndeterminateVisibility(false);
//                mSearchResults.setText(result);
            Log.w("YelpActivity", "onPostExecute called. String param 'result' is " + result);


        }
    }

//    Business yelpSearch(String searchTerm) {
//        new AsyncTask<String, Void, String>() {
//
//            private Exception exception;
//
//            @Override
//            protected String doInBackground(String... terms) {
//                Yelp y = Yelp.getYelp(getActivity());
//                String s = y.search(terms[0], 40.769280, -74.005185);
//                Log.w("YelpActivity", "Returned string is " + s);
//                try {
//                    return getRandBusiness(s).toString();
////                    return processJson(s);
//                } catch (JSONException e) {
//                    return s;
//                }
//            }
//
//            @Override
//            protected void onPostExecute(String result) {
////                mSearchResultsText.setText(result);
////                setProgressBarIndeterminateVisibility(false);
////                mSearchResults.setText(result);
//                Log.w("YelpActivity", "onPostExecute called. String param 'result' is " + result);
//
//
//            }
//        }.execute();
//
//    }

    Business getRandBusiness (String jsonStuff) throws JSONException {

        int length = jsonStuff.length();
        Random rand = new Random();
        int randomBusiness = rand.nextInt(length < 5 ? length : 5);

        JSONObject json = new JSONObject(jsonStuff);
        JSONArray businesses = json.getJSONArray("businesses");
        JSONArray regions = json.getJSONArray("region");
        JSONObject business = businesses.getJSONObject(randomBusiness);
        JSONObject address = business.getJSONObject("location");

        String name = business.getString("name");
        String street_address = address.getString("address");
        String city = address.getString("city");
        String state =  address.getString("state");
        String country = address.getString("country_code");

        Business ret = new Business(name, street_address, city, state, country);

//        String longitude = regions.getJSONObject(0).getString("")

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
