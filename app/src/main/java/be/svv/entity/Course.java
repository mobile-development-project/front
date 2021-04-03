package be.svv.entity;

import androidx.annotation.NonNull;

import java.util.Arrays;

public class Course extends Entity
{

    private int id;

    private String name;

    private Assignment[] assignments;

    public int getId ()
    {
        return id;
    }

    public String getName ()
    {
        return name;
    }

    public Assignment[] getAssignments ()
    {
        return assignments;
    }

    @Override
    public String toString ()
    {
        return "Course{" + "id=" + id + ", name='" + name + '\'' + ", assignments=" + Arrays.toString(assignments) + '}';
    }
}
