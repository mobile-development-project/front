package be.svv.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;

import be.svv.mobileapplication.R;
import be.svv.view.fragment.assignment.AssignmentFragment;
import be.svv.view.fragment.category.CategoryFragment;
import be.svv.view.fragment.course.CourseFragment;
import be.svv.view.fragment.note.NoteFragment;

public class DataFragment extends Fragment
{

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_data, container, false);
        return view;
    }

    @Override
    public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        viewPager = view.findViewById(R.id.view_pager);
        tabLayout = view.findViewById(R.id.tab_layout);

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), 0);
        viewPagerAdapter.addFragment(new CourseFragment(), "Cours");
        viewPagerAdapter.addFragment(new AssignmentFragment(), "Devoirs");
        viewPagerAdapter.addFragment(new CategoryFragment(), "Catégories");
        viewPagerAdapter.addFragment(new NoteFragment(), "Notes");

        viewPager.setAdapter(viewPagerAdapter);
        viewPager.setSaveEnabled(false);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_class_24);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_home_work_24);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_baseline_category_24);
        tabLayout.getTabAt(3).setIcon(R.drawable.ic_baseline_sticky_note_2_24);
    }

}