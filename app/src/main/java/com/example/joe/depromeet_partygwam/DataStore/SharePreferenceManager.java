package com.example.joe.depromeet_partygwam.DataStore;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class SharePreferenceManager {
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;

    public static SharedPreferences getInstance(Context context) {
        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("Store", Activity.MODE_PRIVATE);
            editor = sharedPreferences.edit();
            return sharedPreferences;
        }
        return sharedPreferences;
    }

    public static void putString(String key, String data) {
        editor.putString(key, data);
        editor.commit();
    }

    public static String getString(String key) {
        return sharedPreferences.getString(key, "default");
    }

    public static void remove(String key) {
        editor.remove(key);
        editor.commit();
    }
}
