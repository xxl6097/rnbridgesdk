package com.rn.bridge;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.ReadableMap;
import com.facebook.react.bridge.WritableMap;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class AndroidApi {
    //https://blog.csdn.net/weixin_33788424/article/details/117800333
    private static Map<String, Object> objects = new HashMap<>();
    private static String getClassName() {
        String model = android.os.Build.MODEL;
        model = model.trim();
        model = model.replaceAll(" ", "");
        model = model.toUpperCase();
        String classname = "com.android.api." + model + "Api";
        return classname;
    }

    public static void init(final Activity activity) {
        Log.i("uu_", "AndroidApi==init==========");
//        VolumeManager.getInstance().init(activity, new Handler());
//        ScreenBrightnessApi.getApi().allowModifySettings(activity);
        String classname = getClassName();
        if (classname == null) {
            Log.i("uu_", "没有找到目标类，初始化失败");
            return;
        }
        try {
            Object object = objects.get(classname.toUpperCase());
            if (object == null) {
                Class<?> clasz = Class.forName(classname);
                if (clasz != null) {
                    Constructor<?> con = clasz.getConstructor(Activity.class);
                    object = con.newInstance(activity);
                    objects.put(classname.toUpperCase(), object);
                }else{
                    Log.i("uu_", "0初始化失败"+object);
                }
            }
        } catch (InstantiationException e) {
            Log.i("uu_", "1初始化失败"+(e!=null?e.getMessage():""));
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            Log.i("uu_", "2初始化失败"+(e!=null?e.getMessage():""));
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            Log.i("uu_", "3初始化失败"+(e!=null?e.getMessage():""));
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            Log.i("uu_", "4初始化失败"+(e!=null?e.getMessage():""));
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            Log.i("uu_", "5初始化失败"+(e!=null?e.getMessage():""));
            e.printStackTrace();
        }
    }

    public static WritableMap bundleToWritableMap(Bundle bundle){
        WritableMap writableMap = Arguments.fromBundle(bundle);
        return writableMap;
    }

    public static Bundle ReadableMapToBundle(ReadableMap readableMap){
        Bundle bundle = Arguments.toBundle(readableMap);
        return bundle;
    }

    public static void invoke(final ReadableMap readableMap, final Callback callback,
                              final Context context) {
        if (readableMap == null) {
            if (callback != null) {
                callback.invoke("methodName is null");
            }
            return;
        }
        String classname = getClassName();
        if (classname == null)
            return;
        try {
            Object object = objects.get(classname.toUpperCase());
            if (object == null) {
                Log.i("uu_", "invoke失败，object is null");
                return;
            }
            if (readableMap == null){
                Log.i("uu_", "invoke失败，readableMap is null");
                return;
            }

            Method method = object.getClass().getMethod("invoke", Context.class,Bundle.class);
            if (method == null) {
                Log.i("uu_", "invoke失败，method is null");
                return;
            }
            Object result = method.invoke(object, context, Arguments.toBundle(readableMap));
            if(result != null && callback != null && result instanceof Bundle){
                Bundle bundle = (Bundle) result;
                callback.invoke(Arguments.fromBundle(bundle));
            }
        } catch (NoSuchMethodException e) {
            Log.i("uu_", "1invoke失败"+(e!=null?e.getMessage():""));
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            Log.i("uu_", "2invoke失败"+(e!=null?e.getMessage():""));
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            Log.i("uu_", "3invoke失败"+(e!=null?e.getMessage():""));
            e.printStackTrace();
        }
    }
}
