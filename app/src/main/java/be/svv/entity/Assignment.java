package be.svv.entity;

import java.util.Date;

public class Assignment
{

    private int id;

    private String name;

    private Course course;

    private boolean allowNotifications;

    private Date createdAt;

    private Date finishAt;

    public int getId ()
    {
        return id;
    }

    public Course getCourse ()
    {
        return course;
    }

    public String getName ()
    {
        return name;
    }

    public boolean isAllowNotifications ()
    {
        return allowNotifications;
    }

    public Date getCreatedAt ()
    {
        return createdAt;
    }

    public Date getFinishAt ()
    {
        return finishAt;
    }

    @Override
    public String toString ()
    {
        return "Assignment{" + "id=" + id + ", name='" + name + '\'' + ", course=" + course + ", allowNotifications=" + allowNotifications + ", createdAt=" + createdAt + ", finishAt=" + finishAt + '}';
    }
}
