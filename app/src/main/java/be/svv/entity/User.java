package be.svv.entity;

import java.util.ArrayList;
import java.util.Arrays;

public class User extends Entity
{

    private int id;

    private String[] roles;

    private String email;

    private ArrayList<Assignment> assignments;

    private ArrayList<Category> categories;

    private ArrayList<Course> courses;

    public int getId ()
    {
        return id;
    }

    public String getEmail ()
    {
        return email;
    }

    public String[] getRoles ()
    {
        return roles;
    }

    public ArrayList<Assignment> getAssignments ()
    {
        return assignments;
    }

    public ArrayList<Category> getCategories ()
    {
        return categories;
    }

    public ArrayList<Course> getCourses ()
    {
        return courses;
    }

    @Override
    public String toString ()
    {
        return "User{" + "id=" + id + ", " + "roles=" + Arrays.toString(roles) + ", " + "email='" + email + '\'' + ", " + "assignments=" + assignments + ", " + "categories=" + categories + ", " + "courses=" + courses + '}';
    }
}


