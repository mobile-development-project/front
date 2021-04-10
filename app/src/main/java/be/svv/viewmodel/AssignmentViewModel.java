package be.svv.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import be.svv.model.Assignment;
import be.svv.model.Course;
import be.svv.model.request.AssignmentRequest;
import be.svv.model.request.CourseRequest;
import be.svv.repository.AssignmentRepository;
import be.svv.repository.CourseRepository;

public class AssignmentViewModel extends ViewModel
{

    private AssignmentRepository repository;
    private MutableLiveData<List<Assignment>> assignments;

    public AssignmentViewModel ()
    {
        repository = new AssignmentRepository();
    }

    public MutableLiveData<List<Assignment>> getAllAssignments ()
    {
        if (assignments == null)
        {
            assignments = repository.getAll();
        }
        return assignments;
    }

    public void addAssignment (AssignmentRequest assignmentRequest)
    {
        repository.add(assignmentRequest);
    }
}
