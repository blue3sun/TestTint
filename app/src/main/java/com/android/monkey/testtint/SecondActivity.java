package com.android.monkey.testtint;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.StateListDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;

/**
 * Description:
 * Author: Monkey
 * Time: 2017/1/13 14:24
 */

public class SecondActivity extends AppCompatActivity {
    private LinearLayout mLlContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        initView();
    }

    private void initView() {
        mLlContainer = (LinearLayout)findViewById(R.id.ll_container);
        addViewOne();
        addViewTwo();
    }
    public ImageButton CreateImageButton(){
        ImageButton imageButton = new ImageButton(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(150, 150);
        imageButton.setLayoutParams(lp);
        return imageButton;
    }
    public ImageView CreateImageView(){
        ImageView imageView = new ImageView(this);
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(150, 150);
        imageView.setLayoutParams(lp);
        return imageView;
    }

    //代码设置tint同时实现selector的功能。（实现了tint,但是没有实现selector。BitmapDrawable实现不了）
    private void addViewOne() {
        ImageView imageView = CreateImageView();
        Drawable drawable = ContextCompat.getDrawable(this,R.mipmap.ic_launcher);
        //Drawable.ConstantState state = drawable.getConstantState();
        //Drawable wrapDrawable = DrawableCompat.wrap(
        // state==null?drawable:state.newDrawable()).mutate();
        Drawable wrapDrawable = DrawableCompat.wrap(drawable).mutate();
        wrapDrawable.setBounds(0,0,wrapDrawable.getIntrinsicWidth(),wrapDrawable.getIntrinsicHeight());
        DrawableCompat.setTint(wrapDrawable,ContextCompat.getColor(this,R.color.color_tint));
        imageView.setImageDrawable(wrapDrawable);
        imageView.setClickable(true);//这一步很重要，否则点击无效果
        mLlContainer.addView(imageView);
    }

    //使用StateListDrawble实现selector
    private void addViewTwo() {
        ImageView imageView = CreateImageView();
        Drawable drawable = ContextCompat.getDrawable(this,R.mipmap.ic_launcher);
        int[][] states = new int[2][];
        states[0] = new int[]{android.R.attr.state_pressed};
        states[1] = new int[]{};

        StateListDrawable stateListDrawable = new StateListDrawable();
        stateListDrawable.addState(states[0],drawable);
        stateListDrawable.addState(states[1],drawable);

        int[] color = new int[]{
                ContextCompat.getColor(this,R.color.color_ff0000),
                ContextCompat.getColor(this,R.color.color_ff6c38)};

        ColorStateList colorStateList = new ColorStateList(states,color);

        Drawable.ConstantState state = stateListDrawable.getConstantState();
        Drawable wrapDrawable = DrawableCompat.wrap(
                state == null ? stateListDrawable : state.newDrawable()).mutate();
        wrapDrawable.setTintList(colorStateList);
        imageView.setImageDrawable(wrapDrawable);
        imageView.setClickable(true);//这一步很重要，否则点击无效果
        mLlContainer.addView(imageView);
    }

}
