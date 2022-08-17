package com.example.unicon;

import android.app.Activity;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class APICall {
    Activity activity;
    EditSharedPreference editSharedPreference;

    APICall(Activity activity, EditSharedPreference editSharedPreference){
        this.activity = activity;
        this.editSharedPreference = editSharedPreference;
    }
    public void callWithURL(String url)
    {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                // we are using GET HTTP request method
                Request.Method.GET,
                // url we want to send the HTTP request to
                url,
                // this parameter is used to send a JSON object to the
                // server, since this is not required in our case,
                // we are keeping it `null`
                null,

                // lambda function for handling the case
                // when the HTTP request succeeds
                (Response.Listener<JSONObject>) response -> {
                    try {
                        float newDollarValue = Float.parseFloat(response.getJSONObject("rates").getString("INR"));
                        Toast.makeText(activity, "Fetch Successful "+newDollarValue, Toast.LENGTH_SHORT).show();
                        editSharedPreference.updateDollarValue(newDollarValue);
                    } catch (JSONException e) {
                        Log.e("APICall","JSON Parsing Problem");
                        e.printStackTrace();
                    }
                },
                // lambda function for handling the case
                // when the HTTP request fails
                (Response.ErrorListener) error -> {
                    Toast.makeText(activity, "No Internet Connection", Toast.LENGTH_SHORT).show();
                    Log.e("APICall", "Internet Connection Problem");
                }
        );
        // Necessary for API Call -> Checkout Why?
        RequestQueue requestQueue = Volley.newRequestQueue(activity);
        requestQueue.add(jsonObjectRequest);
    }
}
