package be.svv.viewmodel;


import androidx.lifecycle.MutableLiveData;

import java.util.List;

import be.svv.model.Assignment;
import be.svv.repository.AssignmentRepository;
import be.svv.repository.RepositoryFactory;
import retrofit2.Response;

public class AssignmentViewModel extends EntityViewModel<Assignment>
{

    private AssignmentRepository api = (AssignmentRepository) RepositoryFactory.getInstance().getRepository(Assignment.class);
    private MutableLiveData<List<Assignment>> nearestAssignments;

    public AssignmentViewModel ()
    {
        super(RepositoryFactory.getInstance().getRepository(Assignment.class));
        nearestAssignments = new MutableLiveData<>();
        initNearestAssignments();
    }

    public void initNearestAssignments ()
    {
        api.getNearAssignments(new ViewModelCallback<Assignment>()
        {
            @Override
            public void onResponse (Response<List<Assignment>> response)
            {
                nearestAssignments.setValue(response.body());
            }
        });
    }

    public MutableLiveData<List<Assignment>> getNearestAssignments ()
    {
        initNearestAssignments();
        return nearestAssignments;
    }

}
