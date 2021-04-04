package be.svv.mobileapplication.course;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;

import be.svv.adapter.AssignmentAdapter;
import be.svv.entity.Course;
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

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        AssignmentAdapter adapter = new AssignmentAdapter(this, course.getAssignments());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}