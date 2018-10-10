package com.example.externkoutsodimakis.darksky.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.externkoutsodimakis.darksky.View.CurrentFragment;
import com.example.externkoutsodimakis.darksky.View.DailyFragment;
import com.example.externkoutsodimakis.darksky.View.HourlyFragment;
import com.example.externkoutsodimakis.darksky.View.MapFragment;

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;

    public PagerAdapter(FragmentManager fm, int numOfTabs) {
        super(fm);
        this.mNumOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                CurrentFragment currentFragment = new CurrentFragment();
                return currentFragment;
            case 1:
                HourlyFragment hourlyFragment = new HourlyFragment();
                return hourlyFragment;
            case 2:
                DailyFragment dailyFragment = new DailyFragment();
                return dailyFragment;
            case 3:
                MapFragment mapFragment = new MapFragment();
                return mapFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}
