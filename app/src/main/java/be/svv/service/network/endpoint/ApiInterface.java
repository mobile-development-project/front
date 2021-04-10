package be.svv.service.network.endpoint;

import java.util.List;

import be.svv.model.Course;
import be.svv.model.request.CourseRequest;
import be.svv.model.request.ModelRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface
{

    @GET("courses")
    Call<List<Course>> getAll (@Query("course") Class<Course> course);

    @POST("courses")
    Call<Course> add (@Body ModelRequest courseRequest, @Query("course") Class<Course> course);

}
