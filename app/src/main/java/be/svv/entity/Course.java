package be.svv.entity;

import java.util.ArrayList;

public class Course extends Entity
{

    private int id;

    private String name;

    private ArrayList<Assignment> assignments;

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
        return "Course{" + "id=" + id + ", name='" + name + '\'' + ", assignments=" + assignments + '}';
    }
}
