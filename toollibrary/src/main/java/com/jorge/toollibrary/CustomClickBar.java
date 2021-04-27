package com.jorge.toollibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class CustomClickBar extends RelativeLayout {

    private TextView tvBarName;
    private TextView tvBarHint;
    private ImageView imageRight;
    private RelativeLayout rlLayout;

    public CustomClickBar(Context context) {
        super(context);
        init(context,null,0);
    }

    public CustomClickBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public CustomClickBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        View view = LayoutInflater.from(context).inflate(R.layout.bar_custom_click,this,true);

        rlLayout = view.findViewById(R.id.rl_layout);
        tvBarName = view.findViewById(R.id.tv_bar_name);
        tvBarHint = view.findViewById(R.id.tv_bar_hint);
        imageRight = view.findViewById(R.id.image_right);

        TypedArray array = context.obtainStyledAttributes(attrs,R.styleable.CustomClickBar);
        String barName = array.getString(R.styleable.CustomClickBar_tvBarName);
        String barHint = array.getString(R.styleable.CustomClickBar_tvBarHint);
        int image_arrows = array.getInt(R.styleable.CustomClickBar_imageRight,0);
        array.recycle();

        tvBarName.setText(barName);
        tvBarHint.setText(barHint);
        imageRight.setVisibility(image_arrows == 0 ? GONE : VISIBLE);
    }

    public TextView getTvBarName() {
        return tvBarName;
    }

    public TextView getTvBarHint() {
        return tvBarHint;
    }

    public ImageView getImageRight() {
        return imageRight;
    }

    public void setOnClickItemListener(OnClickListener listener){
        rlLayout.setOnClickListener(listener);
    }

    public RelativeLayout getRlLayout(){
        return rlLayout;
    }
}
