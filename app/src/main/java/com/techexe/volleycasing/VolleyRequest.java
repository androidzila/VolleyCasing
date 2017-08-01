package com.techexe.volleycasing;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.techexe.volleycasing.Common.HTTPStatusCodes;
import com.techexe.volleycasing.Common.NetworkUtils;
import com.techexe.volleycasing.Common.Preferences;
import com.techexe.volleycasing.Common.ProgressDialogUtils;
import com.techexe.volleycasing.Interfaces.OnResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by rgi-5 on 31/7/17.
 */

public class VolleyRequest {

    public static final int METHOD_GET = Request.Method.GET;
    public static final int METHOD_PUT = Request.Method.PUT;
    public static final int METHOD_POST = Request.Method.POST;
    public static final int METHOD_DELETE = Request.Method.DELETE;
    public static final int METHOD_PATCH = Request.Method.PATCH;

    public static int  ONLINE_MODE = 0;
    public static int  OFFLINE_MODE = 1;
    public static int  BOTH_MODE = 2;

    public static final String MODE_TYPE = "online";

    private String[] MODE_TYPES = {"online","offline","both"};


    private Context context=null;
    private ProgressDialogUtils progressDialogUtils = null;
    private boolean showProgress = false;
    private OnResponse onResponse = null;
    private int METHOD;

    private Preferences preferences = null;
    JsonObjectRequest jsonObjectRequest;
    JsonArrayRequest jsonArrayRequest;
    private int STATUS_CODE;

    private ArrayList<String> requestQueue;
    public VolleyRequest(Context context,boolean showprogress) {
        this.context = context;
        progressDialogUtils = new ProgressDialogUtils(context);
        preferences = new Preferences(context);
        showProgress = showprogress;
        requestQueue = new ArrayList<>();
    }


    public JsonObjectRequest jsonObjectRequest(@NonNull String Url, @NonNull int method, @NonNull JSONObject jsonObject, final OnResponse onResponse) {
        try {
            if (context == null){
                Log.e("Error","Context can not be null");
            }
            if(!NetworkUtils.isNetworkAvailable(context)){
                if (MODE_TYPE.equals("0")){

                }
            }
            showProgress();
            Log.d("request",jsonObject.toString());
            jsonObjectRequest = new JsonObjectRequest(method, Url, jsonObject,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            dismisProgress();
                            if(onResponse != null){
                                onResponse.onSuccess(response.toString(), STATUS_CODE);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            requestQueue.remove(jsonObjectRequest.toString());
                            dismisProgress();
                            try {
                                String jsonErrorData = new String(error.networkResponse.data, HttpHeaderParser.parseCharset(error.networkResponse.headers));
                                STATUS_CODE=error.networkResponse.statusCode;

                                if (STATUS_CODE== HTTPStatusCodes.CODE_401) {

                                }
                                if (STATUS_CODE== HTTPStatusCodes.CODE_503){

                                }
                                if(onResponse != null){
                                    onResponse.onError(jsonErrorData, STATUS_CODE);
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }) {

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    //  String credentials = "username:password";
                    //   String auth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                    //   headers.put("Content-Type", "application/json");
         //           headers.put("Authorization", prf.loadPrefString("Authorization_Token"));

                    return headers;
                }

                @Override
                protected Response<JSONObject> parseNetworkResponse(NetworkResponse response) {

                    dismisProgress();

                    STATUS_CODE = response.statusCode;
                    //     Log.e("VOLLY_RESPONSE", "statusCode : " + STATUS_CODE);

                    try {
                        if (STATUS_CODE == HTTPStatusCodes.CODE_200 ||
                                STATUS_CODE == HTTPStatusCodes.CODE_201 ||
                                STATUS_CODE == HTTPStatusCodes.CODE_304) {

                            String json = new String(response.data,
                                    HttpHeaderParser.parseCharset(response.headers));

                            if(!json.contains("{") || !json.contains("}")){
                                return Response.success(new JSONObject(), HttpHeaderParser.parseCacheHeaders(response));
                            }
                            else{
                                return Response.success(new JSONObject(json), HttpHeaderParser.parseCacheHeaders(response));
                            }
                        } else if (STATUS_CODE == HTTPStatusCodes.CODE_204) {
                            return Response.success(new JSONObject(), HttpHeaderParser.parseCacheHeaders(response));
                        } else if (STATUS_CODE == HTTPStatusCodes.CODE_422) {
                            return Response.error(new ParseError(response));
                        }
                        else {
                        //    showErrorMessage();
                            return null;
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        return Response.error(new ParseError(response));
                    }
                }
            };
            jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(
                    0,
                    0,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            addRequest(jsonObjectRequest);
            requestQueue.add(jsonObjectRequest.toString());
            Log.i("Object Request Sequence"," "+jsonObjectRequest.getSequence());
            jsonObjectRequest.getSequence();


        } catch (Exception e) {
            e.printStackTrace();
            dismisProgress();
        }

        return jsonObjectRequest;
    }



    public JsonArrayRequest jsonArrayRequest(@NonNull String Url, @NonNull int method, @NonNull JSONArray jsonArray, final OnResponse onResponse) {
        try {
            if (context == null){
                Log.e("Error","Context can not be null");
            }
            if(!NetworkUtils.isNetworkAvailable(context)){
                if (MODE_TYPE.equals("0")){

                }
            }
            showProgress();
            Log.d("request",jsonArray.toString());
            jsonArrayRequest = new JsonArrayRequest(method, Url, jsonArray,
                    new Response.Listener<JSONArray>() {
                        @Override
                        public void onResponse(JSONArray response) {
                            dismisProgress();
                            if(onResponse != null){
                                onResponse.onSuccess(response.toString(), STATUS_CODE);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            dismisProgress();
                            try {
                                String jsonErrorData = new String(error.networkResponse.data, HttpHeaderParser.parseCharset(error.networkResponse.headers));
                                STATUS_CODE=error.networkResponse.statusCode;

                                if (STATUS_CODE== HTTPStatusCodes.CODE_401) {

                                }
                                if (STATUS_CODE== HTTPStatusCodes.CODE_503){

                                }
                                if(onResponse != null){
                                    onResponse.onError(jsonErrorData, STATUS_CODE);
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }) {

                @Override
                public Map<String, String> getHeaders() throws AuthFailureError {
                    Map<String, String> headers = new HashMap<>();
                    //  String credentials = "username:password";
                    //   String auth = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);
                    //   headers.put("Content-Type", "application/json");
         //           headers.put("Authorization", prf.loadPrefString("Authorization_Token"));

                    return headers;
                }

                @Override
                protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {

                    dismisProgress();

                    STATUS_CODE = response.statusCode;
                    //     Log.e("VOLLY_RESPONSE", "statusCode : " + STATUS_CODE);

                    try {
                        if (STATUS_CODE == HTTPStatusCodes.CODE_200 ||
                                STATUS_CODE == HTTPStatusCodes.CODE_201 ||
                                STATUS_CODE == HTTPStatusCodes.CODE_304) {

                            String json = new String(response.data,
                                    HttpHeaderParser.parseCharset(response.headers));

                            if(!json.contains("{") || !json.contains("}")){
                                return Response.success(new JSONArray(), HttpHeaderParser.parseCacheHeaders(response));
                            }
                            else{
                                return Response.success(new JSONArray(json), HttpHeaderParser.parseCacheHeaders(response));
                            }
                        } else if (STATUS_CODE == HTTPStatusCodes.CODE_204) {
                            return Response.success(new JSONArray(), HttpHeaderParser.parseCacheHeaders(response));
                        } else if (STATUS_CODE == HTTPStatusCodes.CODE_422) {
                            return Response.error(new ParseError(response));
                        }
                        else {
                        //    showErrorMessage();
                            return null;
                        }

                    } catch (Exception e) {
                        e.printStackTrace();
                        return Response.error(new ParseError(response));
                    }
                }
            };
            jsonArrayRequest.setRetryPolicy(new DefaultRetryPolicy(
                    0,
                    0,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            addRequest(jsonArrayRequest);

            Log.i("Array Request Sequence"," "+jsonArrayRequest.getSequence());
            jsonArrayRequest.getSequence();

        } catch (Exception e) {
            e.printStackTrace();
            dismisProgress();
        }

        return jsonArrayRequest;
    }


    private void showProgress() {
        try {
            if (showProgress) {
                progressDialogUtils.showLoadingProgress();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void dismisProgress() {
        try {
            if (showProgress && progressDialogUtils != null) {
                progressDialogUtils.dismissProgress();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private <T> void addRequest(Request<T> req) {
        MyAplication.getGGApp().addToRequestQueue(req);
    }

}
