package com.techexe.volleycasing.Common;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * Created by rgi-5 on 15/11/16.
 */

public class NetworkUtils {


    public static boolean isInternetConnected(Context context) {
        boolean isConnected = false;
        try {
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null) {
                if (networkInfo.isConnected()) {
                    isConnected = true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return isConnected;
    }

    public static boolean isNetworkAvailable(Context context) {
        boolean isConnect = isInternetConnected(context);
        if (!isConnect) {
            Log.d("Internet","Error");
        }
        return isConnect;
    }


}
