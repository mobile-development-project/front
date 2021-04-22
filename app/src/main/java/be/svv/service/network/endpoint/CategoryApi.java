package be.svv.service.network.endpoint;

import java.util.List;

import be.svv.model.Assignment;
import be.svv.model.Category;
import be.svv.model.request.AssignmentRequest;
import be.svv.model.request.CategoryRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CategoryApi
{

    @GET("categories")
    Call<List<Category>> getAll ();

    @POST("categories")
    Call<Category> add (@Body CategoryRequest categoryRequest);

    @PUT("categories/{id}")
    Call<Category> update (@Path("id") int id, @Body CategoryRequest request);

    @DELETE("categories/{id}")
    Call<Category> delete (@Path("id") int id);

}
