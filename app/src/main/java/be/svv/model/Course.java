package be.svv.model;

import java.util.ArrayList;

public class Course extends Model
{

    private int id;

    private String name;

    private ArrayList<Assignment> assignments;

    public Course (String name)
    {
        this.name = name;
    }

    public int getId ()
    {
        return id;
    }

    public String getName ()
    {
        return name;
    }

    public ArrayList<Assignment> getAssignments ()
    {
        return assignments;
    }

    @Override
    public String toString ()
    {
        return name;
    }
}
