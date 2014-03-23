package com.djr.adventure;

import android.app.ActionBar;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;


public class RouteActivity extends FragmentActivity {
	
	private TabManager mAdapter;
	private ViewPager mViewPager;
	private ActionBar actionBar;
	private String[] tabs = {"Map", "Direction"};
    private GoogleMap mMap;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_route);


        setUpMapIfNeeded();

        // Initialize
        actionBar = getActionBar();
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mAdapter = new TabManager(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);

        // Specify that tabs should be displayed in the action bar.
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // tab listener that is called when the user changes tabs.
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {
            public void onTabSelected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            public void onTabUnselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
                // ignore
            }

            public void onTabReselected(ActionBar.Tab tab, android.app.FragmentTransaction ft) {
                // ignore
            }

        };

        // Add 2 tabs, specifying the tab's text and TabListener
        for (String tab : tabs) {
            actionBar.addTab(
                    actionBar.newTab()
                            .setText(tab)
                            .setTabListener(tabListener)
            );
        }

    }
    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (mMap == null) {
            mMap = ((MapFragment) getFragmentManager().findFragmentById(R.id.map))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (mMap != null) {
                // The Map is verified. It is now safe to manipulate the map.

            }
        }
    }

}
