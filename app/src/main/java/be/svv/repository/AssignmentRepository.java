package be.svv.repository;

import java.util.List;

import be.svv.model.Assignment;
import be.svv.model.request.AssignmentRequest;
import be.svv.model.request.ModelRequest;
import be.svv.service.network.RetrofitService;
import be.svv.service.network.endpoint.AssignmentApi;
import be.svv.viewmodel.ViewModelCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AssignmentRepository implements RepositoryInterface<Assignment>
{

    private AssignmentApi api;

    public AssignmentRepository ()
    {
        api = RetrofitService.getClient().create(AssignmentApi.class);
    }

    @Override
    public void getAll (ViewModelCallback callback)
    {
        api.getAll().enqueue(new Callback<List<Assignment>>()
        {
            @Override
            public void onResponse (Call<List<Assignment>> call, Response<List<Assignment>> response)
            {
                callback.onResponse(response);
            }

            @Override
            public void onFailure (Call<List<Assignment>> call, Throwable t)
            {

            }
        });
    }

    @Override
    public <T extends ModelRequest> void add (T request, ViewModelCallback callback)
    {
        api.add((AssignmentRequest) request).enqueue(new Callback<Assignment>()
        {
            @Override
            public void onResponse (Call<Assignment> call, Response<Assignment> response)
            {
                callback.onResponse(response);
            }

            @Override
            public void onFailure (Call<Assignment> call, Throwable t)
            {

            }
        });
    }

    @Override
    public <T extends ModelRequest> void update (T request, int id, ViewModelCallback callback)
    {
        api.update(id, (AssignmentRequest) request).enqueue(new Callback<Assignment>()
        {
            @Override
            public void onResponse (Call<Assignment> call, Response<Assignment> response)
            {
                callback.onResponse(response);
            }

            @Override
            public void onFailure (Call<Assignment> call, Throwable t)
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
