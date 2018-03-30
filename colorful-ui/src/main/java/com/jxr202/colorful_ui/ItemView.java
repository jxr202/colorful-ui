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
    private int mRightIconId;

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

        mLeftIconId = array.getResourceId(R.styleable.ItemView_leftImageId, R.id.leftIconId);
        mLeftIcon.setId(mLeftIconId);
        mLeftIcon.setImageDrawable(array.getDrawable(R.styleable.ItemView_leftImage));
        mLeftIcon.setVisibility(array.getInteger(R.styleable.ItemView_leftImageVisibility, VISIBLE));

        mLeftTitle.setId(array.getResourceId(R.styleable.ItemView_leftTitleId, R.id.leftTextId));
        mLeftTitle.setText(array.getString(R.styleable.ItemView_leftTitleText));
        mLeftTitle.setTextSize(px2dip(array.getDimension(R.styleable.ItemView_leftTitleTextSize, 15)));
        mLeftTitle.setTextColor(array.getColor(R.styleable.ItemView_leftTitleTextColor, 0xff010101));
        mLeftTitle.setVisibility(array.getInteger(R.styleable.ItemView_leftTitleTextVisibility, VISIBLE));
        mLeftTitle.setEllipsize(TextUtils.TruncateAt.END);
        mLeftTitle.setLines(1);
        mLeftTitle.setPadding(dp2px(15), 0, dp2px(15), 0);

        mRightIconId = array.getResourceId(R.styleable.ItemView_rightImageId, R.id.rightIconId);
        mRightIcon.setId(mRightIconId);
        mRightIcon.setImageDrawable(array.getDrawable(R.styleable.ItemView_rightImage));
        mRightIcon.setVisibility(array.getInteger(R.styleable.ItemView_rightImageVisibility, VISIBLE));

        mRightSummary.setId(array.getResourceId(R.styleable.ItemView_rightTextId, R.id.leftTextId));
        mRightSummary.setText(array.getString(R.styleable.ItemView_rightText));
        mRightSummary.setTextSize(px2dip(array.getDimension(R.styleable.ItemView_rightTextSize, 15)));
        mRightSummary.setTextColor(array.getColor(R.styleable.ItemView_rightTextColor, 0xff999999));
        mRightSummary.setVisibility(array.getInteger(R.styleable.ItemView_rightTextVisibility, VISIBLE));
        mRightSummary.setGravity(Gravity.END | Gravity.CENTER);

        mBottomLine.setBackgroundColor(array.getColor(R.styleable.ItemView_bottomLineColor, 0xffff00ff));

        array.recycle();
        initView();
    }

    private void initView() {
        RelativeLayout.LayoutParams leftIconParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams leftTitleParams = new RelativeLayout.LayoutParams(dp2px(180), ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams rightIconParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        RelativeLayout.LayoutParams rightTextParams = new RelativeLayout.LayoutParams(dp2px(80), ViewGroup.LayoutParams.WRAP_CONTENT);
        LayoutParams bottomLineParams = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 1);

        leftIconParams.addRule(CENTER_VERTICAL);
        leftIconParams.setMarginStart(dp2px(10));

        leftTitleParams.addRule(CENTER_VERTICAL);
        leftTitleParams.addRule(END_OF, mLeftIconId);
        leftTitleParams.setMarginStart(dp2px(5));
        leftTitleParams.setMarginEnd(dp2px(5));

        rightIconParams.addRule(CENTER_VERTICAL);
        rightIconParams.addRule(ALIGN_PARENT_END);
        rightIconParams.setMarginEnd(dp2px(10));

        rightTextParams.addRule(CENTER_VERTICAL);
        rightTextParams.addRule(START_OF, mRightIconId);

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
