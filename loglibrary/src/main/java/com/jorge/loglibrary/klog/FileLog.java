package com.jorge.loglibrary.klog;

import android.annotation.SuppressLint;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class FileLog {

    public static void printFile(String tag, File targetDirectory, String fileName,
                                 String headString, String msg,boolean continueToWrite) {

        fileName = (fileName == null) ? getFileName() : fileName;

        if (continueToWrite){

            if (saveContinueToWrite(targetDirectory, fileName, msg)) {
                Log.d(tag, headString + " save log success ! location is >>>" + targetDirectory.getAbsolutePath() + "/" + fileName);
            } else {
                Log.e(tag, headString + "save log fails !");
            }

        }else {

            if (save(targetDirectory, fileName, msg)) {
                Log.d(tag, headString + " save log success ! location is >>>" + targetDirectory.getAbsolutePath() + "/" + fileName);
            } else {
                Log.e(tag, headString + "save log fails !");
            }

        }

    }

    private static boolean saveContinueToWrite(File dic, String fileName, String msg) {

        //判断SD卡是否存在
        if (!Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) { return false; }

//        SharedPreferencesUtils.getCrashPath(MyApp.myApp);
        @SuppressLint("SimpleDateFormat")
        String time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());


        File file = new File(dic, fileName);

        try {
            //往文件中写入数据
//            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(file)));
            PrintWriter pw = new PrintWriter(
                    new BufferedWriter(
                            new FileWriter(file.getAbsolutePath(),true)
                    )
            );//在后面追加信息

            pw.println(time);
            pw.println(msg);
            pw.println("--------------------------");
            pw.close();
        } catch (IOException e1)
        {
            e1.printStackTrace();
        }

        return true;
    }

    private static boolean save(File dic, String fileName, String msg) {

        File file = new File(dic, fileName);

        try {
            OutputStream outputStream = new FileOutputStream(file);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
            outputStreamWriter.write(msg);
            outputStreamWriter.flush();
            outputStream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    private static String getFileName() {
        Random random = new Random();
        return "KLog_" + Long.toString(System.currentTimeMillis() + random.nextInt(10000)).substring(4) + ".txt";
    }



}

