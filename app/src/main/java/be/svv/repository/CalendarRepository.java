package be.svv.repository;

import android.util.Log;

import java.util.List;

import be.svv.model.Agenda;
import be.svv.model.request.AgendaRequest;
import be.svv.model.request.DeleteCategoryRequest;
import be.svv.model.request.ModelRequest;
import be.svv.service.network.RetrofitService;
import be.svv.service.network.endpoint.CalendarApi;
import be.svv.viewmodel.ViewModelCallback;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CalendarRepository implements RepositoryInterface
{

    private CalendarApi api;

    public CalendarRepository ()
    {
        api = RetrofitService.getClient().create(CalendarApi.class);
    }

    @Override
    public void getAll (ViewModelCallback callback)
    {
        api.getAll().enqueue(new Callback<List<Agenda>>()
        {
            @Override
            public void onResponse (Call<List<Agenda>> call, Response<List<Agenda>> response)
            {
                callback.onResponse(response);
            }

            @Override
            public void onFailure (Call<List<Agenda>> call, Throwable t)
            {

            }
        });
    }

    @Override
    public <T extends ModelRequest> void add (T request, ViewModelCallback callback)
    {
        api.add((AgendaRequest) request).enqueue(new Callback<Agenda>()
        {
            @Override
            public void onResponse (Call<Agenda> call, Response<Agenda> response)
            {
                callback.onResponse(response);
            }

            @Override
            public void onFailure (Call<Agenda> call, Throwable t)
            {
            }
        });
    }

    @Override
    public <T extends ModelRequest> void update (T request, int id, ViewModelCallback callback)
    {

    }

    @Override
    public void delete (int id, ViewModelCallback callback)
    {

    }

    public void deleteCategory (int id, DeleteCategoryRequest deleteCategoryRequest, ViewModelCallback callback)
    {
        api.deleteCategory(id, deleteCategoryRequest).enqueue(new Callback<Agenda>()
        {
            @Override
            public void onResponse (Call<Agenda> call, Response<Agenda> response)
            {
                callback.onResponse(response);
            }

            @Override
            public void onFailure (Call<Agenda> call, Throwable t)
            {

            }
        });
    }

    public void getAllByDate (String date, ViewModelCallback callback)
    {
        api.getAllByDate(date).enqueue(new Callback<List<Agenda>>()
        {
            @Override
            public void onResponse (Call<List<Agenda>> call, Response<List<Agenda>> response)
            {
                callback.onResponse(response);
            }

            @Override
            public void onFailure (Call<List<Agenda>> call, Throwable t)
            {

            }
        });
    }
}
