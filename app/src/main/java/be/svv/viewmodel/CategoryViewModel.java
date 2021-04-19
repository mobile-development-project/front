package be.svv.viewmodel;

import be.svv.model.Category;
import be.svv.repository.RepositoryFactory;

public class CategoryViewModel extends EntityViewModel<Category>
{

    public CategoryViewModel ()
    {
        super(RepositoryFactory.getRepository(Category.class));
    }
}
