package com.jorge.squareblur;

import android.os.AsyncTask;

import androidx.annotation.CallSuper;
import androidx.annotation.NonNull;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jorge on 6/28/21.
 */

abstract class BaseBlurEngine implements BlurEngine {

    final List<AsyncTask> asyncTasks = new LinkedList<>();

    final BlurConfig blurConfig;

    public BaseBlurEngine(@NonNull BlurConfig blurConfig) {
        this.blurConfig = blurConfig;
    }

    @CallSuper
    @Override
    public void destroy() {
        for (AsyncTask asyncTask : asyncTasks) {
            asyncTask.cancel(true);
        }
        asyncTasks.clear();
    }

    abstract long calculateComputation(int bmpWidth, int bmpHeight, int radius);
    abstract boolean shouldAsync(int bmpWidth, int bmpHeight, int radius);
}
