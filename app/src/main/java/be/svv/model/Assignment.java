package be.svv.model;

import java.util.Date;

public class Assignment extends Model
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

    public void setId (int id)
    {
        this.id = id;
    }

    public String getName ()
    {
        return name;
    }

    public void setName (String name)
    {
        this.name = name;
    }

    public Course getCourse ()
    {
        return course;
    }

    public void setCourse (Course course)
    {
        this.course = course;
    }

    public boolean isAllowNotifications ()
    {
        return allowNotifications;
    }

    public void setAllowNotifications (boolean allowNotifications)
    {
        this.allowNotifications = allowNotifications;
    }

    public Date getCreatedAt ()
    {
        return createdAt;
    }

    public void setCreatedAt (Date createdAt)
    {
        this.createdAt = createdAt;
    }

    public Date getFinishAt ()
    {
        return finishAt;
    }

    public void setFinishAt (Date finishAt)
    {
        this.finishAt = finishAt;
    }

    @Override
    public String toString ()
    {
        return "Assignment{" + "id=" + id + ", name='" + name + '\'' + ", course=" + course + ", allowNotifications=" + allowNotifications + ", createdAt=" + createdAt + ", finishAt=" + finishAt + '}';
    }
}
