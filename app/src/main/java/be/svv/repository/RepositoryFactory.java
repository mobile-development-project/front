package be.svv.repository;

import android.content.Context;

import be.svv.entity.Assignment;
import be.svv.entity.Category;
import be.svv.entity.Course;
import be.svv.entity.Entity;

public class RepositoryFactory
{

    private Context context;

    public RepositoryFactory (Context context)
    {
        this.context = context;
    }

    /**
     * @param entity
     * @return
     */
    public <T extends Entity> Repository getRepository (Class<T> entity)
    {
        if (entity == null)
            return null;
        if (entity.equals(Assignment.class))
            return new AssignmentRepository(context);
        if (entity.equals(Category.class))
            return new CategoryRepository(context);
        if (entity.equals(Course.class))
            return new CourseRepository(context);

        return null;
    }

}
