package com.jorge.customtool.ToolTest;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.annotation.Nullable;
import com.jorge.customtool.R;
import com.jorge.customtool.ToolTest.blurr.BlurrActivity;
import com.jorge.customtool.ToolTest.photo_album.PhotoAlbumActivity;
import com.jorge.customtool.ToolTest.recyclerViewHeaderFooder.MultipleRecyclerActiviy;
import com.jorge.customtool.ToolTest.utils.PermissionUtils;
import com.jorge.loglibrary.KLogHandler;

import java.lang.ref.PhantomReference;

/**
 * Created by Jorge on 4/29/21.
 */

public class HomeActivity extends BaseActivity{

    private static final String TAG = "HomeActivity";
    private int count = 0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_layout);
    }

    public void click_bar_click(View view) {
        Log.d(TAG,"click_bar_click");
        Toast.makeText(this, "click_bar_click", Toast.LENGTH_SHORT).show();

        int tCount = count++;

        if (PermissionUtils.checkReadWritePermission(HomeActivity.this)){

            Toast.makeText(this, tCount+",,", Toast.LENGTH_SHORT).show();
            KLogHandler.getInstance().setLog("click:" + tCount);
        }
    }

    public void recycleView_click(View view) {
        startActivity(new Intent(this, MultipleRecyclerActiviy.class));
    }

    /**背景模糊**/
    public void btn_blurr(View view) {
        startActivity(new Intent(this, BlurrActivity.class));
    }

    public void photo_album(View view) {
        startActivity(new Intent(this, PhotoAlbumActivity.class));
    }
}
