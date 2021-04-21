package be.svv.view.note;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import be.svv.mobileapplication.R;
import be.svv.viewmodel.AssignmentViewModel;

public class NoteFragment extends Fragment
{
    private AssignmentViewModel assignmentViewModel;

    public View onCreateView (@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        assignmentViewModel = new ViewModelProvider(this).get(AssignmentViewModel.class);
        View root = inflater.inflate(R.layout.fragment_assignment, container, false);
        return root;
    }
}