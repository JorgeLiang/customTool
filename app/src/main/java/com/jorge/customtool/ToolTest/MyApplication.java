package com.jorge.customtool.ToolTest;

import android.app.Application;
import android.os.Environment;

import com.jorge.loglibrary.KLogHandler;

import java.io.File;

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        String path = Environment.getExternalStorageDirectory()
                + File.separator + "GeneralLog";
        String fileName = "generalName_ta";
        KLogHandler.getInstance().initHandler(path,fileName);
    }
}
