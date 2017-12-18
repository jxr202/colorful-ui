package com.jxr202.colorful_ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Jxr202 on 2017/12/15
 */

public class DateSelectView extends RelativeLayout {

    private static final String TAG = "jxr";

    private ImageView mDateLeft;
    private ImageView mDateRight;
    private TextView mDateTitle;
    private TextView mDateToday;

    private int mDateTitleId;

    private OnDateClickListener mListener;


    public DateSelectView(Context context) {
        super(context);
        Log.i(TAG, "Create -> DateSelectView -> 1");
    }

    public DateSelectView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.i(TAG, "Create -> DateSelectView -> 2");
        init(context, attrs, 0);
    }

    public DateSelectView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        Log.i(TAG, "Create -> DateSelectView -> 3");
        init(context, attrs, defStyleAttr);
    }


    private void init(Context context, AttributeSet attrs, int defStyleAttr) {

        mDateLeft = new ImageView(context);
        mDateRight = new ImageView(context);
        mDateTitle = new TextView(context);
        mDateToday = new TextView(context);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.dateStyle, defStyleAttr, 0);

        mDateLeft.setImageDrawable(array.getDrawable(R.styleable.dateStyle_leftViewSrc));
        mDateRight.setImageDrawable(array.getDrawable(R.styleable.dateStyle_rightViewSrc));

        mDateTitleId = array.getResourceId(R.styleable.dateStyle_titleTextId, R.id.date_title);
        mDateTitle.setId(mDateTitleId);
        mDateTitle.setText(array.getString(R.styleable.dateStyle_titleText));
        mDateTitle.setTextSize(px2dip(context, array.getDimension(R.styleable.dateStyle_titleTextSize, 16)));
        mDateTitle.setTextColor(array.getColor(R.styleable.dateStyle_titleTextColor, 0xff2C9BB6));

        mDateToday.setGravity(Gravity.CENTER);
        mDateToday.setText(array.getString(R.styleable.dateStyle_todayText));
        mDateToday.setTextSize(px2dip(context, array.getDimension(R.styleable.dateStyle_titleTextSize, 16)));
        mDateToday.setTextColor(array.getColor(R.styleable.dateStyle_titleTextColor, 0xffffffff));
        mDateToday.setBackgroundResource(array.getResourceId(R.styleable.dateStyle_todayTextBackground, R.drawable.bg_date_today));

        array.recycle();

        initView();

        initListener();
    }


    private void initView() {

        RelativeLayout.LayoutParams mDateLeftParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams mDateRightParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams mDateTitleParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams mDateTodayParams = new RelativeLayout.LayoutParams(130, 75);

        mDateTitleParams.addRule(CENTER_IN_PARENT);

        mDateLeftParams.addRule(CENTER_VERTICAL);
        mDateLeftParams.addRule(START_OF, mDateTitleId);
        mDateLeftParams.setMarginEnd(16);

        mDateRightParams.addRule(CENTER_VERTICAL);
        mDateRightParams.addRule(END_OF, mDateTitleId);
        mDateRightParams.setMarginStart(16);

        mDateTodayParams.addRule(CENTER_VERTICAL);
        mDateTodayParams.addRule(ALIGN_PARENT_END);
        mDateTodayParams.setMarginEnd(16);

        addView(mDateLeft, mDateLeftParams);
        addView(mDateRight, mDateRightParams);
        addView(mDateTitle, mDateTitleParams);
        addView(mDateToday, mDateTodayParams);
    }

    private void initListener() {

        mDateLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onDateLeftClick();
                }
            }
        });
        mDateRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onDateRightClick();
                }
            }
        });
        mDateTitle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onDateTitleClick();
                }
            }
        });
        mDateToday.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null) {
                    mListener.onDateTodayClick();
                }
            }
        });
    }


    interface OnDateClickListener {

        void onDateLeftClick();

        void onDateRightClick();

        void onDateTitleClick();

        void onDateTodayClick();
    }

    public static class SimpleDateClickListener implements OnDateClickListener {

        @Override
        public void onDateLeftClick() {

        }

        @Override
        public void onDateRightClick() {

        }

        @Override
        public void onDateTitleClick() {

        }

        @Override
        public void onDateTodayClick() {

        }
    }


    public void setOnDateClickListener(OnDateClickListener listener) {

        this.mListener = listener;
    }


    private int px2dip(Context context, float pxValue) {

        final float scale = context.getResources().getDisplayMetrics().density;

        return (int) (pxValue / scale + 0.5f);
    }


}
