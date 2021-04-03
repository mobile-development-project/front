package be.svv.repository;

import android.content.Context;

import be.svv.globals.ApiUrl;

public class CourseRepository extends Repository
{

    public CourseRepository (Context context)
    {
        super(ApiUrl.COURSES, context);
    }

}
