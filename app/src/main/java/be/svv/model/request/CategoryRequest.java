package be.svv.model.request;

public class CategoryRequest extends ModelRequest
{

    private String name;

    private int color;

    public CategoryRequest (String name, int color)
    {
        this.name = name;
        this.color = color;
    }

    public String getName ()
    {
        return name;
    }

    public int getColor ()
    {
        return color;
    }

    @Override
    public String toString ()
    {
        return "CategoryRequest{" + "name='" + name + '\'' + '}';
    }
}
