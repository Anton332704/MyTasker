package iipo.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import iipo.fragments.FragmentTasks;
import iipo.fragments.NewTaskFragment;

/**
 * Created by user on 15.05.2016.
 */
public class TasksPagerAdapter extends FragmentStatePagerAdapter {

    private static int NUM_ITEMS = 2;
    public TasksPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: // Fragment # 0 - This will show FirstFragment
                return FragmentTasks.newInstance("Page # 1");
            case 1: // Fragment # 0 - This will show FirstFragment different title
                return NewTaskFragment.newInstance("Page # 2");
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return NUM_ITEMS;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return "PAGE " + position;
    }
}
