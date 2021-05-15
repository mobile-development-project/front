package be.svv.model;

import java.util.ArrayList;

public class Agenda extends Model
{

    private int id;

    private String date;

    private ArrayList<Course> courses;

    private ArrayList<Category> categories;

    public int getId ()
    {
        return id;
    }

    public String getDate ()
    {
        return date;
    }

    public ArrayList<Course> getCourses ()
    {
        return courses;
    }

    public ArrayList<Category> getCategories ()
    {
        return categories;
    }

    @Override
    public String toString ()
    {
        return "Calendar{" + "id=" + id + ", date='" + date + '\'' + ", courses=" + courses + ", categories=" + categories + '}';
    }
}
