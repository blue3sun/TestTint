package com.android.monkey.testtint;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;

/**
 * Description:
 * Author: Monkey
 * Time: 2017/1/19 17:31
 */

public class ThirdActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        Log.e("TEST", displayMetrics.widthPixels+"+"+displayMetrics.heightPixels+"");
    }
}
