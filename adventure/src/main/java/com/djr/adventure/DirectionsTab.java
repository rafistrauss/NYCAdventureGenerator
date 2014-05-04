package com.djr.adventure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;


public class DirectionsTab extends ListFragment {

   // TextView mSearchResults;

    private ArrayList<Business> businesses;

	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getActivity().getIntent();
        businesses = (ArrayList<Business>) intent.getExtras().get("EXTRA_DIRECTIONS_STEPS");
        DirectionStepAdapter adapter = new DirectionStepAdapter(businesses);
        setListAdapter(adapter);
    }

    private class DirectionStepAdapter extends ArrayAdapter<Business> {
        public DirectionStepAdapter(ArrayList<Business> businesses) {
            super(getActivity(), 0, businesses);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_step, null);
            }

            // configure the view for this crime
            Business b = getItem(position);
            TextView directionText = (TextView)convertView.findViewById(R.id.direction_text);
            directionText.setText(b.getName());
            TextView directionTextInfo = (TextView)convertView.findViewById(R.id.direction_info_text);
            directionTextInfo.setText(b.getLatitude() + "," + b.getLatitude());
            return convertView;
        }
    }
}
