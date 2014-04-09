package com.djr.adventure;

import android.app.ActionBar;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class RouteActivity extends FragmentActivity {

    private HashMap<String,Boolean> preferenceMap;
    private TabManager mAdapter;
    private ViewPager mViewPager;
    private ActionBar actionBar;
    private String[] tabs = {"Map", "Direction"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);

        preferenceMap = new HashMap<String, Boolean>();
        Intent intent = getIntent();
        preferenceMap = (HashMap<String, Boolean>)intent.getSerializableExtra("EXTRA_PREFERENCES_MAP");

        // Initialize
        actionBar = getActionBar();
        mViewPager = (ViewPager)findViewById(R.id.pager);
        mAdapter = new TabManager(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);

        // Specify that tabs should be displayed in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // tab listener that is called when the user changes tabs.
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            public void onTabSelected(ActionBar.Tab tab,android.app.FragmentTransaction ft) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            public void onTabUnselected(ActionBar.Tab tab,android.app.FragmentTransaction ft) {
                // ignore
            }

            public void onTabReselected(ActionBar.Tab tab,android.app.FragmentTransaction ft) {
                // ignore
            }

        };

        // Add 2 tabs, specifying the tab's text and TabListener
        for (String tab : tabs) {
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(tab)
                            .setTabListener(tabListener));
        }



        //mSearchResults = (TextView) v.findViewById(R.id.searchResults);
        //Intent i = getActivity().getIntent();



//        HashMap<String, Boolean> params = (HashMap<String, Boolean>) i.getExtras().get("EXTRA_PREFERENCES_MAP");
        ArrayList<Business> bs = new ArrayList();
        for (String k : preferenceMap.keySet()) {
            if(preferenceMap.get(k)) {
                try {

                    YelpAsyncTask yat = new YelpAsyncTask();
                    yat.execute(k);

                    Business b = (Business) yat.get();
                    bs.add(b);

                }
                catch (Exception e) {
                    Log.d("YelpSearch", e.toString());

                }
//                   res += yelpSearch(k).toString();
            }
        }

//        Intent directionsIntent = new Intent(RouteActivity.this, DirectionsTab.class);
//        Intent mapIntent = new Intent(RouteActivity.this, MapTab.class);

//        directionsIntent.putExtra("BUSINESS_LIST", bs);
//        mapIntent.putExtra("BUSINESS_LIST", bs);
//        startActivity(directionsIntent);



        //mSearchResults.setText(res);

    }

    private class YelpAsyncTask extends AsyncTask<String, Void, Object> {
        private Exception exception;

        @Override
        protected Object doInBackground(String... terms) {
            Yelp y = Yelp.getYelp(RouteActivity.this);
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
//                mSearchResultsText.setText(result);
//                setProgressBarIndeterminateVisibility(false);
//                mSearchResults.setText(result);
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
//        JSONObject address = business.getJSONObject("location");

        String name = business.getString("name");
//        String street_address = address.getString("address");
//        String city = address.getString("city");
//        String state =  address.getString("state");
//        String country = address.getString("country_code");

        Business ret = new Business(name, lon, lat);

//        String longitude = regions.getJSONObject(0).getString("")

        return ret;


    }
}