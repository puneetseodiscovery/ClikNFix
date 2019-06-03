package com.cliknfix.user.util;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceHandler {

    public static final int MODE = Context.MODE_PRIVATE;
    public static final String PREF_NAME = "APPFRAMEWORK_PREFERENCES";
    public static final String PREF_KEY_USER_EMAIL = "PREF_KEY_USER_EMAIL";
    public static final String PREF_KEY_USER_PASSWORD = "PREF_KEY_USER_PASSWORD";
    public static final String PREF_KEY_LOGIN_TOKEN = "PREF_KEY_LOGIN_TOKEN";
    public static final String PREF_KEY_LOGIN_USER_ID = "PREF_KEY_LOGIN_USER_ID";
    public static final String PREF_KEY_FIREBASE_TOKEN = "PREF_KEY_FIREBASE_TOKEN";
    public void writeBoolean(Context context, String key, boolean value) {
        getEditor(context).putBoolean(key, value).commit();
    }

    public boolean readBoolean(Context context, String key,
                                      boolean defValue) {
        return getPreferences(context).getBoolean(key, defValue);
    }

        public void writeInteger(Context context, String key, int value) {
        getEditor(context).putInt(key, value).commit();
    }

    public int readInteger(Context context, String key, int defValue) {
        return getPreferences(context).getInt(key, defValue);
    }

    public void writeString(Context context, String key, String value) {
        getEditor(context).putString(key, value).commit();
    }

    public String readString(Context context, String key, String defValue) {
        return getPreferences(context).getString(key, defValue);
    }

    public void writeFCM_KEY(Context context, String key, String value) {
        getFCMEditor(context).putString(key, value).commit();
    }

    public String readFCM_KEY(Context context, String key, String defValue) {
        return getFCMPREFERENCE(context).getString(key, defValue);
    }

    public void writeFloat(Context context, String key, float value) {
        getEditor(context).putFloat(key, value).commit();
    }

    public float readFloat(Context context, String key, float defValue) {
        return getPreferences(context).getFloat(key, defValue);
    }

    public void writeLong(Context context, String key, long value) {
        getEditor(context).putLong(key, value).commit();
    }

    public long readLong(Context context, String key, long defValue) {
        return getPreferences(context).getLong(key, defValue);
    }



    public SharedPreferences.Editor getEditor(Context context) {
        return getPreferences(context).edit();
    }

    public SharedPreferences getFCMPREFERENCE(Context context) {
        return context.getSharedPreferences(PREF_NAME, MODE);
    }
    public SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(PREF_NAME, MODE);
    }
    public SharedPreferences.Editor getFCMEditor(Context context) {
        return getFCMPREFERENCE(context).edit();
    }

    public void clearSavedPrefrences(Context context) {
        SharedPreferences settings= context.getSharedPreferences(PREF_NAME, MODE);
        settings.edit().clear().apply();
    }



}