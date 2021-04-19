package be.svv.view.fragment.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.MutableLiveData;
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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.List;

import be.svv.globals.Url;
import be.svv.mobileapplication.R;
import be.svv.model.Course;
import be.svv.viewmodel.CourseViewModel;

public class AddAssignmentActivity extends AppCompatActivity
{

    private ArrayAdapter<Course> adapter;
    private Course selectedCourse;
    private CourseViewModel courseViewModel;

    private EditText editTextName;
    private Spinner spinner;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);

        editTextName = findViewById(R.id.edit_text_assignment_name);

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);

        spinner = findViewById(R.id.spinner_assignemt_course);

        courseViewModel.getAll().observe(this, new Observer<List<Course>>()
        {
            @Override
            public void onChanged (List<Course> courses)
            {
                adapter = new ArrayAdapter(AddAssignmentActivity.this, android.R.layout.simple_spinner_dropdown_item, courses);
                adapter.notifyDataSetChanged();
                spinner.setAdapter(adapter);
            }
        });
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected (AdapterView<?> parent, View view, int position, long id)
            {
                selectedCourse = (Course) parent.getSelectedItem();
            }

            @Override
            public void onNothingSelected (AdapterView<?> parent)
            {

            }
        });

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);
        setTitle("Ajouter un devoir");
    }

    private void saveAssignment ()
    {
        String name = editTextName.getText().toString();

        if (name.trim().isEmpty())
        {
            Toast.makeText(this, "L'intitulé ne peut pas être vide!", Toast.LENGTH_SHORT).show();
            return;
        }
        Intent data = new Intent();
        data.putExtra("NAME", name);
        data.putExtra("COURSE", Url.COURSES + selectedCourse.getId());
        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onCreateOptionsMenu (Menu menu)
    {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.save:
                saveAssignment();
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}