package com.djr.adventure;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class MapTab extends Fragment {

    private Button mRedoButton;
    private Button mAcceptButton;
    private GoogleMap mMap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.map_fragment, parent, false);


        Typeface roboto = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");

        setUpMapIfNeeded();


        Intent i = getActivity().getIntent();
        ArrayList<Business> businesses = (ArrayList<Business>)i.getExtras().get("EXTRA_MAP_LOCATIONS");

        for (Business b : businesses) {
            mMap.addMarker(new MarkerOptions().position(new LatLng(b.getLatitude(), b.getLongitude())).title(b.getName()));
            Log.v("MAPTAB_LOCATIONS", b.toString());
        }
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(40.757110, -73.989680), 12.0f));

        //mMap.addMarker(new MarkerOptions().position(new LatLng(40.769280, -74.005185)).title("New York City!"));

        return v;
    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            mMap = ((SupportMapFragment) getFragmentManager().findFragmentById(R.id.map)).getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                // The Map is verified. It is now safe to manipulate the map.

            }
        }
    }

}