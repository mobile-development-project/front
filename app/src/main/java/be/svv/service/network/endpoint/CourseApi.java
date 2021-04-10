package be.svv.service.network.endpoint;


import be.svv.model.Course;
import be.svv.model.request.CourseRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface CourseApi extends ApiInterface
{


    @POST("courses")
    Call<Course> add (@Body CourseRequest courseRequest);

}
