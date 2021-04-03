package be.svv.entity;

import java.util.Arrays;

public class User
{

    private int id;

    private String[] roles;

    private String email;

    private Assignment[] assignments;

    private Category[] categories;

    private Course[] courses;

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

    public Assignment[] getAssignments ()
    {
        return assignments;
    }

    public Category[] getCategories ()
    {
        return categories;
    }

    public Course[] getCourses ()
    {
        return courses;
    }

    @Override
    public String toString ()
    {
        return "User{" + "id=" + id + ", " +
                "roles=" + Arrays.toString(roles) + ", " +
                "email='" + email + '\'' + ", " +
                "assignments=" + Arrays.toString(assignments) + ", " +
                "categories=" + Arrays.toString(categories) + ", " +
                "courses=" + Arrays.toString(courses) + '}';
    }
}


