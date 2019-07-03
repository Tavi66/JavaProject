package com.example.halaljava;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabPagerAdapter extends FragmentPagerAdapter {
    int tabCount;
    public TabPagerAdapter(FragmentManager fm, int numberOfTabs) {
        super(fm);
        this.tabCount = numberOfTabs;
    }

    @Override
    public Fragment getItem(int i) {
        Fragment frag = null;
        switch (i)
        {
            case 0:
                frag = new newItemFrag();
                break;
            case 1:
                frag = new editItemFrag();
                break;
            case 2:
                frag = new deleteItemFrag();
                break;
            default:
                break;
        }
        return frag;
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
