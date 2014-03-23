package com.djr.adventure;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.GoogleMap;

public class DrawMapFragment extends Fragment {

    private Button mRedoButton;
    private Button mAcceptButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

	}

    private GoogleMap mMap;
		
	@Override 
	public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.map_fragment, parent, false);


        Typeface roboto = Typeface.createFromAsset(getActivity().getAssets(), "fonts/Roboto-Regular.ttf");

        mRedoButton = (Button)v.findViewById(R.id.button_redo);
        mAcceptButton = (Button)v.findViewById(R.id.button_accept);
        mAcceptButton.setTypeface(roboto);
        mRedoButton.setTypeface(roboto);

        mRedoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // do something when redo is pressed
                getActivity().finish();
            }
        });

        mAcceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // do something when accept is clicked
            }
        });

		return v;
	}

}
