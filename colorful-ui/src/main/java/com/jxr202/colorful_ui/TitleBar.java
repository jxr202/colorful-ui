package com.jxr202.colorful_ui;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by Jxr35 on 2018/2/27
 */

public class TitleBar extends RelativeLayout {

    private static final int GONE = 8;
    private static final int VISIBLE = 0;

    private Context mContext;
    private ImageView mIvLeft;
    private TextView mTvTitle;
    private ImageView mIvRight;
    private TextView mTvRight;

    // 左边的图标属性
    private Drawable mLeftDrawable;
    private int mLeftImageVisibility;

    // 中间标题属性
    private String mTitleText = "";
    private float mTitleTextSize;
    private int mTitleTextColor;
    private int mTitleTextVisibility;

    // 右边图标属性
    private Drawable mRightDrawable;
    private int mRightImageVisibility;

    // 右边文本的属性
    private String mRightText = "";
    private float mRightTextSize;
    private int mRightTextColor;
    private int mRightTextVisibility;

    private LayoutParams mIvLeftParams;
    private LayoutParams mTvTitleParams;
    private LayoutParams mTvRightParams;
    private LayoutParams mIvRightParams;

    private OnTitleBarClickListener mListener;

    public TitleBar(Context context) {
        this(context, null);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;

        mIvLeft = new ImageView(mContext);
        mTvTitle = new TextView(mContext);
        mIvRight = new ImageView(mContext);
        mTvRight = new TextView(mContext);

        Resources res = getResources();
        TypedArray ta = mContext.obtainStyledAttributes(attrs, R.styleable.titleBar, defStyleAttr, 0);

        mLeftDrawable = ta.getDrawable(R.styleable.titleBar_leftDrawable);
        mLeftImageVisibility = ta.getInteger(R.styleable.titleBar_leftImageVisibility, VISIBLE);

        mTitleText = ta.getString(R.styleable.titleBar_titleText);
        mTitleTextSize = CommonUtils.px2dip(mContext, ta.getDimension(R.styleable.titleBar_titleTextSize, 17));
        mTitleTextColor = ta.getColor(R.styleable.titleBar_titleColor, 0xffffffff);
        mTitleTextVisibility = ta.getInteger(R.styleable.titleBar_titleTextVisibility, VISIBLE);

        mRightText = ta.getString(R.styleable.titleBar_rightText);
        mRightTextSize = CommonUtils.px2dip(mContext, ta.getDimension(R.styleable.titleBar_rightTextSize, 15));
        mRightTextColor = ta.getColor(R.styleable.titleBar_rightTextColor, 0xffffffff);
        mRightTextVisibility = ta.getInteger(R.styleable.titleBar_rightTextVisibility, GONE);

        mRightDrawable = ta.getDrawable(R.styleable.titleBar_rightDrawable);
        mRightImageVisibility = ta.getInteger(R.styleable.titleBar_rightImageVisibility, GONE);

        ta.recycle();
        init();
        initListener();
    }

    private void init() {
        Resources res = getResources();

        mIvLeft.setImageDrawable(mLeftDrawable);
        mIvLeft.setVisibility(mLeftImageVisibility);

        mTvTitle.setText(mTitleText);
        mTvTitle.setTextColor(mTitleTextColor);
        mTvTitle.setTextSize(mTitleTextSize);
        mTvTitle.setVisibility(mTitleTextVisibility);
        mTvTitle.setLines(1);

        mTvRight.setText(mRightText);
        mTvRight.setTextColor(mRightTextColor);
        mTvRight.setTextSize(mRightTextSize);
        mTvRight.setVisibility(mRightTextVisibility);

        mIvRight.setImageDrawable(mRightDrawable);
        mIvRight.setVisibility(mRightImageVisibility);
        // 为组件元素设定响应的布局元素
        mIvLeftParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mTvTitleParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        mTvRightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mIvRightParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);

        mIvLeft.setPadding(10, 0, 40, 0);
        mIvRight.setPadding(40, 0, 20, 0);
        mTvRight.setPadding(40, 0, 20, 0);
        mTvRight.setGravity(Gravity.CENTER_VERTICAL);

        mIvLeftParams.addRule(ALIGN_PARENT_LEFT);
        mTvTitleParams.addRule(CENTER_IN_PARENT);
        mTvRightParams.addRule(ALIGN_PARENT_RIGHT);
        mIvRightParams.addRule(ALIGN_PARENT_RIGHT);
        //mTvTitleParams.addRule(CENTER_IN_PARENT);
        //mTvTitleParams.setMarginStart(2*spacing40px);
        //mTvTitleParams.setMarginEnd(2*spacing40px);

        addView(mIvLeft, mIvLeftParams);
        addView(mTvTitle, mTvTitleParams);
        addView(mTvRight, mTvRightParams);
        addView(mIvRight, mIvRightParams);
    }

    private void initListener() {
        mIvLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onLeftImageClick();
                }
            }
        });
        mIvRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onRightImageClick();
                }
            }
        });
        mTvRight.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mListener != null) {
                    mListener.onRightTextClick();
                }
            }
        });
    }

    public Drawable getLeftDrawable() {
        return mLeftDrawable;
    }

    public void setLeftDrawable(Drawable drawable) {
        mLeftDrawable = drawable;
        mIvLeft.setImageDrawable(drawable);
    }

    public int getLeftImageVisibility() {
        return mLeftImageVisibility;
    }

    public void setLeftImageVisibility(int visibility) {
        mLeftImageVisibility = visibility;
        mIvLeft.setVisibility(visibility);
    }

    public void setLeftImageEnable(boolean enable) {
        mIvLeft.setEnabled(enable);
    }

    public boolean isLeftImageEnable() {
        return mIvLeft.isEnabled();
    }

    public String getTitleText() {
        return mTitleText;
    }

    public void setTitleText(String text) {
        mTitleText = text;
        mTvTitle.setText(text);
    }

    public float getTitleTextSize() {
        return mTitleTextSize;
    }

    public void setTitleTextSize(float size) {
        mTitleTextSize = size;
        mTvTitle.setTextSize(size);
    }

    public int getTitleTextColor() {
        return mTitleTextColor;
    }

    public void setTitleTextColor(int color) {
        mTitleTextColor = color;
        mTvTitle.setTextColor(color);
    }

    public int getTitleTextVisibility() {
        return mTitleTextVisibility;
    }

    public void setTitleTextVisibility(int visibility) {
        mTitleTextVisibility = visibility;
        mTvTitle.setVisibility(visibility);
    }

    public Drawable getRightDrawable() {
        return mRightDrawable;
    }

    public void setRightDrawable(Drawable drawable) {
        mRightDrawable = drawable;
        mIvRight.setImageDrawable(drawable);
    }

    public int getRightImageVisibility() {
        return mRightImageVisibility;
    }

    public void setRightImageVisibility(int visibility) {
        mRightImageVisibility = visibility;
        mIvRight.setVisibility(visibility);
    }

    public void setRightImageEnable(boolean enable) {
        mIvRight.setEnabled(enable);
    }

    public boolean isRightImageEnable() {
        return mIvRight.isEnabled();
    }

    public String getRightText() {
        return mRightText;
    }

    public void setRightText(String text) {
        mRightText = text;
        mTvRight.setText(text);
    }

    public float getRightTextSize() {
        return mRightTextSize;
    }

    public void setRightTextSize(float size) {
        mRightTextSize = size;
        mTvRight.setTextSize(size);
    }

    public int getRightTextColor() {
        return mRightTextColor;
    }

    public void setRightTextColor(int color) {
        mRightTextColor = color;
        mTvRight.setTextColor(color);
    }

    public int getRightTextVisibility() {
        return mRightTextVisibility;
    }

    public void setRightTextVisibility(int visibility) {
        mRightTextVisibility = visibility;
        mTvRight.setVisibility(visibility);
    }

    public void setRightTextEnable(boolean enable) {
        mTvRight.setEnabled(enable);
    }

    public boolean isRightTextEnable() {
        return mTvRight.isEnabled();
    }

    public void setOnTitleBarClickListener(OnTitleBarClickListener listener) {
        mListener = listener;
    }

    public interface OnTitleBarClickListener {
        void onLeftImageClick();

        void onRightImageClick();

        void onRightTextClick();
    }

    public static class SimpleTitleBarClickListener implements OnTitleBarClickListener {
        @Override
        public void onLeftImageClick() {

        }

        @Override
        public void onRightImageClick() {

        }

        @Override
        public void onRightTextClick() {

        }
    }

}