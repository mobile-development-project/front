package be.svv.repository;

import be.svv.model.Assignment;
import be.svv.model.Category;
import be.svv.model.Course;
import be.svv.model.Model;

public class RepositoryFactory
{

    /**
     * @param entity
     * @return
     */
    public static <T extends Model> RepositoryInterface getRepository (Class<T> entity)
    {
        if (entity == null)
            return null;
        if (entity.equals(Assignment.class))
            return new AssignmentRepository();
        if (entity.equals(Category.class))
            return new CategoryRepository();
        if (entity.equals(Course.class))
            return new CourseRepository();

        return null;
    }

}
