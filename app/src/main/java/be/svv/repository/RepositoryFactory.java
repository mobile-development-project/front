package be.svv.repository;

import android.content.Context;

import be.svv.model.Assignment;
import be.svv.model.Category;
import be.svv.model.Course;
import be.svv.model.Model;

//public class RepositoryFactory
//{
//
//    private Context context;
//
//    public RepositoryFactory (Context context)
//    {
//        this.context = context;
//    }
//
//    /**
//     * @param entity
//     * @return
//     */
//    public <T extends Model> Repository getRepository (Class<T> entity)
//    {
//        if (entity == null)
//            return null;
//        if (entity.equals(Assignment.class))
//            return new AssignmentRepository(context);
//        if (entity.equals(Category.class))
//            return new CategoryRepository(context);
//        if (entity.equals(Course.class))
//            return new CourseRepository(context);
//
//        return null;
//    }
//
//}
