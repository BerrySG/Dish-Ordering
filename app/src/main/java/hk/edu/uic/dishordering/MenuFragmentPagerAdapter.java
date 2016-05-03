package hk.edu.uic.dishordering;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MenuFragmentPagerAdapter extends FragmentPagerAdapter {

    private String tabTitles[] = new String[]{"FEATURED", "FOOD", "DRINK", "DESSERTS"};

    public MenuFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return MenuFragment.newInstance("featured");
            case 1:
                return MenuFragment.newInstance("food");
            case 2:
                return MenuFragment.newInstance("drink");
            case 3:
                return MenuFragment.newInstance("desserts");
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabTitles.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
