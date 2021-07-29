package com.jorge.customtool.ToolTest.photo_album;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;

import com.jorge.customtool.R;
import com.jorge.customtool.ToolTest.BaseActivity;
import com.jorge.customtool.ToolTest.utils.PermissionUtils;
import com.jorge.photoalbum.PickPictureCallback;
import com.jorge.photoalbum.PickPictureHelper;
import com.jorge.photoalbum.PhotoAlbumBean;

import java.util.List;

/**
 * Created by Jorge on 7/23/21.
 */

public class PhotoAlbumActivity extends BaseActivity {

    private String TAG = "PhotoAlbumActivity : ";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.blank_layout);

        PermissionUtils.checkReadWritePermission(this);

        PickPictureHelper.readPicture(PhotoAlbumActivity.this, new PickPictureCallback() {
            @Override
            public void onStart() {
                Log.d(TAG,"readPicture onStart");
            }

            @Override
            public void onSuccess(List<PhotoAlbumBean> list) {
                Log.d(TAG,"readPicture onSuccess : " + list.size());
            }

            @Override
            public void onError() {
                Log.d(TAG,"readPicture onError");
            }
        });
    }

}
