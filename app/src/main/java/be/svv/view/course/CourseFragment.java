package be.svv.view.course;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import be.svv.model.request.CourseRequest;
import be.svv.service.gson.GsonSingleton;
import be.svv.view.adapter.CourseAdapter;
import be.svv.model.Course;
import be.svv.mobileapplication.R;
import be.svv.viewmodel.CourseViewModel;

public class CourseFragment extends Fragment
{

    public static final int ADD_COURSE_REQUEST = 1;
    public static final int EDIT_COURSE_REQUEST = 2;

    private RecyclerView recyclerView;
    private CourseAdapter adapter;
    private CourseViewModel courseViewModel;
    private FloatingActionButton btnPlus;
    private ProgressBar progressBar;

    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_course, container, false);
        progressBar = root.findViewById(R.id.progress_bar_course);
        btnPlus = root.findViewById(R.id.btn_add_course);
        btnPlus.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                startActivityForResult(new Intent(getContext(), AddEditCourseActivity.class), ADD_COURSE_REQUEST);
                //getActivity().overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
            }
        });

        return root;
    }

    @Override
    public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view_course);
        adapter = new CourseAdapter();
        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        courseViewModel.getAll().observe(getViewLifecycleOwner(), new Observer<List<Course>>()
        {
            @Override
            public void onChanged (List<Course> courses)
            {
                adapter.setCourses(courses);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(ProgressBar.INVISIBLE);
            }
        });
        adapter.setOnItemClickListener(new CourseAdapter.OnItemClickListener()
        {
            @Override
            public void onItemClick (Course course)
            {
                Intent intent = new Intent(getContext(), AddEditCourseActivity.class);
                intent.putExtra(AddEditCourseActivity.EXTRA_COURSE, GsonSingleton.getInstance().toJson(course));
                intent.putExtra(AddEditCourseActivity.EXTRA_ID, course.getId());
                startActivityForResult(intent, EDIT_COURSE_REQUEST);
            }
        });

    }

    @Override
    public void onActivityResult (int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_COURSE_REQUEST && resultCode == Activity.RESULT_OK)
        {
            CourseRequest courseRequest = GsonSingleton.getInstance()
                    .fromJson(data.getStringExtra(AddEditCourseActivity.EXTRA_COURSE), CourseRequest.class);
            courseViewModel.add(courseRequest);
            Toast.makeText(getContext(), "Cours crée", Toast.LENGTH_SHORT).show();
        }
        else if (requestCode == EDIT_COURSE_REQUEST && resultCode == Activity.RESULT_OK)
        {
            CourseRequest courseRequest = GsonSingleton.getInstance()
                    .fromJson(data.getStringExtra(AddEditCourseActivity.EXTRA_COURSE), CourseRequest.class);
            int id = data.getIntExtra(AddEditCourseActivity.EXTRA_ID, -1);
            if (id == -1)
            {
                Toast.makeText(getContext(), "Le cours ne peut pas être modifié!", Toast.LENGTH_SHORT).show();
                return;
            }
            courseViewModel.update(courseRequest, id);
            Toast.makeText(getContext(), "Cours modifié avec succès!", Toast.LENGTH_SHORT).show();
        }
    }

}