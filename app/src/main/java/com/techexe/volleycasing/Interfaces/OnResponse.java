package com.techexe.volleycasing.Interfaces;

/**
 * Created by rgi-5 on 23/11/16.
 */

public interface OnResponse {

    void onSuccess(String response, int statusCode);
    void onError(String errorData, int statusCode);
}
