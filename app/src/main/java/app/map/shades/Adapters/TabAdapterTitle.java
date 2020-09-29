package app.map.shades.Adapters;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class TabAdapterTitle extends FragmentStatePagerAdapter {
    private List<Fragment> FragmentList = new ArrayList<>();
    private List<String> FragmentTitleList = new ArrayList<>();

    public TabAdapterTitle(FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentList.get(position);
    }

    public void addFragment(Fragment fragment, String title) {
        FragmentList.add(fragment);
        FragmentTitleList.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return FragmentTitleList.get(position);
    }


    @Override
    public int getCount() {
        return FragmentList.size();
    }

}
