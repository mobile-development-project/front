package be.svv.view.course;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.gson.Gson;

import be.svv.view.adapter.AssignmentAdapter;
import be.svv.model.Course;
import be.svv.mobileapplication.R;

public class ShowCourseActivity extends AppCompatActivity
{

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_show);

        TextView id = findViewById(R.id.course_show_id);
        TextView name = findViewById(R.id.course_show_name);
        TextView assignments = findViewById(R.id.course_show_assignments);

        Course course = (new Gson()).fromJson(getIntent().getStringExtra("COURSE"), Course.class);
        setTitle(course.getName());
        id.setText(course.getId() + "");
        name.setText(course.getName());
        assignments.setText(course.getAssignments().size() + " devoir(s)");

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_baseline_close_24);
        setTitle(course.getName());

        RecyclerView recyclerView = findViewById(R.id.recycler_view_list_assignments);

        AssignmentAdapter adapter = new AssignmentAdapter();
        adapter.setAssignments(course.getAssignments());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onOptionsItemSelected (@NonNull MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}