package com.techexe.volleycasing;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.techexe.volleycasing.Interfaces.OnResponse;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    VolleyRequest request;

    String url = "https://retail.globalgarner.com/api/sliders";
    String url1 = "http://bloombayindia.com/admin/index.php/Mobileapi/product/?name=f1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        request = new VolleyRequest(MainActivity.this,true);


        request.jsonArrayRequest(url, VolleyRequest.METHOD_GET, new JSONArray(), new OnResponse() {
            @Override
            public void onSuccess(String response, int statusCode) {
                Log.d("Response",response);
            }

            @Override
            public void onError(String errorData, int statusCode) {
                Log.d("Error",errorData);
            }
        });
        request.jsonObjectRequest(url1, VolleyRequest.METHOD_GET, new JSONObject(), new OnResponse() {
            @Override
            public void onSuccess(String response, int statusCode) {
                Log.d("Response",response);
            }

            @Override
            public void onError(String errorData, int statusCode) {
                Log.d("Error",errorData);
            }
        });

    }
}
