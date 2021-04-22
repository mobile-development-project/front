package be.svv.model;

public class Category extends Model
{

    private int id;

    private String name;

    private int color;

    private User user;

    public int getId ()
    {
        return id;
    }

    public String getName ()
    {
        return name;
    }

    public int getColor ()
    {
        return color;
    }

    public User getUser ()
    {
        return user;
    }
}
