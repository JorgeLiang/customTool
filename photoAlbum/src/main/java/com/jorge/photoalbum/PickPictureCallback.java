package com.jorge.photoalbum;

import java.util.List;

/**
 * Created by Jorge on 7/23/21.
 */

public interface PickPictureCallback {
    void onStart();

    void onSuccess(List<AlbumBean> list);

    void onError();
}
