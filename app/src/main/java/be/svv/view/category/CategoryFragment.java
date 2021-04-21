package be.svv.view.category;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import be.svv.mobileapplication.R;
import be.svv.model.Category;
import be.svv.model.request.CategoryRequest;
import be.svv.view.adapter.CategoryAdapter;
import be.svv.viewmodel.CategoryViewModel;

public class CategoryFragment extends Fragment
{

    public static final int ADD_CATEGORY_REQUEST = 1;
    private RecyclerView recyclerView;
    private CategoryAdapter adapter;
    private CategoryViewModel categoryViewModel;
    private FloatingActionButton btnPlus;
    private ProgressBar progressBar;

    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_category, container, false);
        progressBar = root.findViewById(R.id.progress_bar_category);
        btnPlus = root.findViewById(R.id.btn_add_category);
        btnPlus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                startActivityForResult(new Intent(getContext(), AddCategoryActivity.class), ADD_CATEGORY_REQUEST);
                //getActivity().overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
            }
        });
        return root;
    }

    @Override
    public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view_category);
        adapter = new CategoryAdapter();
        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        categoryViewModel.getAll().observe(getViewLifecycleOwner(), new Observer<List<Category>>()
        {
            @Override
            public void onChanged (List<Category> categories)
            {
                adapter.setCategories(categories);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(ProgressBar.INVISIBLE);
            }
        });
    }

    @Override
    public void onActivityResult (int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_CATEGORY_REQUEST && resultCode == Activity.RESULT_OK)
        {
            String name = data.getStringExtra("NAME");
            categoryViewModel.add(new CategoryRequest(name));
            Toast.makeText(getContext(), "Catégorie créee", Toast.LENGTH_SHORT).show();
        }
    }
}