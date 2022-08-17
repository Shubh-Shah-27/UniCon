package com.example.unicon;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class EditSharedPreference {
    Activity activity;
    SharedPreferences sp;
    String key;

    EditSharedPreference(Activity activity, String keyDollarToINR){
        this.activity = activity;
        sp = activity.getPreferences(Context.MODE_PRIVATE);
        key = keyDollarToINR;
    }

    void updateDollarValue(float newValue)
    {
        Log.e("Storage","Value updated");
        SharedPreferences.Editor editor = sp.edit();
        editor.putFloat(key,newValue);
        editor.apply();
        editor.clear();
    }

    float getStoredDollarValue()
    {
        Log.e("Storage","Value accessed");
        float defaultDollarValue = 78.0f;
        return sp.getFloat(key,defaultDollarValue);
    }
}
