package com.djr.adventure;

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

public class DirectionsFragment extends Fragment {

    TextView mSearchResults;


	@Override
	public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.directions_fragment, parent, false);

        mSearchResults = (TextView) v.findViewById(R.id.searchResults);

        new AsyncTask<Void, Void, String>() {

            private Exception exception;

            @Override
            protected String doInBackground(Void... urls) {
                Yelp y = Yelp.getYelp(getActivity());
                String s = y.search("MOMA", 40.769280, -74.005185);
                Log.w("YelpActivity", "Returned string is " + s);
                try {
                      return getFirstBusiness(s).toString();
//                    return processJson(s);
                } catch (JSONException e) {
                    return s;
                }
            }

            @Override
            protected void onPostExecute(String result) {
//                mSearchResultsText.setText(result);
//                setProgressBarIndeterminateVisibility(false);
                mSearchResults.setText(result);
                Log.w("YelpActivity", "onPostExecute called. String param 'result' is " + result);
            }
        }.execute();

		return v;
	}

    Business getFirstBusiness (String jsonStuff) throws JSONException {
        JSONObject json = new JSONObject(jsonStuff);
        JSONArray businesses = json.getJSONArray("businesses");
        JSONArray regions = json.getJSONArray("region");
        JSONObject business = businesses.getJSONObject(0);
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
