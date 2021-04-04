package be.svv.repository;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.StringRequest;

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

    /**
     * Returns one item
     *
     * @param id
     * @param callback
     */
    public void findById (int id, final VolleyCallback callback)
    {
        stringRequest(Request.Method.GET, URL + "/" + id, callback);
    }

    /**
     * Returns a collection of items
     *
     * @param callback
     */
    public void findAll (final VolleyCallback callback)
    {
        stringRequest(Request.Method.GET, URL, callback);
    }

    /**
     * Fetches the data and call the callback passed in argument
     *
     * @param method
     * @param url
     * @param callback
     */
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
