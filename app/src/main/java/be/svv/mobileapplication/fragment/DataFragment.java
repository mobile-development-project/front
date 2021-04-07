package be.svv.mobileapplication.fragment;

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
import be.svv.mobileapplication.fragment.assignment.AssignmentFragment;
import be.svv.mobileapplication.fragment.category.CategoryFragment;
import be.svv.mobileapplication.fragment.course.CourseFragment;

public class DataFragment extends Fragment
{

    private CourseFragment courseFragment;
    private AssignmentFragment assignmentFragment;
    private CategoryFragment categoryFragment;

    private ViewPager viewPager;
    private TabLayout tabLayout;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_data, container, false);
    }

    @Override
    public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        viewPager = view.findViewById(R.id.view_pager);
        tabLayout = view.findViewById(R.id.tab_layout);
        courseFragment = new CourseFragment();
        assignmentFragment = new AssignmentFragment();
        categoryFragment = new CategoryFragment();

        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getChildFragmentManager(), 0);
        viewPagerAdapter.addFragment(courseFragment, "Cours");
        viewPagerAdapter.addFragment(assignmentFragment, "Devoirs");
        viewPagerAdapter.addFragment(categoryFragment, "Catégories");

        viewPager.setAdapter(viewPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        
        tabLayout.getTabAt(0).setIcon(R.drawable.ic_baseline_class_24);
        tabLayout.getTabAt(1).setIcon(R.drawable.ic_baseline_home_work_24);
        tabLayout.getTabAt(2).setIcon(R.drawable.ic_baseline_category_24);
    }
}