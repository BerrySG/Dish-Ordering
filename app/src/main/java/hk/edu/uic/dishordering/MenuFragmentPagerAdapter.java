package hk.edu.uic.dishordering;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class MenuFragmentPagerAdapter extends FragmentPagerAdapter {

    final int PAGE_COUNT = 3;

    private String tabTitles[] = new String[] { "Tab1", "Tab2", "Tab3"};
    private Context mContext;

    public MenuFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return DishFragment.newInstance(position + 1);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tabTitles[position];
    }
}
