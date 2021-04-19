package be.svv.viewmodel;

import be.svv.model.Assignment;
import be.svv.repository.RepositoryFactory;

public class AssignmentViewModel extends EntityViewModel<Assignment>
{

    public AssignmentViewModel ()
    {
        super(RepositoryFactory.getRepository(Assignment.class));
    }
}
