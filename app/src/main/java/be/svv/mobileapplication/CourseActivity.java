package be.svv.mobileapplication;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import be.svv.adapter.CourseAdapter;
import be.svv.entity.Course;
import be.svv.entity.Entity;
import be.svv.globals.ApiUrl;
import be.svv.repository.CourseRepository;
import be.svv.service.Volley.VolleyCallback;

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
            Course[] courses = gson.fromJson(result, Course[].class);
            CourseAdapter adapter = new CourseAdapter(CourseActivity.this, courses);
            recycler_view.setAdapter(adapter);
            recycler_view.setLayoutManager(new LinearLayoutManager(CourseActivity.this));
        });
    }
}