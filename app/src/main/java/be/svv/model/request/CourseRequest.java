package be.svv.model.request;

public class CourseRequest extends ModelRequest
{

    private String name;

    public CourseRequest (String name)
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
        return "CourseRequest{" + "name='" + name + '\'' + '}';
    }
}
