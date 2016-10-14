package com.orange.ifitdiet.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.media.Image;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * 根据屏幕大小缩放图片的工具类
 * Created by 廖俊瑶 on 2016/9/5.
 */
public class DisplayUtil {

    private WindowManager wm;
    private int widthPixels;//屏幕的横向像素数量
    private int heightPixels;//屏幕的纵向像素数量
    private Display display;
    private DisplayMetrics displayMetrics;

    public DisplayUtil(Context context) {
        wm= (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        display = wm.getDefaultDisplay();
        displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        widthPixels = displayMetrics.widthPixels;
        heightPixels = displayMetrics.heightPixels;
    }

    /**
     * 计算缩放系数的方法
     * @param image 待缩放的图片
     * @return 返回缩放系数
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public int scale(Image image){
        int imgHeight = image.getHeight();
        int imgWidth = image.getWidth();
        int scale_x=widthPixels/ imgWidth;
        int scale_y=heightPixels/imgHeight;
        int scale=1;
        if(scale_x>=1&&scale_y>=1){
            scale=scale_x>scale_y?scale_x:scale_y;
        }
        return scale;
    }

    /**
     * 获取屏幕的横向像素数
     * @return 屏幕的横向像素数
     */
    public int getWidthPixels() {
        return widthPixels;
    }

    /**
     * 获取屏幕的纵向像素数
     * @return 屏幕的纵向像素数
     */
    public int getHeightPixels() {
        return heightPixels;
    }
}
