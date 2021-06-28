package com.jorge.squareblur;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;

/**
 * Created by Jorge on 6/28/21.
 */

class NoBlur implements BlurEngine {

    @Override
    public Bitmap execute(@NonNull Bitmap inBitmap, boolean canReuseInBitmap) {
        return null;
    }

    @Override
    public Bitmap execute(@NonNull Bitmap inBitmap, @NonNull Bitmap outBitmap) {
        return null;
    }

    @Override
    public void execute(@NonNull Bitmap inBitmap, boolean canReuseInBitmap, @NonNull Callback callback) {
        callback.onFinished(null);
    }

    @Override
    public void execute(@NonNull Bitmap inBitmap, @NonNull Bitmap outBitmap, @NonNull Callback callback) {
        callback.onFinished(null);
    }

    @Override
    public void destroy() {

    }

    @Override
    public String methodDescription() {
        return "No Blur Effect";
    }
}
