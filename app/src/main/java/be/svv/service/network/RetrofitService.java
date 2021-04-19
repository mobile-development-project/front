package be.svv.service.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitService
{

    private static Retrofit retrofit;
    private static String BASE_URL = "http://192.168.0.134:8000/api/";

    public static synchronized Retrofit getClient ()
    {
        if (RetrofitService.retrofit == null)
        {
            RetrofitService.retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();
        }

        return RetrofitService.retrofit;
    }

}