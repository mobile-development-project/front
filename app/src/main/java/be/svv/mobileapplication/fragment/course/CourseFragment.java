package be.svv.mobileapplication.fragment.course;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import be.svv.adapter.CourseAdapter;
import be.svv.entity.Course;
import be.svv.mobileapplication.R;
import be.svv.repository.Repository;
import be.svv.repository.RepositoryFactory;
import be.svv.service.Gson.GsonSingleton;

public class CourseFragment extends Fragment
{
    private RecyclerView recyclerView;
    private ArrayList<Course> courses;

    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_course, container, false);
        recyclerView = root.findViewById(R.id.recycler_view);
        return root;
    }

    @Override
    public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated (@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);
        RepositoryFactory repositoryFactory = new RepositoryFactory(getContext());
        Repository courseRepository = repositoryFactory.getRepository(Course.class);

        courseRepository.findAll(result ->
        {
            courses = GsonSingleton.getInstance().fromJson(result, new TypeToken<List<Course>>()
            {
            }.getType());
            CourseAdapter adapter = new CourseAdapter(getContext(), courses);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            recyclerView.setAdapter(adapter);
        });
    }
}