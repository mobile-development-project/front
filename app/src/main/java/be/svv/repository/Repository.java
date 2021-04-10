package be.svv.repository;

import android.util.Log;

import java.util.List;

import be.svv.model.Course;
import be.svv.model.Model;
import be.svv.model.request.CourseRequest;
import be.svv.model.request.ModelRequest;
import be.svv.service.network.RetrofitService;
import be.svv.service.network.endpoint.ApiInterface;
import be.svv.viewmodel.ViewModelCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repository<T extends Model>
{

    private ApiInterface api;
    private Class model;

    protected Repository (Class<T> model)
    {
        api = RetrofitService.getClient().create(ApiInterface.class);
        this.model = model;
    }

    public void getAll (final ViewModelCallback callback)
    {
        api.getAll(model).enqueue(new Callback<List<T>>()
        {
            @Override
            public void onResponse (Call<List<T>> call, Response<List<T>> response)
            {
                callback.onResponse(response);
            }

            @Override
            public void onFailure (Call<List<T>> call, Throwable t)
            {

            }
        });
    }

    public <T extends ModelRequest> void add (T modelRequest, final ViewModelCallback callback)
    {
        api.add(modelRequest, model).enqueue(new Callback<T>()
        {
            @Override
            public void onResponse (Call<T> call, Response<T> response)
            {
                callback.onResponse(response);
            }

            @Override
            public void onFailure (Call<T> call, Throwable t)
            {

            }
        });
    }


}
