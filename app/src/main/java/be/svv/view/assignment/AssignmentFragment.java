package be.svv.view.assignment;

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

import be.svv.mobileapplication.R;
import be.svv.model.Assignment;
import be.svv.model.Course;
import be.svv.model.request.AssignmentRequest;
import be.svv.view.adapter.AssignmentAdapter;
import be.svv.view.assignment.AddAssignmentActivity;
import be.svv.viewmodel.AssignmentViewModel;
import be.svv.viewmodel.CourseViewModel;

public class AssignmentFragment extends Fragment
{

    public static final int ADD_ASSIGNMENT_REQUEST = 1;
    private AssignmentViewModel assignmentViewModel;
    private CourseViewModel courseViewModel;
    private RecyclerView recyclerView;
    private AssignmentAdapter adapter;
    private FloatingActionButton btnPlus;
    private ProgressBar progressBar;

    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        assignmentViewModel = new ViewModelProvider(this).get(AssignmentViewModel.class);
        courseViewModel = new ViewModelProvider(this).get(CourseViewModel.class);
        View root = inflater.inflate(R.layout.fragment_assignment, container, false);

        progressBar = root.findViewById(R.id.progress_bar_assignment);
        btnPlus = root.findViewById(R.id.btn_add_assignment);
        courseViewModel.getAll().observe(getViewLifecycleOwner(), new Observer<List<Course>>()
        {
            @Override
            public void onChanged (List<Course> courses)
            {
                if (courses.size() > 0)
                {
                    btnPlus.setOnClickListener(new View.OnClickListener()
                    {
                        @Override
                        public void onClick (View v)
                        {
                            startActivityForResult(new Intent(getContext(), AddAssignmentActivity.class), ADD_ASSIGNMENT_REQUEST);
                            //getActivity().overridePendingTransition(R.anim.slide_in_up, R.anim.slide_out_up);
                        }
                    });
                }
                else
                {
                    btnPlus.setEnabled(false);
                }
            }
        });


        return root;
    }

    @Override
    public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        recyclerView = view.findViewById(R.id.recycler_view_assignment);
        adapter = new AssignmentAdapter();
        assignmentViewModel = new ViewModelProvider(this).get(AssignmentViewModel.class);
        assignmentViewModel.getAll().observe(getViewLifecycleOwner(), new Observer<List<Assignment>>()
        {
            @Override
            public void onChanged (List<Assignment> assignments)
            {
                adapter.setAssignments(assignments);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(adapter);
                progressBar.setVisibility(ProgressBar.INVISIBLE);
            }
        });
    }

    @Override
    public void onActivityResult (int requestCode, int resultCode, @Nullable Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_ASSIGNMENT_REQUEST && resultCode == Activity.RESULT_OK)
        {
            String name = data.getStringExtra("NAME");
            String course = data.getStringExtra("COURSE");
            assignmentViewModel.add(new AssignmentRequest(name, course, false));
            Toast.makeText(getContext(), "Devoir crée", Toast.LENGTH_SHORT).show();
        }
    }

}