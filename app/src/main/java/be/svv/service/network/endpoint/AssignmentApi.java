package be.svv.service.network.endpoint;

import java.util.List;

import be.svv.model.Assignment;
import be.svv.model.request.AssignmentRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface AssignmentApi
{

    @GET("assignments")
    Call<List<Assignment>> getAll ();

    @GET("assignments/near")
    Call<List<Assignment>> getNearAssignments ();

    @POST("assignments")
    Call<Assignment> add (@Body AssignmentRequest request);

    @PUT("assignments/{id}")
    Call<Assignment> update (@Path("id") int id, @Body AssignmentRequest request);

    @DELETE("assignments/{id}")
    Call<Assignment> delete (@Path("id") int id);

}
