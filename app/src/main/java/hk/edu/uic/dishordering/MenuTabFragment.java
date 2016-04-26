package hk.edu.uic.dishordering;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

public class MenuTabFragment extends Fragment {

    private ViewPager mViewPager;
    private TabLayout mMenuTabLayout;

    public MenuTabFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_tab_menu, container, false);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        MenuFragmentPagerAdapter adapter = new MenuFragmentPagerAdapter(getChildFragmentManager());
        mViewPager = (ViewPager) getActivity().findViewById(R.id.view_pager);
        mViewPager.setAdapter(adapter);

        mMenuTabLayout = (TabLayout) getActivity().findViewById(R.id.menu_tab_layout);
        mMenuTabLayout.setVisibility(View.VISIBLE);
        mMenuTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onStart() {
        super.onStart();
        mMenuTabLayout.setVisibility(View.VISIBLE);
    }

    @Override
    public void onStop() {
        super.onStop();
        mMenuTabLayout.setVisibility(View.GONE);
    }
}
