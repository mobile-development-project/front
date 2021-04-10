package be.svv.view.fragment.category;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import be.svv.mobileapplication.R;
import be.svv.view.fragment.course.AddCourseActivity;
import be.svv.viewmodel.CategoryViewModel;

public class CategoryFragment extends Fragment
{
    private CategoryViewModel categoryViewModel;

    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_category, container, false);
        return root;
    }
}