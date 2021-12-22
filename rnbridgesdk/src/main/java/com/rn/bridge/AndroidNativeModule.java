package com.rn.bridge;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.ReadableMap;

public class AndroidNativeModule extends ReactContextBaseJavaModule {
    private Context context;

    public AndroidNativeModule(@Nullable ReactApplicationContext reactContext) {
        super(reactContext);
        context = reactContext;
        Log.i("uu_","AndroidNativeModule========>"+reactContext.toString());
    }

    @NonNull
    @Override
    public String getName() {
        return "AndroidNativeModule";
    }

    @ReactMethod
    public void invoke(final ReadableMap readableMap,final Callback callback){
        Log.i("uu_","AndroidNativeModule.invoke==>"+readableMap);
        AndroidApi.invoke(readableMap,callback,context);
    }

    @ReactMethod
    public void toast(String methodName){
        Log.i("uu_","AndroidNativeModule.toast==>"+methodName);
        Toast.makeText(context,methodName,Toast.LENGTH_LONG).show();
    }
}
