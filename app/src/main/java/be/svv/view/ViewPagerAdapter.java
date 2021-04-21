package be.svv.view;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter
{

    private List<Fragment> fragments = new ArrayList<>();
    private List<String> fragmentsTitle = new ArrayList<>();

    public ViewPagerAdapter (@NonNull FragmentManager fm, int behavior)
    {
        super(fm, behavior);
    }

    public void addFragment (Fragment fragment, String title)
    {
        fragments.add(fragment);
        fragmentsTitle.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle (int position)
    {
        return fragmentsTitle.get(position);
    }

    @NonNull
    @Override
    public Fragment getItem (int position)
    {
        return fragments.get(position);
    }

    @Override
    public int getCount ()
    {
        return fragments.size();
    }
}
