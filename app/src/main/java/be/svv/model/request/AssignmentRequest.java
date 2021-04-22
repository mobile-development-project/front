package be.svv.model.request;

public class AssignmentRequest extends ModelRequest
{

    private String name;

    private String course;

    private Boolean allowNotifications;

    private String finishAt;

    public AssignmentRequest (String name, String course, Boolean allowNotifications, String finishAt)
    {
        this.name = name;
        this.course = course;
        this.allowNotifications = allowNotifications;
        this.finishAt = finishAt;
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

    public String getFinishAt ()
    {
        return finishAt;
    }

    @Override
    public String toString ()
    {
        return "AssignmentRequest{" + "name='" + name + '\'' + ", course='" + course + '\'' + '}';
    }
}
