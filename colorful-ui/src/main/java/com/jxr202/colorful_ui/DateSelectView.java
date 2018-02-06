package com.jxr202.colorful_ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.Calendar;

import static com.jxr202.colorful_ui.DateUtils.dateToString;
import static com.jxr202.colorful_ui.DateUtils.getTodayDate;

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
    private Context mContext;
    private Calendar mCalendar;
    private boolean mClickable = true;
    private String mFormat = "yyyy-MM-dd";
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

        mContext = context;
        mDateLeft = new ImageView(context);
        mDateRight = new ImageView(context);
        mDateTitle = new TextView(context);
        mDateToday = new TextView(context);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.dateStyle, defStyleAttr, 0);

        mDateLeft.setImageDrawable(array.getDrawable(R.styleable.dateStyle_leftViewSrc));
        mDateRight.setImageDrawable(array.getDrawable(R.styleable.dateStyle_rightViewSrc));

        mDateTitleId = array.getResourceId(R.styleable.dateStyle_titleViewId, R.id.date_title);
        mDateTitle.setId(mDateTitleId);
        mDateTitle.setText(array.getString(R.styleable.dateStyle_titleViewText));
        mDateTitle.setTextSize(px2dip(array.getDimension(R.styleable.dateStyle_titleViewTextSize, 16)));
        mDateTitle.setTextColor(array.getColor(R.styleable.dateStyle_titleViewTextColor, 0xff2C9BB6));

        mDateToday.setGravity(Gravity.CENTER);
        mDateToday.setText(array.getString(R.styleable.dateStyle_todayText));
        mDateToday.setTextSize(px2dip(array.getDimension(R.styleable.dateStyle_todayTextSize, 16)));
        mDateToday.setTextColor(array.getColor(R.styleable.dateStyle_todayTextColor, 0xffffffff));

        int backgroundColor = array.getColor(R.styleable.dateStyle_todayTextBackground, 0xff3e6373);
        GradientDrawable drawable = new GradientDrawable();//创建drawable
        drawable.setColor(backgroundColor);
        drawable.setCornerRadius(dp2px(25));
        drawable.setStroke(dp2px(1), backgroundColor);
        mDateToday.setBackground(drawable);

        array.recycle();

        initCalendar();

        initView();

        initListener();
    }

    private void initCalendar() {
        mCalendar = Calendar.getInstance();
        mCalendar.set(Calendar.HOUR_OF_DAY, 0);
        mCalendar.set(Calendar.SECOND, 0);
        mCalendar.set(Calendar.MINUTE, 0);
        mCalendar.set(Calendar.MILLISECOND, 0);
    }

    /**
     * convert px to its equivalent dp
     * <p>
     * 将px转换为与之相等的dp
     */
    public int px2dp(float pxValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    /**
     * convert dp to its equivalent px
     * <p>
     * 将dp转换为与之相等的px
     */
    public int dp2px(float dipValue) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    private void initView() {

        RelativeLayout.LayoutParams mDateLeftParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams mDateRightParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams mDateTitleParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams mDateTodayParams = new RelativeLayout.LayoutParams(dp2px(65), dp2px(26));

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

        mDateTitle.setText(getTodayDate());

        addView(mDateLeft, mDateLeftParams);
        addView(mDateRight, mDateRightParams);
        addView(mDateTitle, mDateTitleParams);
        addView(mDateToday, mDateTodayParams);
    }

    private void initListener() {

        mDateLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null && mClickable) {
                    mCalendar = DateUtils.getBeforeDay(mCalendar);
                    mDateTitle.setText(dateToString(mCalendar.getTime(), mFormat));
                    mListener.onDateLeftClick();
                }
            }
        });
        mDateRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null && mClickable) {
                    if (dateToString(mCalendar.getTime(), mFormat).equals(dateToString(Calendar.getInstance().getTime(), mFormat))) {
                        return;
                    }
                    mCalendar = DateUtils.getAfterDay(mCalendar);
                    mDateTitle.setText(dateToString(mCalendar.getTime(), mFormat));
                    mListener.onDateRightClick();
                }
            }
        });
        mDateTitle.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null && mClickable) {
                    mListener.onDateTitleClick();
                }
            }
        });
        mDateToday.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener != null && mClickable) {
                    if (dateToString(mCalendar.getTime(), mFormat).equals(dateToString(Calendar.getInstance().getTime(), mFormat))) {
                        return;
                    }
                    initCalendar();
                    mDateTitle.setText(dateToString(mCalendar.getTime(), mFormat));
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

    public void setFormat(String format) {
        mFormat = format;
    }

    public void setClickable(boolean clickable) {
        mClickable = clickable;
    }

    public void setOnDateClickListener(OnDateClickListener listener) {

        this.mListener = listener;
    }


    private int px2dip(float pxValue) {

        final float scale = mContext.getResources().getDisplayMetrics().density;

        return (int) (pxValue / scale + 0.5f);
    }


}
