package be.svv.repository;

import android.util.Log;

import java.util.List;

import be.svv.model.Assignment;
import be.svv.model.Course;
import be.svv.model.request.AssignmentRequest;
import be.svv.model.request.CourseRequest;
import be.svv.model.request.ModelRequest;
import be.svv.service.network.RetrofitService;
import be.svv.service.network.endpoint.CourseApi;
import be.svv.viewmodel.ViewModelCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseRepository implements RepositoryInterface
{

    private CourseApi api;

    public CourseRepository ()
    {
        api = RetrofitService.getClient().create(CourseApi.class);
    }

    @Override
    public void getAll (ViewModelCallback callback)
    {
        api.getAll().enqueue(new Callback<List<Course>>()
        {
            @Override
            public void onResponse (Call<List<Course>> call, Response<List<Course>> response)
            {
                callback.onResponse(response);
            }

            @Override
            public void onFailure (Call<List<Course>> call, Throwable t)
            {
            }
        });
    }

    @Override
    public <T extends ModelRequest> void add (T request, ViewModelCallback callback)
    {
        api.add((CourseRequest) request).enqueue(new Callback<Course>()
        {
            @Override
            public void onResponse (Call<Course> call, Response<Course> response)
            {
                callback.onResponse(response);
            }

            @Override
            public void onFailure (Call<Course> call, Throwable t)
            {
            }
        });
    }

    @Override
    public <T extends ModelRequest> void update (T request, int id, ViewModelCallback callback)
    {
        api.update(id, (CourseRequest) request).enqueue(new Callback<Course>()
        {
            @Override
            public void onResponse (Call<Course> call, Response<Course> response)
            {
                callback.onResponse(response);
            }

            @Override
            public void onFailure (Call<Course> call, Throwable t)
            {
            }
        });
    }

    @Override
    public void delete (int id, ViewModelCallback callback)
    {
        api.delete(id).enqueue(new Callback<Course>()
        {
            @Override
            public void onResponse (Call<Course> call, Response<Course> response)
            {
                callback.onResponse(response);
            }

            @Override
            public void onFailure (Call<Course> call, Throwable t)
            {

            }
        });
    }

}
