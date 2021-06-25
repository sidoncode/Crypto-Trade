package com.cryptocurrencybestrate.ethereum.UtilPackage;

import android.app.Activity;
import android.app.Application;
import android.content.SharedPreferences;

public class Utility extends Application {

    // firstname
    // lastname
    // mobile number



    public static void setUserFirstName(Activity activity, String name)
    {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("user",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", name).apply();
    }

    public static void setUserLasstName(Activity activity, String name)
    {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("user",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lastName", name).apply();
    }


    public static void setUserMobile(Activity activity, String city)
    {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("user",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("mobile", city).apply();
    }

    public static String getUserFirstName(Activity activity)
    {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("user",MODE_PRIVATE);
        return sharedPreferences.getString("name","");
    }

    public static String getUserLastName(Activity activity)
    {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("user",MODE_PRIVATE);
        return sharedPreferences.getString("lastName","");
    }

    public static String getUserMobile(Activity activity)
    {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("user",MODE_PRIVATE);
        return sharedPreferences.getString("mobile","");
    }



    // social Sign in // // google//

    public static void g_setUserFirstName(Activity activity, String name)
    {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("user",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("name", name).apply();
    }

    public static void g_setUserLasstName(Activity activity, String name)
    {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("user",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lastName", name).apply();
    }


    public static void g_setUserMobile(Activity activity, String city)
    {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("user",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("mobile", city).apply();
    }

    public static String g_getUserFirstName(Activity activity)
    {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("user",MODE_PRIVATE);
        return sharedPreferences.getString("name","");
    }

    public static String g_getUserLastName(Activity activity)
    {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("user",MODE_PRIVATE);
        return sharedPreferences.getString("lastName","");
    }

    public static String g_getUserMobile(Activity activity)
    {
        SharedPreferences sharedPreferences = activity.getSharedPreferences("user",MODE_PRIVATE);
        return sharedPreferences.getString("mobile","");
    }


}
