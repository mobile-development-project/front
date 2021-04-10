package be.svv.service.network.endpoint;

import java.util.List;

import be.svv.model.Category;
import be.svv.model.request.CategoryRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface CategoryApi
{

    @GET("categories")
    Call<List<Category>> getAll ();

    @POST("categories")
    Call<Category> add (@Body CategoryRequest categoryRequest);

}
