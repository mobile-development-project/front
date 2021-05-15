package be.svv.view.assignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import be.svv.globals.Url;
import be.svv.mobileapplication.R;
import be.svv.model.Assignment;
import be.svv.model.Course;
import be.svv.model.request.AssignmentRequest;
import be.svv.service.gson.GsonSingleton;
import be.svv.viewmodel.CourseViewModel;

public class AddEditAssignmentActivity extends AppCompatActivity
{

    public static final String EXTRA_ASSIGNMENT = "ASSIGNMENT";
    public static final String EXTRA_ID = "ID";

    private ArrayAdapter<Course> adapter;
    private Course selectedCourse;
    private CourseViewModel courseViewModel;
    private Assignment assignment;

    private EditText editTextName;
    private Spinner spinner;
    private EditText dateTime;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_assignment);

        editTextName = findViewById(R.id.edit_text_assignment_name);
        spinner = findViewById(R.id.spinner_categories_agenda);
        dateTime = findViewById(R.id.edit_text_date_time);

        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.getAll().observe(this, new Observer<List<Course>>()
        {
            @Override
            public void onChanged (List<Course> courses)
            {
                adapter = new ArrayAdapter(AddEditAssignmentActivity.this, android.R.layout.simple_spinner_dropdown_item, courses);
                adapter.notifyDataSetChanged();
                spinner.setAdapter(adapter);
                if (getIntent().hasExtra(EXTRA_ASSIGNMENT))
                {
                    init();
                }
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
                selectedCourse = null;
            }
        });

        dateTime.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                showDateTimeDialog(dateTime);
            }
        });

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ASSIGNMENT))
        {
            setTitle("Modifier un devoir");
            assignment = GsonSingleton.getInstance().fromJson(getIntent().getStringExtra(EXTRA_ASSIGNMENT), Assignment.class);
            editTextName.setText(assignment.getName());
            dateTime.setText(assignment.getFinishAt());
        }
        else
        {
            setTitle("Ajouter un devoir");
        }

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);

    }

    private void showDateTimeDialog (EditText dateTime)
    {
        final Calendar calendar = Calendar.getInstance();
        Locale.setDefault(Locale.FRANCE);
        DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet (DatePicker view, int year, int month, int dayOfMonth)
            {
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.YEAR, year);

                TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener()
                {
                    @Override
                    public void onTimeSet (TimePicker view, int hourOfDay, int minute)
                    {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);

                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                        dateTime.setText(simpleDateFormat.format(calendar.getTime()));
                    }
                };
                new TimePickerDialog(AddEditAssignmentActivity.this, timeSetListener, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true)
                        .show();
            }
        };

        new DatePickerDialog(AddEditAssignmentActivity.this, dateSetListener, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar
                .get(Calendar.DAY_OF_MONTH)).show();
    }

    private void init ()
    {
        int index = 0;
        for (int i = 0; i < adapter.getCount(); i++)
        {
            if (adapter.getItem(i).getId() == assignment.getCourse().getId())
            {
                spinner.setSelection(index);
            }
            index++;
        }
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
        data.putExtra(EXTRA_ASSIGNMENT, GsonSingleton.getInstance()
                .toJson(new AssignmentRequest(name, Url.COURSES + selectedCourse.getId(), true, dateTime.getText().toString())));

        int id = getIntent().getIntExtra(EXTRA_ID, -1);

        if (id != -1)
        {
            data.putExtra(EXTRA_ID, id);
        }

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