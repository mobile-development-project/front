package be.svv.entity;

public class Category extends Entity
{

    private int id;

    private String name;

    private User user;

    public int getId ()
    {
        return id;
    }

    public String getName ()
    {
        return name;
    }

    public User getUser ()
    {
        return user;
    }
}
