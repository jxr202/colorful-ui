package com.jxr202.colorful_ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import static android.util.TypedValue.COMPLEX_UNIT_PX;
import static android.util.TypedValue.COMPLEX_UNIT_SP;

/**
 * Created by Jxr35 on 2018/3/29
 */

public class ItemView extends RelativeLayout {

    private ImageView mLeftIcon;
    private ImageView mRightIcon;
    private TextView mLeftTitle;
    private TextView mRightSummary;
    private View mBottomLine;

    private Context mContext;
    private int mLeftIconId;
    private int mLeftTitleId;
    private int mRightIconId;
    private int mRightIconVisibility;

    public ItemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initTypedArray(context, attrs, 0);
    }

    public ItemView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initTypedArray(context, attrs, defStyleAttr);
    }

    private void initTypedArray(Context context, AttributeSet attrs, int defStyleAttr) {
        mContext = context;
        mLeftIcon = new ImageView(context);
        mLeftTitle = new TextView(context);
        mRightIcon = new ImageView(context);
        mRightSummary = new TextView(context);
        mBottomLine = new View(context);

        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.ItemView, defStyleAttr, 0);

        mLeftIconId = array.getResourceId(R.styleable.ItemView_mLeftImageId, R.id.leftIconId);
        mLeftIcon.setId(mLeftIconId);
        mLeftIcon.setImageDrawable(array.getDrawable(R.styleable.ItemView_mLeftImage));
        mLeftIcon.setVisibility(array.getInteger(R.styleable.ItemView_mLeftImageVisibility, VISIBLE));

        mLeftTitleId = array.getResourceId(R.styleable.ItemView_mLeftTitleId, R.id.leftTextId);
        mLeftTitle.setId(mLeftTitleId);
        mLeftTitle.setText(array.getString(R.styleable.ItemView_mLeftTitleText));
        mLeftTitle.setTextSize(COMPLEX_UNIT_PX, array.getDimension(R.styleable.ItemView_mLeftTitleTextSize, 15));
        mLeftTitle.setTextColor(array.getColor(R.styleable.ItemView_mLeftTitleTextColor, 0xff010101));
        mLeftTitle.setVisibility(array.getInteger(R.styleable.ItemView_mLeftTitleTextVisibility, VISIBLE));
        mLeftTitle.setEllipsize(TextUtils.TruncateAt.END);
        mLeftTitle.setLines(1);
        mLeftTitle.setPadding(dp2px(5), 0, dp2px(5), 0);

        mRightIconId = array.getResourceId(R.styleable.ItemView_mRightImageId, R.id.rightIconId);
        mRightIconVisibility = array.getInteger(R.styleable.ItemView_mRightImageVisibility, VISIBLE);
        mRightIcon.setId(mRightIconId);
        mRightIcon.setImageDrawable(array.getDrawable(R.styleable.ItemView_mRightImage));
        mRightIcon.setVisibility(mRightIconVisibility);

        mRightSummary.setId(array.getResourceId(R.styleable.ItemView_mRightTextId, R.id.rightTextId));
        mRightSummary.setText(array.getString(R.styleable.ItemView_mRightText));
        mRightSummary.setTextSize(COMPLEX_UNIT_PX, array.getDimension(R.styleable.ItemView_mRightTextSize, 15));
        mRightSummary.setTextColor(array.getColor(R.styleable.ItemView_mRightTextColor, 0xff999999));
        mRightSummary.setVisibility(array.getInteger(R.styleable.ItemView_mRightTextVisibility, VISIBLE));
        mRightSummary.setGravity(Gravity.END | Gravity.CENTER);

        mBottomLine.setBackgroundColor(array.getColor(R.styleable.ItemView_mBottomLineColor, 0xffff00ff));

        array.recycle();
        initView();
    }

    private void initView() {
        RelativeLayout.LayoutParams leftIconParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams leftTitleParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams rightIconParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams rightTextParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LayoutParams bottomLineParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);

        leftIconParams.addRule(CENTER_VERTICAL);
        leftIconParams.setMarginStart(dp2px(15));

        leftTitleParams.addRule(CENTER_VERTICAL);
        leftTitleParams.addRule(END_OF, mLeftIconId);
        leftTitleParams.setMarginStart(dp2px(5));
        leftTitleParams.setMarginEnd(dp2px(5));

        rightIconParams.addRule(CENTER_VERTICAL);
        rightIconParams.addRule(ALIGN_PARENT_END);
        rightIconParams.setMarginEnd(dp2px(10));

        rightTextParams.addRule(CENTER_VERTICAL);
        rightTextParams.addRule(END_OF, mLeftTitleId);
        if (mRightIconVisibility == VISIBLE) {
            rightTextParams.addRule(START_OF, mRightIconId);
        } else {
            rightTextParams.addRule(ALIGN_PARENT_END);
            rightTextParams.setMarginEnd(dp2px(20));
        }

        bottomLineParams.addRule(ALIGN_PARENT_BOTTOM);

        addView(mLeftIcon, leftIconParams);
        addView(mLeftTitle, leftTitleParams);
        addView(mRightSummary, rightTextParams);
        addView(mRightIcon, rightIconParams);
        addView(mBottomLine, bottomLineParams);
    }

    public void setLeftIcon(Drawable drawable) {
        mLeftIcon.setImageDrawable(drawable);
    }

    public void setTitleText(String text) {
        mLeftTitle.setText(text);
    }

    public void setRightText(String text) {
        mRightSummary.setText(text);
    }

    private int px2dip(float pxValue) {
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

}
