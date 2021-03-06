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
import be.svv.service.gson.GsonSingleton;
import be.svv.view.adapter.CategoryAdapter;
import be.svv.viewmodel.CategoryViewModel;

public class CategoryFragment extends Fragment
{

    public static final int ADD_CATEGORY_REQUEST = 1;
    public static final int EDIT_CATEGORY_REQUEST = 2;

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
                startActivityForResult(new Intent(getContext(), AddEditCategoryActivity.class), ADD_CATEGORY_REQUEST);
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

        adapter.setOnItemClickListener(new CategoryAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick (Category category)
            {
                Intent intent = new Intent(getContext(), AddEditCategoryActivity.class);
                intent.putExtra(AddEditCategoryActivity.EXTRA_CATEGORY, GsonSingleton.getInstance().toJson(category));
                intent.putExtra(AddEditCategoryActivity.EXTRA_ID, category.getId());
                startActivityForResult(intent, EDIT_CATEGORY_REQUEST);
            }
        });
    }

    @Override
    public void onActivityResult (int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_CATEGORY_REQUEST && resultCode == Activity.RESULT_OK)
        {
            CategoryRequest categoryRequest = GsonSingleton.getInstance()
                    .fromJson(data.getStringExtra(AddEditCategoryActivity.EXTRA_CATEGORY), CategoryRequest.class);
            categoryViewModel.add(categoryRequest);
            Toast.makeText(getContext(), "Cat??gorie cr????e", Toast.LENGTH_SHORT).show();
        }
        else if (requestCode == EDIT_CATEGORY_REQUEST && resultCode == Activity.RESULT_OK)
        {
            CategoryRequest courseRequest = GsonSingleton.getInstance()
                    .fromJson(data.getStringExtra(AddEditCategoryActivity.EXTRA_CATEGORY), CategoryRequest.class);
            int id = data.getIntExtra(AddEditCategoryActivity.EXTRA_ID, -1);
            if (id == -1)
            {
                Toast.makeText(getContext(), "La cat??gorie ne peut pas ??tre modifi??e!", Toast.LENGTH_SHORT).show();
                return;
            }
            categoryViewModel.update(courseRequest, id);
            Toast.makeText(getContext(), "Cat??gorie modifi??e!", Toast.LENGTH_SHORT).show();
        }
    }
}