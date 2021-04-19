package be.svv.model.request;

public class AssignmentRequest extends ModelRequest
{

    private String name;

    private String course;

    private Boolean allowNotifications;

    public AssignmentRequest (String name, String course, Boolean allowNotifications)
    {
        this.name = name;
        this.course = course;
        this.allowNotifications = allowNotifications;
    }

    public String getCourse ()
    {
        return course;
    }

    public String getName ()
    {
        return name;
    }

    public Boolean getAllowNotifications ()
    {
        return allowNotifications;
    }

    @Override
    public String toString ()
    {
        return "AssignmentRequest{" + "name='" + name + '\'' + ", course='" + course + '\'' + '}';
    }
}
