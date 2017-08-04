package com.techexe.volleycasing.permissions;

import android.Manifest;

/**
 * Created by jaimin on 2017/8/3.
 */

public class PermissionRequest {
    public static String[] cameraPermission() {
        return new String[]{Manifest.permission.CAMERA};
    }

    public static String[] cameraAndGalleryPermission() {
        return new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    }

    public static String[] cameraAndGalleryReadPermission() {
        return new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.READ_EXTERNAL_STORAGE};
    }

    public static String[] readContactPermission() {
        return new String[]{Manifest.permission.READ_CONTACTS};
    }

    public static String[] locationPermission() {
        return new String[]{Manifest.permission.ACCESS_FINE_LOCATION};
    }

    public static String[] readWriteStoragePermission(){
        return new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    }
    public static String[] callPermission(){
        return new String[]{Manifest.permission.CALL_PHONE};
    }

    public static String[] audioRecordPermission(){
        return new String[]{Manifest.permission.RECORD_AUDIO};
    }
}
