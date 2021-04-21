package be.svv.service.network.endpoint;

import java.util.List;

import be.svv.model.Assignment;
import be.svv.model.Course;
import be.svv.model.request.AssignmentRequest;
import be.svv.model.request.CourseRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CourseApi
{

    @GET("courses")
    Call<List<Course>> getAll ();

    @POST("courses")
    Call<Course> add (@Body CourseRequest courseRequest);

    @PUT("courses/{id}")
    Call<Course> update (@Path("id") int id, @Body CourseRequest request);

    @DELETE("courses/{id}")
    Call<Course> delete (@Path("id") int id);

}
