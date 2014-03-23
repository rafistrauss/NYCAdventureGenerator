package com.djr.adventure;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;



public class RouteActivity extends FragmentActivity {
	
	private TabManager mAdapter;
	private ViewPager mViewPager;
	private ActionBar actionBar;
	private String[] tabs = {"Map", "Direction"};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_route);


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
		
	}

}
