package com.android.monkey.testtint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;

import java.io.InputStream;

/**
 * Description:
 * Author: Bravowhale
 * Time: 2017/1/19 17:32
 */

public class MyView extends View {
    private Context mContext;
    private Bitmap mBitmap;
    private Paint mPaint1;
    private Paint mPaint2;

    public MyView(Context context) {
        super(context,null);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        initBitmap();
        initPaint();
    }

    private void initBitmap() {
        mBitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.img);
        InputStream inputStream = getResources().openRawResource(R.raw.img);
        mBitmap = BitmapFactory.decodeStream(inputStream);
    }

    private void initPaint() {
        mPaint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        ColorMatrixColorFilter colorMatrixColorFilter = new ColorMatrixColorFilter(new float[]{//4行5列 [0.0F , 2.0F]
                0.33F, 0.59F, 0.11F, 0, 0,
                0.33F, 0.59F, 0.11F, 0, 0,
                0.33F, 0.59F, 0.11F, 0, 0,
                0, 0, 0, 1, 0,
        });
        mPaint2.setColorFilter(colorMatrixColorFilter);
    }

    public int getScreenWidth(){
        DisplayMetrics displayMatrics = getResources().getDisplayMetrics();
        return displayMatrics.widthPixels;
    }
    public int getScreenHeight(){
        DisplayMetrics displayMatrics = getResources().getDisplayMetrics();
        return displayMatrics.heightPixels;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(mBitmap,0,0, mPaint1);
        canvas.drawBitmap(mBitmap,0,300, mPaint2);
//        int left = getScreenWidth()/2-getWidth()/2;
//        int top = getScreenHeight()/2-getHeight()/2;
//        canvas.drawBitmap(mBitmap,left,top,mPaint2);
    }
}
