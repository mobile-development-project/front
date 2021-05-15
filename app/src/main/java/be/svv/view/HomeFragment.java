package be.svv.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import be.svv.mobileapplication.R;
import be.svv.model.Assignment;
import be.svv.view.adapter.AssignmentAdapter;
import be.svv.view.agenda.AgendaActivity;
import be.svv.viewmodel.AssignmentViewModel;

public class HomeFragment extends Fragment
{

    private Button btnAgenda;
    private TextView txtNearHomeworks;
    private AssignmentViewModel assignmentViewModel;
    private RecyclerView recyclerView;
    private AssignmentAdapter adapter;

    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        getActivity().setTitle("Accueil");

        return root;
    }

    @Override
    public void onViewCreated (@NonNull View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        btnAgenda = view.findViewById(R.id.btn_agenda);
        txtNearHomeworks = view.findViewById(R.id.txt_near_homeworks);
        recyclerView = view.findViewById(R.id.recycler_view_nearest_assignments);
        adapter = new AssignmentAdapter();
        assignmentViewModel = new ViewModelProvider(this).get(AssignmentViewModel.class);
        assignmentViewModel.getNearestAssignments().observe(getViewLifecycleOwner(), new Observer<List<Assignment>>()
        {
            @Override
            public void onChanged (List<Assignment> assignments)
            {
                if (assignments.size() > 0)
                {
                    adapter.setAssignments(assignments);
                    adapter.enableSettings(false);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(adapter);
                }
                else
                {
                    recyclerView.setVisibility(View.GONE);
                    txtNearHomeworks.setText("Vous n'avez aucun devoir pour le moment !");
                }
            }
        });

        btnAgenda.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                startActivity(new Intent(getActivity(), AgendaActivity.class));
            }
        });

        //        FirebaseInstanceId.getInstance().getInstanceId().addOnSuccessListener(getActivity(), new OnSuccessListener<InstanceIdResult>()
        //        {
        //            @Override
        //            public void onSuccess (InstanceIdResult instanceIdResult)
        //            {
        //                String fcmToken = instanceIdResult.getToken();
        //                Log.d("TOKEN", fcmToken);
        //            }
        //        });
    }

}