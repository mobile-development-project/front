package be.svv.mobileapplication.course;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import be.svv.adapter.CourseAdapter;
import be.svv.entity.Course;
import be.svv.mobileapplication.R;
import be.svv.repository.CourseRepository;
import be.svv.service.Gson.GsonSingleton;
import be.svv.service.Volley.VolleyCallback;

public class CourseActivity extends AppCompatActivity
{

    @Override
    protected void onCreate (Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course);

        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        (new CourseRepository(this)).findAll(new VolleyCallback()
        {
            @Override
            public void onSuccess (String result)
            {
                ArrayList<Course> courses = GsonSingleton.getInstance().fromJson(result, new TypeToken<List<Course>>()
                {
                }.getType());
                CourseAdapter adapter = new CourseAdapter(CourseActivity.this, courses);
                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(CourseActivity.this));
            }
        });
    }
}