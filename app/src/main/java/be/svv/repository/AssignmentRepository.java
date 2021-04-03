package be.svv.repository;

import android.content.Context;

import be.svv.globals.ApiUrl;

public class AssignmentRepository extends Repository
{

    public AssignmentRepository (Context context)
    {
        super(ApiUrl.ASSIGNMENTS, context);
    }
}
