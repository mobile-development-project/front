package be.svv.service.network.endpoint;

import java.util.List;

import be.svv.model.Assignment;
import be.svv.model.Course;
import be.svv.model.Model;
import be.svv.model.request.AssignmentRequest;
import be.svv.model.request.CourseRequest;
import be.svv.model.request.ModelRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface AssignmentApi
{

    @GET("courses")
    Call<List<Assignment>> getAll ();

    @POST("assignments")
    Call<Assignment> add (@Body ModelRequest request);
}
