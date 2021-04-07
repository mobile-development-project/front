package be.svv.repository;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import be.svv.entity.Entity;
import be.svv.service.Gson.GsonSingleton;
import be.svv.service.Volley.VolleyCallback;
import be.svv.service.Volley.VolleySingleton;

public class Repository
{

    protected final String URL;
    protected final Context context;

    public Repository (String URL, Context context)
    {
        this.URL = URL;
        this.context = context;
    }

    /**
     * Returns one item
     *
     * @param id
     * @param callback
     */
    public void findById (int id, final VolleyCallback callback)
    {
        getStringRequest(URL + "/" + id, callback);
    }

    /**
     * Returns a collection of items
     *
     * @param callback
     */
    public void findAll (final VolleyCallback callback)
    {
        getStringRequest(URL, callback);
    }

    public void add (int id, Object object, final VolleyCallback callback)
    {
        postStringRequest(Request.Method.POST, id, URL, object, callback);
    }

    public void update (int id, Object object, final VolleyCallback callback)
    {
        postStringRequest(Request.Method.PUT, id, URL, object, callback);
    }

    private void postStringRequest (int method, int id, String url, Object object, final VolleyCallback callback)
    {
        StringRequest stringRequest = new StringRequest(method, url + "/" + id, response ->
        {
            callback.onSuccess(response);
        }, error ->
        {
            NetworkResponse response = error.networkResponse;
            try
            {
                String responseData = new String(response.data);
                Log.d("ERROR", responseData);
                JSONObject responseObject = new JSONObject(responseData);
                //Log.e("ERROR", responseObject.optString("message"));
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        })
        {
            @Override
            public byte[] getBody ()
            {
                return GsonSingleton.getInstance().toJson(object).getBytes();
            }

            @Override
            public Map<String, String> getHeaders ()
            {
                Map<String, String> params = new HashMap<>();
                params.put("Content-Type", "application/json");
                return params;
            }
        };

        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

    /**
     * Fetches the data and call the callback passed in argument
     *
     * @param url
     * @param callback
     */
    private void getStringRequest (String url, final VolleyCallback callback)
    {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, response ->
        {
            callback.onSuccess(response);
        }, error ->
        {
            NetworkResponse response = error.networkResponse;
            try
            {
                String responseData = new String(response.data);
                JSONObject responseObject = new JSONObject(responseData);
                Log.d("ERROR", responseData);
                //Log.e("ERROR", responseObject.optString("message"));
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
        });

        VolleySingleton.getInstance(context).addToRequestQueue(stringRequest);
    }

}
