package be.svv.repository;

import java.util.List;

import be.svv.model.Course;
import be.svv.model.Model;
import be.svv.model.request.CourseRequest;
import be.svv.service.network.RetrofitService;
import be.svv.service.network.endpoint.CourseApi;
import be.svv.viewmodel.ViewModelCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CourseRepository extends Repository<Course>
{
    public CourseRepository ()
    {
        super(Course.class);
    }

    //    public void getAll (final ViewModelCallback callback)
    //    {
    //        courseApi.getAll().enqueue(new Callback<List<Course>>()
    //        {
    //            @Override
    //            public void onResponse (Call<List<Course>> call, Response<List<Course>> response)
    //            {
    //                if (response.isSuccessful() && response.body() != null)
    //                {
    //                    callback.onResponse(response);
    //                }
    //            }
    //
    //            @Override
    //            public void onFailure (Call<List<Course>> call, Throwable t)
    //            {
    //            }
    //        });
    //    }

    //    public void add (CourseRequest courseRequest, final ViewModelCallback callback)
    //    {
    //        courseApi.add(courseRequest).enqueue(new Callback<Course>()
    //        {
    //            @Override
    //            public void onResponse (Call<Course> call, Response<Course> response)
    //            {
    //                callback.onResponse(response);
    //            }
    //
    //            @Override
    //            public void onFailure (Call<Course> call, Throwable t)
    //            {
    //
    //            }
    //        });
    //    }


}
