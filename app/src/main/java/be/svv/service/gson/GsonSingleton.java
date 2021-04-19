package be.svv.service.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

public class GsonSingleton
{

    private static GsonSingleton instance;
    private GsonBuilder gsonBuilder;
    private Gson gson;

    private GsonSingleton ()
    {
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
    }

    public static synchronized GsonSingleton getInstance ()
    {
        if (instance == null)
        {
            instance = new GsonSingleton();
        }
        return instance;
    }

    public <T> T fromJson (String json, Type type)
    {
        return gson.fromJson(json, type);
    }

    public String toJson (Object object)
    {
        return gson.toJson(object);
    }

}
