package be.svv.model.request;

public class DeleteCategoryRequest extends ModelRequest
{

    private int id;

    public DeleteCategoryRequest (int id)
    {
        this.id = id;
    }

    public int getId ()
    {
        return id;
    }
}
