package be.svv.repository;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import be.svv.globals.ApiUrl;
import be.svv.service.Volley.VolleyCallback;
import be.svv.service.Volley.VolleySingleton;

public class Repository
{

    protected final String URL;
    protected final Context context;
    protected RequestQueue queue;

    public Repository (String URL, Context context)
    {
        this.URL = URL;
        this.context = context;
        queue = VolleySingleton.getInstance(context).getRequestQueue();
    }

    public void findById (int id, final VolleyCallback callback)
    {
        stringRequest(Request.Method.GET, URL + "/" + id, callback);
    }

    public void findAll (final VolleyCallback callback)
    {
        stringRequest(Request.Method.GET, URL, callback);
    }

    private void stringRequest (int method, String url, final VolleyCallback callback)
    {
        StringRequest stringRequest = new StringRequest(method, url, response ->
        {
            callback.onSuccess(response);
        }, error ->
        {
            Log.e("ERROR", error.getMessage());
        });
        queue.add(stringRequest);
    }
}
