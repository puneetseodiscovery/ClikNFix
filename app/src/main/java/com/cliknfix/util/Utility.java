package com.cliknfix.util;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.MotionEvent;
import android.view.View;


import com.agrawalsuneet.dotsloader.loaders.CircularDotsLoader;
import com.cliknfix.R;
import com.cliknfix.base.MyApp;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class Utility {


    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null;
    }

    public static boolean validEmail(String email) {
        return (!TextUtils.isEmpty(email) && Patterns.EMAIL_ADDRESS.matcher(email).matches());
    }

    // need to remove below method

    public boolean onTouch(View v, MotionEvent event) {
        final int DRAWABLE_LEFT = 0;
        final int DRAWABLE_TOP = 1;
        final int DRAWABLE_RIGHT = 2;
        final int DRAWABLE_BOTTOM = 3;

        if (event.getAction() == MotionEvent.ACTION_UP) {
//            if (event.getRawX() >= (locationText.getLeft() - locationText.getCompoundDrawables()[DRAWABLE_LEFT].getBounds().width())) {
//                // your action here
//
//                return true;
//            }
        }
        return false;
    }

    // need to remove above method

    public static Typeface typeFaceForSemiBoldText(Context context)
    {

        return Typeface.createFromAsset(context.getAssets(),
                "fonts/bahnschrift_semibold.ttf");
    }

    public static Typeface typeFaceForBoldText(Context context)
    {

        return Typeface.createFromAsset(context.getAssets(),
                "fonts/bahnschrift_bold.ttf");
    }

    public static Typeface typeFaceForText(Context context)
    {

        return Typeface.createFromAsset(context.getAssets(),
                "fonts/bahnschrift_regular.ttf");
    }

    public static String message(Response response) {
        try {
            ResponseBody responseBody = response.errorBody();
            return responseBody == null ? response.message() : responseBody.string();
        } catch (IOException e) {
            return response.message();
        }
    }

    public static String getToken()
    {
        return new PreferenceHandler().readString(MyApp.getInstance().getApplicationContext(), PreferenceHandler.PREF_KEY_LOGIN_TOKEN, "");
    }

    public static ProgressDialog showLoader(Context context) {
        ProgressDialog progressDialog = new ProgressDialog(context);
//        progressDialog.setMessage("Loading..");
        try {
            progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        }
        catch (Exception e)
        {
            e.fillInStackTrace();
        }
        progressDialog.setCancelable(false);
        progressDialog.show();
        progressDialog.setContentView(R.layout.layout_progress);
        return progressDialog;
    }

    public static CircularDotsLoader circularLoader(Context context){
        CircularDotsLoader loader = new CircularDotsLoader(context);
        loader.setDefaultColor(ContextCompat.getColor(context,R.color.blackColor));
        loader.setSelectedColor(ContextCompat.getColor(context,R.color.editTextBackColor));
        loader.setBigCircleRadius(60);
        loader.setRadius(14);
        loader.setAnimDur(300);
        loader.setShowRunningShadow(true);
        loader.setFirstShadowColor(ContextCompat.getColor(context, R.color.editTextBackColor));
        loader.setSecondShadowColor(ContextCompat.getColor(context, R.color.blackColor));
        return loader;
    }

    public static boolean isValidPassword(final String password) {
        String pattern= "^[a-zA-Z0-9]*$";
        return password.matches(pattern);

    }

}
