package com.orange.ifitdiet.common;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by 廖俊瑶 on 2016/9/9.
 */
public class MyImageView extends ImageView {

    protected Context mContext;

    public MyImageView(Context context) {
        super(context);
        mContext = context;
    }

    public MyImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public MyImageView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (getDrawable() == null) {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            return;
        }
        // 计算出ImageView的宽度
        int viewWidth = View.MeasureSpec.getSize(widthMeasureSpec);
        // 根据图片长宽比例算出ImageView的高度
        int viewHeight = viewWidth * getDrawable().getIntrinsicHeight() / getDrawable().getIntrinsicWidth();
        // 将计算出的宽度和高度设置为图片显示的大小
        setMeasuredDimension(viewWidth, viewHeight);
    }
}
