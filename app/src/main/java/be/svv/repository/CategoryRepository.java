package be.svv.repository;

import android.content.Context;

import be.svv.globals.ApiUrl;

public class CategoryRepository extends Repository
{

    public CategoryRepository (Context context)
    {
        super(ApiUrl.CATEGORIES, context);
    }
}
