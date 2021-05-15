package be.svv.view.agenda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import be.svv.mobileapplication.R;
import be.svv.model.Assignment;
import be.svv.model.Category;
import be.svv.model.request.DeleteCategoryRequest;
import be.svv.service.gson.GsonSingleton;
import be.svv.view.adapter.AssignmentAdapter;
import be.svv.view.adapter.CategoryAdapter;
import be.svv.viewmodel.AgendaViewModel;
import be.svv.viewmodel.AssignmentViewModel;

public class ShowDayEventsActivity extends AppCompatActivity
{

    private TextView txtAssignment, txtCategory;

    private ArrayList<Assignment> assignments;
    private ArrayList<Category> categories;

    private AgendaViewModel agendaViewModel;

    private RecyclerView assignmentRecyclerView;
    private AssignmentAdapter assignmentAdapter;
    private RecyclerView categoriesRecyclerView;
    private CategoryAdapter categoryAdapter;

    private int agendaId;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_day_events);
        txtAssignment = findViewById(R.id.txt_events_assignment_title);
        txtCategory = findViewById(R.id.txt_events_category_title);
        assignmentRecyclerView = findViewById(R.id.recycler_view_events_assignments);
        assignmentAdapter = new AssignmentAdapter();
        agendaViewModel = new ViewModelProvider(this).get(AgendaViewModel.class);
        categoriesRecyclerView = findViewById(R.id.recycler_view_events_categories);
        categoryAdapter = new CategoryAdapter();

        if (getIntent().hasExtra(AgendaActivity.EXTRA_ASSIGNMENT))
        {
            assignments = GsonSingleton.getInstance()
                    .fromJson(getIntent().getStringExtra(AgendaActivity.EXTRA_ASSIGNMENT), new TypeToken<ArrayList<Assignment>>() {}.getType());
            assignmentAdapter.setAssignments(assignments);
            assignmentAdapter.enableSettings(false);
            assignmentAdapter.enableNotifications(false);
            assignmentRecyclerView.setAdapter(assignmentAdapter);
            assignmentRecyclerView.setHasFixedSize(true);
            assignmentRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        }
        else
        {
            txtAssignment.setText("Aucun devoir pour ce jour!");
        }
        if (getIntent().hasExtra(AgendaActivity.EXTRA_CATEGORY))
        {
            categories = GsonSingleton.getInstance()
                    .fromJson(getIntent().getStringExtra(AgendaActivity.EXTRA_CATEGORY), new TypeToken<ArrayList<Category>>() {}.getType());
            categoryAdapter.setCategories(categories);
            categoryAdapter.enableSettings(false);
            categoriesRecyclerView.setAdapter(categoryAdapter);
            categoriesRecyclerView.setHasFixedSize(true);
            categoriesRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            new ItemTouchHelper(itemTouchHelperCallback).attachToRecyclerView(categoriesRecyclerView);
        }
        else
        {
            txtCategory.setText("Aucune catégorie pour ce jour!");
        }

        agendaId = getIntent().getIntExtra(AgendaActivity.EXTRA_AGENDA_ID, 0);
    }

    ItemTouchHelper.SimpleCallback itemTouchHelperCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT)
    {
        @Override
        public boolean onMove (@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target)
        {
            return false;
        }

        @Override
        public void onSwiped (@NonNull RecyclerView.ViewHolder viewHolder, int direction)
        {
            Category currentCategory = categories.get(viewHolder.getAdapterPosition());
            categories.remove(viewHolder.getAdapterPosition());
            categoryAdapter.notifyDataSetChanged();
            agendaViewModel.deleteCategory(agendaId, new DeleteCategoryRequest(currentCategory.getId()));
        }
    };

}