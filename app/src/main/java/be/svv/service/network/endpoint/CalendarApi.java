package be.svv.service.network.endpoint;

import java.util.List;

import be.svv.model.Agenda;
import be.svv.model.request.AgendaRequest;
import be.svv.model.request.DeleteCategoryRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface CalendarApi
{

    @GET("calendars")
    Call<List<Agenda>> getAll ();

    @GET("calendars")
    Call<List<Agenda>> getAllByDate (@Query("date") String date);

    @POST("calendars")
    Call<Agenda> add (@Body AgendaRequest agendaRequest);

    @PUT("calendar/{id}/category")
    Call<Agenda> deleteCategory (@Path("id") int id, @Body DeleteCategoryRequest deleteCategoryRequest);

}
