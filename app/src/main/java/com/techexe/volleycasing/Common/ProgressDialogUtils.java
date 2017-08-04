package com.techexe.volleycasing.Common;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

/**
 * Created by Jaimin patel on 31/7/17.
 */

public class ProgressDialogUtils {

    public ProgressDialog pd;
    String message;
    private Context context;
    private int theme = 0;

    public ProgressDialogUtils(Context context,int theme){
        this.context = context;
        this.theme = theme;
    }
    public ProgressDialogUtils(Context context){
        this.context = context;
    }
    public void showLoadingProgress(String message) {
        try{
            if (pd != null && pd.isShowing()) {
                Log.i("Progress Dialog","Running");
            }else {
                if(context != null){
                    if (theme == 0){
                        Log.i("Progress Dialog","Create");
                        pd = new ProgressDialog(context);
                    }else {
                        Log.i("Progress Dialog","Create with theme");
                       // pd = new ProgressDialog(context,theme);
                    }
                    pd.setMessage("Please wait...");
                    pd.setCancelable(false);
                    pd.show();
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void dismissProgress() {
        try{
            if (pd != null && pd.isShowing()) {
                pd.dismiss();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
