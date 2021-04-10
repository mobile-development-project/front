package be.svv.repository;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import java.util.List;

import be.svv.model.Assignment;
import be.svv.model.Course;
import be.svv.model.request.AssignmentRequest;
import be.svv.model.request.CourseRequest;
import be.svv.service.network.RetrofitService;
import be.svv.service.network.endpoint.AssignmentApi;
import be.svv.service.network.endpoint.CourseApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssignmentRepository
{

    private AssignmentApi api;

    public AssignmentRepository ()
    {
        api = RetrofitService.getClient().create(AssignmentApi.class);
    }

    public MutableLiveData<List<Assignment>> getAll ()
    {
        final MutableLiveData<List<Assignment>> mutableLiveData = new MutableLiveData<>();
        api.getAll().enqueue(new Callback<List<Assignment>>()
        {
            @Override
            public void onResponse (Call<List<Assignment>> call, Response<List<Assignment>> response)
            {
                if (response.isSuccessful() && response.body() != null)
                {
                    mutableLiveData.setValue(response.body());
                }
            }

            @Override
            public void onFailure (Call<List<Assignment>> call, Throwable t)
            {
            }
        });

        return mutableLiveData;
    }

    public void add (AssignmentRequest assignmentRequest)
    {
        api.add(assignmentRequest).enqueue(new Callback<Assignment>()
        {
            @Override
            public void onResponse (Call<Assignment> call, Response<Assignment> response)
            {
                Log.d("TAG", response.body().toString());
            }

            @Override
            public void onFailure (Call<Assignment> call, Throwable t)
            {

            }
        });
    }


}
