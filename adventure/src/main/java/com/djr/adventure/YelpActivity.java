package com.djr.adventure;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class YelpActivity extends Activity {

    private TextView mSearchResultsText;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_index);
          setContentView(R.layout.directions_fragment);
        Log.v("YelpActivity","Created YelpActivity");

        mSearchResultsText = (TextView)findViewById(R.id.searchResults);

        new AsyncTask<Void, Void, String>() {

            private Exception exception;

            @Override
            protected String doInBackground(Void... urls) {
                Yelp y = Yelp.getYelp(YelpActivity.this);
                String s = y.search("MOMA", 40.769280, -74.005185);
                Log.w("YelpActivity", "Returned string is " + s);
                try {

                    return processJson(s);
                } catch (JSONException e) {
                    return s;
                }
            }

            @Override
            protected void onPostExecute(String result) {
                mSearchResultsText.setText(result);
                setProgressBarIndeterminateVisibility(false);
                Log.w("YelpActivity", "onPostExecute called. String param 'result' is " + result);
            }
        }.execute();
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
