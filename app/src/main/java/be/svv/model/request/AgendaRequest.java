package be.svv.model.request;

import java.util.ArrayList;

public class AgendaRequest extends ModelRequest
{

    private String date;

    private ArrayList<String> categories;

    private ArrayList<String> courses;

    public AgendaRequest (String date, ArrayList<String> categories)
    {
        this.date = date;
        this.categories = categories;
    }

    public String getDate ()
    {
        return date;
    }

    public ArrayList<String> getCategories ()
    {
        return categories;
    }

}
