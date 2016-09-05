package com.challenge.zap.zappropertieslist.propertyDetail;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by eliete on 9/5/16.
 */
public class PhotoAdapter extends FragmentPagerAdapter {

    private List<String> photoList;

    public PhotoAdapter(FragmentManager fm, List<String> photoList) {
        super(fm);
        this.photoList = photoList;
    }

    @Override
    public Fragment getItem(int position) {
        return PhotoFragment.newInstance(photoList.get(position));
    }

    @Override
    public int getCount() {
        return photoList.size();
    }

}
