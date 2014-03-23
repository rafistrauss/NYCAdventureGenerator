package com.djr.adventure;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TextView;

import java.util.HashMap;

public class IndexActivity extends Activity {


	private HashMap<String,Boolean> preferenceMap;
	private TextView mParkText;
	private TextView mMuseumText;
	private TextView mShoppingText;
	private TextView mMonumentText;
	private TextView mBarsText;
	private Button mButton;
	private CheckBox barsCheckBox;
	private CheckBox parksCheckBox;
	private CheckBox museumsCheckBox;
	private CheckBox shoppingCheckBox;
	private CheckBox monumentsCheckBox;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);

        // Set up preference map
        preferenceMap = new HashMap<String, Boolean>();
        preferenceMap.put("Parks", false);
        preferenceMap.put("Shopping", false);
        preferenceMap.put("Museums", false);
        preferenceMap.put("monuments", false);
        preferenceMap.put("Bars", false);
		
		// Set CheckBoxes
		parksCheckBox = (CheckBox)findViewById(R.id.checkbox_parks);
		museumsCheckBox = (CheckBox)findViewById(R.id.checkbox_museums);
		shoppingCheckBox = (CheckBox)findViewById(R.id.checkbox_shopping);
		monumentsCheckBox = (CheckBox)findViewById(R.id.checkbox_monuments);
		barsCheckBox = (CheckBox)findViewById(R.id.checkbox_bars);
		
		// Set TextViews
		mMuseumText = (TextView)findViewById(R.id.text_museums);
		mShoppingText = (TextView)findViewById(R.id.text_shopping);
		mMonumentText = (TextView)findViewById(R.id.text_monuments);
		mParkText = (TextView)findViewById(R.id.text_park);
		mBarsText = (TextView)findViewById(R.id.text_bars);
		
		// Set Buttons 		
		mButton = (Button)findViewById(R.id.button_main);
		
		
		
		// Define Roboto font
		Typeface roboto = Typeface.createFromAsset(getAssets(), "fonts/Roboto-Regular.ttf");
		
		
		// Set fonts 
		mParkText.setTypeface(roboto);
		mMuseumText.setTypeface(roboto);
		mShoppingText.setTypeface(roboto);
		mMonumentText.setTypeface(roboto);
		mBarsText.setTypeface(roboto);
		
		// Set OnCheckListeners
		parksCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                preferenceMap.put("Parks",isChecked);
			}
		});
		museumsCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                preferenceMap.put("Museums",isChecked);
			}
		});
		shoppingCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                preferenceMap.put("Shopping", isChecked);
			}
		});
		monumentsCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                preferenceMap.put("Monuments",isChecked);
			}
		});
        barsCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                preferenceMap.put("Bars",isChecked);
            }
        });

		
		// Set onClickListeners
		
		mButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {

				Intent intent = new Intent(IndexActivity.this, RouteActivity.class);
				// Put Extras here
                intent.putExtra("EXTRA_PREFERENCE_MAP", preferenceMap);
				startActivity(intent);
			}
		});
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu items for use in the action bar
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.index_activity_actions, menu);
        return super.onCreateOptionsMenu(menu);
    }

}
