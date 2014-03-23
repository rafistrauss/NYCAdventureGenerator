package com.djr.adventure;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabManager extends FragmentPagerAdapter {

	public TabManager(FragmentManager fm) {
		super(fm);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		switch (arg0) {
		case 0:
			return new MapFragment();
		
		case 1:
			return new DirectionsFragment();
		}
			
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 2;
	}

}
