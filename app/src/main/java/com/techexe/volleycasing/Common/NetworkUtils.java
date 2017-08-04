package com.techexe.volleycasing.Common;

import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AlertDialog;

/**
 * Created by Jaimin patel on 31/7/17.
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
            new AlertDialog.Builder(context)
                    .setMessage("No Internet Connection")
                    .setCancelable(false)
                    .setNeutralButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.dismiss();
                        }
                    }).show();
        }
        return isConnect;
    }


}
