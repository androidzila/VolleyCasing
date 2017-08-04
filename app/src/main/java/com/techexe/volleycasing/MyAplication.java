package com.techexe.volleycasing;

import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.techexe.volleycasing.Common.Preferences;
import com.techexe.volleycasing.Common.ProgressDialogUtils;

import static android.content.ContentValues.TAG;

/**
 * Created by Jaimin patel on 31/7/17.
 */

public class MyAplication extends MultiDexApplication {

    public static Resources resources;
    private static MyAplication myAplication;
    private RequestQueue mRequestQueue;

    public static synchronized MyAplication getMyApp() {
        return myAplication;
    }

    public ProgressDialogUtils progressDialogUtils= null;
    public Preferences preferences = null;
    public VolleyRequest request = null;
    @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
        myAplication= this;
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        resources = getResources();

       // progressDialogUtils = new ProgressDialogUtils(getApplicationContext());
        preferences = new Preferences(getApplicationContext());
        request = new VolleyRequest(getApplicationContext(),true, R.style.AppTheme_Dark_Dialog);

    }
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }
    public RequestQueue getRequestQueueCount() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        try {
            getRequestQueue().add(req);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public <T> void getRequestQueue(Request<T> req) {
        req.setTag(TAG);
        try {
            getRequestQueue().add(req).getSequence();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }


    public ProgressDialogUtils getDialog(Context context) {
        if (progressDialogUtils == null) {
            progressDialogUtils = new ProgressDialogUtils(context);
            return  progressDialogUtils;
        }else {
            return  progressDialogUtils;
        }
    }
    public Preferences getPreferences() {
        if (preferences == null) {
            preferences = new Preferences(getApplicationContext());
            return  preferences;
        }else {
            return  preferences;
        }
    }
    public VolleyRequest getRequest() {
        if (request == null) {
            request = new VolleyRequest(getApplicationContext(),true);
            return  request;
        }else {
            return  request;
        }
    }

    @Override
    public SQLiteDatabase openOrCreateDatabase(String name, int mode, SQLiteDatabase.CursorFactory factory) {
        return super.openOrCreateDatabase(name, mode, factory);
    }
}
