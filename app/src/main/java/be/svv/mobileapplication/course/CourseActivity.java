package be.svv.mobileapplication.course;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import be.svv.adapter.CourseAdapter;
import be.svv.entity.Course;
import be.svv.mobileapplication.R;
import be.svv.repository.CourseRepository;

public class CourseActivity extends AppCompatActivity
{

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        RecyclerView recycler_view = findViewById(R.id.recycler_view);

        (new CourseRepository(this)).findAll(result ->
        {
            GsonBuilder gsonBuilder = new GsonBuilder();
            Gson gson = gsonBuilder.create();
            ArrayList<Course> courses = gson.fromJson(result, new TypeToken<List<Course>>(){}.getType());
            CourseAdapter adapter = new CourseAdapter(CourseActivity.this, courses);
            recycler_view.setAdapter(adapter);
            recycler_view.setLayoutManager(new LinearLayoutManager(CourseActivity.this));
        });
    }
}