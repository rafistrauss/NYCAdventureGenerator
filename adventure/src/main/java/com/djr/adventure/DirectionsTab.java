package com.djr.adventure;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
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
    // Adapter for businesses
    private class DirectionStepAdapter extends ArrayAdapter<Business> {
        public DirectionStepAdapter(ArrayList<Business> businesses) {
            super(getActivity(), 0, businesses);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if (convertView == null) {
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_item_step, null);
            }

            // configure the view for this business
            Business b = getItem(position);
            TextView directionText = (TextView)convertView.findViewById(R.id.direction_text);
            directionText.setText(b.getName());
            TextView directionTextInfo = (TextView)convertView.findViewById(R.id.direction_info_text);
            directionTextInfo.setText(b.getAddress());
            ImageView img = (ImageView)convertView.findViewById(R.id.image_maneuver);
            img.setImageResource(getImgResource(b));
            return convertView;
        }
        // Returns Image resource to be set
        private int getImgResource(Business b) {
            String category = b.getCategory().toLowerCase();
            if (category.equals("bars")) {
                return R.drawable.cocktail;
            }
            if (category.equals("monuments")) {
                return R.drawable.monuments;
            }
            if(category.equals("parks")) {
                return R.drawable.parks;
            }
            if(category.equals("shopping")) {
                return R.drawable.shopping;
            }
            if(category.equals("museums")) {
                return R.drawable.museum;
            }
            return R.drawable.monuments;
        }
    }
}
