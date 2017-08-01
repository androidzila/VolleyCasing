package com.techexe.volleycasing;

import android.content.res.Resources;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.support.v7.app.AppCompatDelegate;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.HttpStack;
import com.android.volley.toolbox.HurlStack;
import com.android.volley.toolbox.Volley;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import static android.content.ContentValues.TAG;

public class MyAplication extends MultiDexApplication {

    public static Resources resources;
    private static MyAplication ggAplication;
    private HttpStack httpStack = null;
    private RequestQueue mRequestQueue;

    public static synchronized  MyAplication getGGApp() {
        return ggAplication;
    }

    private static SSLSocketFactory createSslSocketFactory() {
        TrustManager[] byPassTrustManagers = new TrustManager[]{new X509TrustManager() {
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }

            public void checkClientTrusted(X509Certificate[] chain, String authType) {
            }

            public void checkServerTrusted(X509Certificate[] chain, String authType) {
            }
        }};

        SSLContext sslContext = null;
        SSLSocketFactory sslSocketFactory = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, byPassTrustManagers, new SecureRandom());
            sslSocketFactory = sslContext.getSocketFactory();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
          //  Log.e(TAG, StringUtils.EMPTY, e);
        } catch (KeyManagementException e) {
            //Log.e(TAG, StringUtils.EMPTY, e);
        }

        return sslSocketFactory;
    }
   @Override
    public void onCreate() {
        super.onCreate();
        MultiDex.install(this);
       ggAplication= this;
        httpStack = new HurlStack(null, createSslSocketFactory());
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        resources = getResources();
    }
    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext(),httpStack);
        }
        return mRequestQueue;
    }
    public RequestQueue getRequestQueueCount() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext(),httpStack);
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
//
//    private void useCompatVectorIfNeeded() {
//        int sdkInt = Build.VERSION.SDK_INT;
//        if (sdkInt <= 21 || sdkInt == 22) { //vector drawables scale correctly in API level 23
//            try {
//                AppCompatDrawableManager drawableManager = AppCompatDrawableManager.get();
//                Class<?> inflateDelegateClass = Class.forName("android.support.v7.widget.AppCompatDrawableManager$InflateDelegate");
//                Class<?> vdcInflateDelegateClass = Class.forName("android.support.v7.widget.AppCompatDrawableManager$VdcInflateDelegate");
//
//                Constructor<?> constructor = vdcInflateDelegateClass.getDeclaredConstructor();
//                constructor.setAccessible(true);
//                Object vdcInflateDelegate = constructor.newInstance();
//
//                Class<?> args[] = {String.class, inflateDelegateClass};
//                Method addDelegate = AppCompatDrawableManager.class.getDeclaredMethod("addDelegate", args);
//                addDelegate.setAccessible(true);
//                addDelegate.invoke(drawableManager, "vector", vdcInflateDelegate);
//            } catch (ClassNotFoundException e) {
//                e.printStackTrace();
//            } catch (NoSuchMethodException e) {
//                e.printStackTrace();
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            } catch (InstantiationException e) {
//                e.printStackTrace();
//            } catch (InvocationTargetException e) {
//                e.printStackTrace();
//            }
//        }
//    }



/*
    void Generate_certificate(){
        CertificateFactory cf = null;
        try {
            cf = CertificateFactory.getInstance("X.509");
        } catch (CertificateException e) {
            e.printStackTrace();
        }

        // Generate the certificate using the certificate file under res/raw/cert.cer
        InputStream caInput = new BufferedInputStream(getResources().openRawResource(R.raw.class));
        Certificate ca = null;
        try {
            ca = cf.generateCertificate(caInput);
            caInput.close();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        // Create a KeyStore containing our trusted CAs
        String keyStoreType = KeyStore.getDefaultType();
        KeyStore trusted = null;
        try {
            trusted = KeyStore.getInstance(keyStoreType);
            try {
                trusted.load(null, null);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            } catch (CertificateException e) {
                e.printStackTrace();
            }
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

        try {
            trusted.setCertificateEntry("ca", ca);
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

        // Create a TrustManager that trusts the CAs in our KeyStore
        String tmfAlgorithm = TrustManagerFactory.getDefaultAlgorithm();
        TrustManagerFactory tmf = null;
        try {
            tmf = TrustManagerFactory.getInstance(tmfAlgorithm);
            tmf.init(trusted);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

        // Create an SSLContext that uses our TrustManager
        SSLContext context = null;
        try {
            context = SSLContext.getInstance("TLS");
            context.init(null, tmf.getTrustManagers(), null);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyManagementException e) {
            e.printStackTrace();
        }


        SSLSocketFactory sf = context.getSocketFactory();
     //   mRequestQueue = Volley.newRequestQueue(getApplicationContext(), new HurlStack(null, sf));
    }
*/

}
