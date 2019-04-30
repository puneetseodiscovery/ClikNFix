package com.cliknfix.user.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;


import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;


public class BaseClass extends AppCompatActivity{

  //  public static TinyDB tinydb;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        tinydb = new TinyDB(MyApp.getInstance().getApplicationContext());
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

}
