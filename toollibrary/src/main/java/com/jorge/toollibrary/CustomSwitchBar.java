package com.jorge.toollibrary;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;

/**
 * Created by Jorge on 4/29/21.
 */

public class CustomSwitchBar extends RelativeLayout {
    private ProgressBar pbProgressBar;
    private SwitchCompat swSwitch;
    private TextView tvSwitchName;

    public CustomSwitchBar(Context context) {
        super(context);
        init(context,null,0);
    }

    public CustomSwitchBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs,0);
    }

    public CustomSwitchBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs,defStyleAttr);
    }

    private void init(Context context, AttributeSet attributeSet, int defStyleAttr) {
        View view = LayoutInflater.from(context).inflate(R.layout.bar_custom_switch,this,true);

        tvSwitchName = view.findViewById(R.id.tv_switch_name);
        swSwitch = view.findViewById(R.id.sw_switch);
        pbProgressBar = view.findViewById(R.id.pb_progress_bar);

        TypedArray array = context.obtainStyledAttributes(attributeSet,R.styleable.CustomSwitchBar);
        String tvSwitchNameResId = array.getString(R.styleable.CustomSwitchBar_tvSwitchNameStr);
        int swSwitchResId = array.getInteger(R.styleable.CustomSwitchBar_swSwitch,0);
        int pbProgressBarResId = array.getInteger(R.styleable.CustomSwitchBar_pbProgressBar,1);
        array.recycle();

//        LogUtils.d("CustomSwitchBar : " + tvSwitchNameResId
//                + "swSwitchResId : " + swSwitchResId
//                + ",pbProgressBarResId : " + pbProgressBarResId);
        tvSwitchName.setText(tvSwitchNameResId);
        swSwitch.setChecked(swSwitchResId == 1);
        pbProgressBar.setVisibility(pbProgressBarResId == 1 ? VISIBLE : GONE);
    }

    public void setProgressBarVisibility(boolean isVisible){
        pbProgressBar.setVisibility(isVisible ? VISIBLE : GONE);
    }

    public void setSwitchCheck(boolean isCheck){
        swSwitch.setChecked(isCheck);
    }

    public SwitchCompat getSwitch(){
        return swSwitch;
    }

    public ProgressBar getProgressBar(){
        return pbProgressBar;
    }
}
