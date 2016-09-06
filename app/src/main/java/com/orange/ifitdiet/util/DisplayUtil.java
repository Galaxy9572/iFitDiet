package com.orange.ifitdiet.util;

import android.content.Context;
import android.media.Image;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by 廖俊瑶 on 2016/9/5.
 */
public class DisplayUtil {

    private WindowManager wm;
    private int widthPixels;
    private int heightPixels;
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

    public int getWidthPixels() {
        return widthPixels;
    }

    public int getHeightPixels() {
        return heightPixels;
    }
}
