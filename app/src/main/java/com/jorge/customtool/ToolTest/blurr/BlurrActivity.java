package com.jorge.customtool.ToolTest.blurr;

import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.jorge.customtool.R;
import com.jorge.customtool.ToolTest.BaseActivity;
import com.jorge.squareblur.BlurringView;

/**
 * Created by Jorge on 6/28/21.
 */

public class BlurrActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blurr_layout);
        initView();
    }

    private void initView() {
//        BlurringView blurringView = findViewById(R.id.blurring_view);
//        RelativeLayout rl_layout = findViewById(R.id.rl_layout);
//        TextView tvBakpal = findViewById(R.id.tv_bakpal);
//        blurringView.blurredView(rl_layout);

//        ListView listView = findViewById(R.id.list_view);
//        ListAdapter adapter = new ListItemArrayAdapter(this, 0, ListItemDataProvider.generateSample(this, 5));
//        listView.setAdapter(adapter);

        FrameLayout ll_parent = findViewById(R.id.ll_parent);
        ImageView viewTest = findViewById(R.id.view_test);
        TextView tv_test = findViewById(R.id.tv_test);


        BlurringView blurringView = findViewById(R.id.blurring_view);
//        blurringView.blurredView(viewTest);
        blurringView.blurredView(ll_parent);

    }
}
