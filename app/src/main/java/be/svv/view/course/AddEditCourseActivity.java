package be.svv.view.course;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import be.svv.mobileapplication.R;
import be.svv.model.Course;
import be.svv.model.request.CourseRequest;
import be.svv.service.gson.GsonSingleton;

public class AddEditCourseActivity extends AppCompatActivity
{

    public static final String EXTRA_COURSE = "COURSE";
    public static final String EXTRA_ID = "ID";
    
    private EditText editTextName;

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_course);

        editTextName = findViewById(R.id.edit_text_course_name);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);

        Intent intent = getIntent();

        // If a course is parsed in the intent, set the value of each fields
        if (intent.hasExtra(EXTRA_COURSE))
        {
            setTitle("Modifier un cours");
            Course course = GsonSingleton.getInstance().fromJson(intent.getStringExtra(EXTRA_COURSE), Course.class);
            editTextName.setText(course.getName());
        }
        else
        {
            setTitle("Ajouter un cours");
        }
    }

    private void saveCourse ()
    {
        String name = editTextName.getText().toString();

        if (name.trim().isEmpty())
        {
            Toast.makeText(this, "Le nom ne peut pas être vide!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_COURSE, GsonSingleton.getInstance().toJson(new CourseRequest(name)));

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
                saveCourse();
                return true;
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


}