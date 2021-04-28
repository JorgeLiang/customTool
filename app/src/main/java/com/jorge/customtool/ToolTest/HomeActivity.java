package com.jorge.customtool.ToolTest;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.jorge.customtool.R;

public class HomeActivity extends BaseActivity{

    private static final String TAG = "HomeActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_layout);
    }

    public void click_bar_click(View view) {
        Log.d(TAG,"click_bar_click");
        Toast.makeText(this, "click_bar_click", Toast.LENGTH_SHORT).show();
    }
}
