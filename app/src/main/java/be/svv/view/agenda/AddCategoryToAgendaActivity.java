package be.svv.view.agenda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import be.svv.mobileapplication.R;
import be.svv.model.Category;
import be.svv.model.request.AgendaRequest;
import be.svv.service.gson.GsonSingleton;
import be.svv.viewmodel.CategoryViewModel;

public class AddCategoryToAgendaActivity extends AppCompatActivity
{

    public static final String EXTRA_DATE = "DATE";
    public static final String EXTRA_CATEGORY = "CATEGORY";

    private TextView txtTitle;
    private Spinner categoriesSpinner;
    private CategoryViewModel categoryViewModel;
    private ArrayAdapter<Category> adapter;
    private Category selectedCategory;
    private String selectedDate;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category_to_agenda);
        setTitle("Ajouter une catégorie");

        txtTitle = findViewById(R.id.txt_title);
        categoriesSpinner = findViewById(R.id.spinner_categories_agenda);
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_DATE))
        {
            selectedDate = intent.getStringExtra(EXTRA_DATE);
            txtTitle.setText("Ajouter une catégorie pour le " + selectedDate);
        }

        categoryViewModel = new ViewModelProvider(this).get(CategoryViewModel.class);
        categoryViewModel.getAll().observe(this, new Observer<List<Category>>()
        {
            @Override
            public void onChanged (List<Category> categories)
            {
                adapter = new ArrayAdapter(AddCategoryToAgendaActivity.this, android.R.layout.simple_spinner_dropdown_item, categories);
                adapter.notifyDataSetChanged();
                categoriesSpinner.setAdapter(adapter);
            }
        });

        categoriesSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected (AdapterView<?> parent, View view, int position, long id)
            {
                selectedCategory = (Category) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected (AdapterView<?> parent)
            {
                selectedCategory = null;
            }
        });

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_menu, menu);
        return true;
    }

    private void saveCategory ()
    {
        Intent data = new Intent();
        ArrayList<String> categories = new ArrayList<>();
        categories.add("/api/categories/" + selectedCategory.getId());
        data.putExtra(EXTRA_CATEGORY, GsonSingleton.getInstance().toJson(new AgendaRequest(selectedDate, categories)));
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.save:
                saveCategory();
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}