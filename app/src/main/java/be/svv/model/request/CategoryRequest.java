package be.svv.model.request;

public class CategoryRequest extends ModelRequest
{

    String name;

    public CategoryRequest (String name)
    {
        this.name = name;
    }

    public String getName ()
    {
        return name;
    }

    @Override
    public String toString ()
    {
        return "CategoryRequest{" + "name='" + name + '\'' + '}';
    }
}
