package com.jxr202.colorful_ui_example;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.jxr202.colorful_ui.DateSelectView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "jxr";

    private DateSelectView mDateSelectView;

    private DateSelectView.SimpleDateClickListener mListener = new DateSelectView.SimpleDateClickListener() {

        @Override
        public void onDateLeftClick() {
            Log.i(TAG, "onDateLeftClick...");
        }

        @Override
        public void onDateRightClick() {
            Log.i(TAG, "onDateRightClick...");
        }

        @Override
        public void onDateTitleClick() {
            Log.i(TAG, "onDateTitleClick...");
        }

        @Override
        public void onDateTodayClick() {
            Log.i(TAG, "onDateTodayClick...");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        mDateSelectView = (DateSelectView) findViewById(R.id.dateSelectView);

        mDateSelectView.setOnDateClickListener(mListener);

    }




}
