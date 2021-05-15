package be.svv.repository;

import be.svv.model.Assignment;
import be.svv.model.Agenda;
import be.svv.model.Category;
import be.svv.model.Course;
import be.svv.model.Model;

public class RepositoryFactory
{

    private static RepositoryFactory instance = null;

    public static synchronized RepositoryFactory getInstance ()
    {
        if (RepositoryFactory.instance == null)
        {
            RepositoryFactory.instance = new RepositoryFactory();
        }

        return RepositoryFactory.instance;
    }

    /**
     * @param entity
     * @return
     */
    public <T extends Model> RepositoryInterface getRepository (Class<T> entity)
    {
        if (entity == null)
            return null;
        if (entity.equals(Assignment.class))
            return new AssignmentRepository();
        if (entity.equals(Category.class))
            return new CategoryRepository();
        if (entity.equals(Course.class))
            return new CourseRepository();
        if (entity.equals(Agenda.class))
            return new CalendarRepository();


        return null;
    }

}
