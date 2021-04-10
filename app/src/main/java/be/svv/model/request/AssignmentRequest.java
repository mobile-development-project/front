package be.svv.model.request;

public class AssignmentRequest extends ModelRequest
{

    private String name;

    private String course;

    public AssignmentRequest (String name, String course)
    {
        this.name = name;
        this.course = course;
    }

    public String getCourse ()
    {
        return course;
    }

    public String getName ()
    {
        return name;
    }

    @Override
    public String toString ()
    {
        return "AssignmentRequest{" + "name='" + name + '\'' + ", course='" + course + '\'' + '}';
    }
}
