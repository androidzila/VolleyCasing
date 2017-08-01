package com.techexe.volleycasing.Common;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

/**
 * Created by admin1 on 21/3/16.
 */
public class Preferences {

    SharedPreferences pref_extra;
    private Context _context;
    public Preferences(Context context) {
        this._context = context;
        pref_extra = _context.getSharedPreferences("iExtra",_context.MODE_PRIVATE);
    }
    public String savePrefStringExtra(String key, String value) {
        // TODO Auto-generated method stub
        SharedPreferences.Editor editor = pref_extra.edit();
        // key encode
       try {
           key = Base64.encodeToString(key.getBytes(), Base64.DEFAULT );
           value = Base64.encodeToString(value.getBytes(), Base64.DEFAULT );
           editor.putString(key, value);
           editor.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return key;
    }
    public String loadPrefStringExtra(String key) {
        // TODO Auto-generated method stub
        key = Base64.encodeToString(key.getBytes(), Base64.DEFAULT );
        String strSaved = pref_extra.getString(key, "");
        try {
            byte[] data = Base64.decode(strSaved, Base64.DEFAULT);
            strSaved = new String(data, "UTF-8");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return strSaved;
    }
    public Boolean loadPrefBooleanExtra(String key) {
        // TODO Auto-generated method stub
        boolean isbool = pref_extra.getBoolean(key, false);
        return isbool;
    }

}
