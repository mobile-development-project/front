package be.svv.viewmodel;

import be.svv.model.Course;
import be.svv.repository.RepositoryFactory;

public class CourseViewModel extends EntityViewModel<Course>
{

    public CourseViewModel ()
    {
        super(RepositoryFactory.getInstance().getRepository(Course.class));
    }

}
