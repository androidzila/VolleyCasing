package com.techexe.volleycasing.Common;

import android.app.ProgressDialog;
import android.content.Context;

/**
 * Created by rgi-5 on 15/11/16.
 */

public class ProgressDialogUtils {

    public ProgressDialog pd;
    String message;
    private Context context;


    public ProgressDialogUtils(Context context){
        this.context = context;
    }
    public void showLoadingProgress() {
        try{
            if (pd != null && pd.isShowing()) {
                pd.dismiss();
            }
            if(context != null){
//                pd = new MyCustomProgressDialog(context,R.style.MyProgressTheme);
                pd = new ProgressDialog(context);
                pd.setMessage("Please wait...");
                pd.setCancelable(false);
                pd.show();
            }
        }catch (Exception e){
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
    public static void dismissProgressDialog(ProgressDialog progressDialog) {
        try{
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
