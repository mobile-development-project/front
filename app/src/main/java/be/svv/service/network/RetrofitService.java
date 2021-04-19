package be.svv.service.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService
{

    private static Retrofit retrofit;
    private static String BASE_URL = "http://192.168.0.134:8000/api/";

    public static Retrofit getClient ()
    {
        if (retrofit == null)
        {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }

        return retrofit;
    }

}