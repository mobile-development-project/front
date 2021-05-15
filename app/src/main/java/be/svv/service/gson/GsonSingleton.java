package be.svv.service.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

public class GsonSingleton
{

    private static GsonSingleton instance;
    private Gson gson;

    private GsonSingleton ()
    {
        gson = (new GsonBuilder()).create();
    }

    public static synchronized GsonSingleton getInstance ()
    {
        if (instance == null)
        {
            instance = new GsonSingleton();
        }
        return instance;
    }

    /**
     * Returns an object from json
     *
     * @param json
     * @param type
     * @param <T>
     * @return object
     */
    public <T> T fromJson (String json, Type type)
    {
        return gson.fromJson(json, type);
    }

    /**
     * Returns json from an object
     *
     * @param object
     * @return json object
     */
    public String toJson (Object object)
    {
        return gson.toJson(object);
    }

}
