package be.svv.repository;

import android.util.Log;

import java.util.List;

import be.svv.model.Category;
import be.svv.model.request.CategoryRequest;
import be.svv.model.request.ModelRequest;
import be.svv.service.network.RetrofitService;
import be.svv.service.network.endpoint.CategoryApi;
import be.svv.viewmodel.ViewModelCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CategoryRepository implements RepositoryInterface<Category>
{

    private CategoryApi api;

    public CategoryRepository ()
    {
        api = RetrofitService.getClient().create(CategoryApi.class);
    }

    @Override
    public void getAll (ViewModelCallback callback)
    {
        api.getAll().enqueue(new Callback<List<Category>>()
        {
            @Override
            public void onResponse (Call<List<Category>> call, Response<List<Category>> response)
            {
                callback.onResponse(response);
            }

            @Override
            public void onFailure (Call<List<Category>> call, Throwable t)
            {

            }
        });
    }

    @Override
    public <T extends ModelRequest> void add (T request, ViewModelCallback callback)
    {
        api.add((CategoryRequest) request).enqueue(new Callback<Category>()
        {
            @Override
            public void onResponse (Call<Category> call, Response<Category> response)
            {
                callback.onResponse(response);
            }

            @Override
            public void onFailure (Call<Category> call, Throwable t)
            {

            }
        });
    }

    @Override
    public <T extends ModelRequest> void update (T request, int id, ViewModelCallback callback)
    {
        api.update(id, (CategoryRequest) request).enqueue(new Callback<Category>()
        {
            @Override
            public void onResponse (Call<Category> call, Response<Category> response)
            {
                callback.onResponse(response);
            }

            @Override
            public void onFailure (Call<Category> call, Throwable t)
            {
            }
        });
    }

    @Override
    public void delete (int id, ViewModelCallback callback)
    {
        api.delete(id);
    }

}
