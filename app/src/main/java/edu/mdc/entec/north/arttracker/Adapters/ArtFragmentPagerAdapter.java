package edu.mdc.entec.north.arttracker.Adapters;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import edu.mdc.entec.north.arttracker.Views.GalleryFragment;
import edu.mdc.entec.north.arttracker.Views.MapFragment;
import edu.mdc.entec.north.arttracker.Views.QuizFragment;

public class ArtFragmentPagerAdapter extends FragmentPagerAdapter {
    public ArtFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                Fragment tab1 = new GalleryFragment();
                return tab1;
            case 1:
                Fragment tab2 = new QuizFragment();
                return tab2;
            case 2:
                Fragment tab3 = new MapFragment();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}