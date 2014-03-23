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

public class IndexActivity extends Activity {
	
	private TextView mParkText;
	private TextView mMuseumText;
	private TextView mShoppingText;
	private TextView mMonumentText;
	private TextView mBarsText;
	private Button mButton;
	private boolean parks;
	private boolean shopping;
	private boolean museums;
	private boolean monuments;
	private boolean bars;
	private CheckBox barsCheckBox;
	private CheckBox parksCheckBox;
	private CheckBox museumsCheckBox;
	private CheckBox shoppingCheckBox;
	private CheckBox monumentsCheckBox;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_index);

		bars = false;
		parks = false;
		museums = false;
		monuments = false;
		
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
				setParks(isChecked);
			}
		});
		museumsCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				setMuseums(isChecked);
			}
		});
		shoppingCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				setShopping(isChecked);
			}
		});
		monumentsCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				setMonuments(isChecked);
			}
		});
       barsCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                setBars(isChecked);
            }
        });

		
		// Set onClickListeners
		
		mButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(IndexActivity.this, RouteActivity.class);
				// Put Extras here
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



    // Getters and setters
	
	public void setParks(boolean b) { parks = b; }
	
	public void setShopping(boolean b) { shopping = b; }
	
	public void setMuseums(boolean b) { museums = b; }
	
	public void setMonuments(boolean b) { monuments = b; }

    public void setBars(boolean b) { bars = b; }


}
