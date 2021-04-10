package be.svv.viewmodel;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import be.svv.model.Course;
import be.svv.model.request.CourseRequest;
import be.svv.repository.CourseRepository;
import be.svv.service.network.RetrofitService;
import be.svv.service.network.endpoint.CourseApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CourseViewModel extends ViewModel
{

    private CourseRepository courseRepository;
    private MutableLiveData<List<Course>> coursesLiveData;

    public CourseViewModel ()
    {
        courseRepository = new CourseRepository();
    }

    private void populateList ()
    {
        courseRepository.getAll(new ViewModelCallback<Course>()
        {
            @Override
            public void onResponse (Response<List<Course>> response)
            {
                coursesLiveData.setValue(response.body());
            }
        });
    }

    public MutableLiveData<List<Course>> getAllCourses ()
    {
        if (coursesLiveData == null)
        {
            coursesLiveData = new MutableLiveData<List<Course>>();
            populateList();
        }
        return coursesLiveData;
    }

    public void addCourse (CourseRequest courseRequest)
    {
        courseRepository.add(courseRequest, new ViewModelCallback<Course>()
        {
            @Override
            public void onResponse (Response<List<Course>> response)
            {
                populateList();
            }
        });
    }

}
