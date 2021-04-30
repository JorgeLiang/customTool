package com.jorge.loglibrary;

import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Jorge on 4/29/21.
 *
 * KLogHandler.getInstance().setLog(KLogHandler.setListLog(checkInLog));
 * KLogHandler.getInstance().setLog("testMessage");
 */

public class KLogHandler {

    private static final String TAG = "KLogHandler";
    private static final boolean SAVE_LOG = true;
    private static final int RANGE_MB = 10;
    private String kLogPath = Environment.getExternalStorageDirectory()
            + File.separator + "GeneralLog";
    private String KLogFileName;
    private int rangeSize = 10;     //MB

    public static KLogHandler getInstance(){
        return KLogHandlerInstance.instance;
    }

    private static class KLogHandlerInstance{
        private static final KLogHandler instance = new KLogHandler();
    }

    public void initHandler(String kLogPath,String KLogFileName) {
        this.kLogPath = kLogPath;
        this.KLogFileName = KLogFileName;
    }

    public void initHandler(String kLogPath,String KLogFileName,int rangeSize) {
        this.kLogPath = kLogPath;
        this.KLogFileName = KLogFileName;
        this.rangeSize = rangeSize;
    }

    public void setRange(int rangeSize){
        this.rangeSize = rangeSize;
    }

    /**Log文件名**/
    private String getLogFileName(int num) {
        if (TextUtils.isEmpty(KLogFileName)){
            Log.d(TAG,"LogFileName == null");
            this.KLogFileName = "KLog_" + num + ".txt";
            return KLogFileName;
        }
        return KLogFileName + "_" + num + ".txt";
    }

    public void setLogFileName(String KLogFileName){
        this.KLogFileName = KLogFileName;
    }

    /**Log记录路径**/
    public String getLogPath() {
        String path;

        if (TextUtils.isEmpty(kLogPath)){
            Log.d(TAG,"LogPath == null");
            path = Environment.getExternalStorageDirectory()
                    + File.separator + "GeneralLog";
        }else {
            path = kLogPath;
        }

        File file = new File(path);
        if (!file.exists()) {
            boolean is = file.mkdirs();
        }
        return path;
    }

    public void setLogPath(String kLogPath){
        this.kLogPath = kLogPath;
    }

    public void setLog(Object obj) {
        if (SAVE_LOG){

            getLogPath();

            List<File> files = listCatalogueFileSortByModifyTime(kLogPath);

            deleteFiles(files);

            Log.d(TAG," file Name : |----------------------------------------------------------------|");

            for(File file : files){

                boolean isCreate = needCreateFileBeyondMB(file.length(),rangeSize);//rangeSize RANGE_MB

                Log.d(TAG," file Name : " + file.getName()
                        + " , 大小：" + FileUtils.FormatFileSize(file.length())
                        + " , 超过：" + isCreate);

            }
            Log.d(TAG," file Name : |----------------------------------------------------------------|");

            String KLogFileName;

            if (files.size() > 0){

                File lastFile = files.get(files.size() - 1);

                //获取最近一个文件大小
                boolean isCreate = needCreateFileBeyondMB(lastFile.length(), rangeSize);//rangeSize RANGE_MB
                Log.d(TAG," setLog file Name : " + lastFile.getName()
                        + " , 大小：" + FileUtils.FormatFileSize(lastFile.length())
                        + " , 超过：" + isCreate);

                if (isCreate){

                    //超过指定大小 创建新txt文件
                    String numStr = StringUtils.cutMiddleString("_",".",lastFile.getName());
                    int num = Integer.parseInt(numStr);
                    KLog.file(TAG,new File(kLogPath),getLogFileName(num+1),obj,true);
                    KLogFileName = getLogFileName(num+1);
                    Log.d(TAG," setLog 超过指定大小 创建新txt文件 KLogFileName : " + KLogFileName);

                }else {

                    //继续在原有文件下写入
                    KLog.file(TAG,new File(kLogPath),lastFile.getName(),obj,true);
                    KLogFileName = lastFile.getName();
                    Log.d(TAG," setLog 继续在原有文件下写入 KLogFileName : " + KLogFileName);

                }

            }else {

                //创建文件
                KLog.file(TAG,new File(kLogPath),getLogFileName(1),obj,true);
                KLogFileName = getLogFileName(1);
                Log.d(TAG," setLog 没有log文件 创建txt文件 KLogFileName : " + KLogFileName);

            }

        }

    }

    private void deleteFiles(List<File> files) {
        if (files.size() > 5){
            int deleteNum = files.size() - 4;
            for (int i = 0; i < deleteNum; i++) {
                File file = files.get(i);
                if (file.isFile()){
                    boolean isDelete = files.get(i).delete();
                    Log.d(TAG," isDelete : " + isDelete + ", file Name" + file.getName());
                }
            }
        }
    }

    public static String setListLog(List<String> strings) {
        String logStr = "";
        for(String string : strings){

            logStr = logStr.isEmpty() ? string : logStr + "\n" + string ;

        }
        return logStr;
    }

    public static String setListLog(String[] strings) {
        String logStr = "";
        for(String string : strings){

            logStr = logStr.isEmpty() ? string : logStr + "\n" + string ;

        }
        return logStr;
    }

    public static String getMessage(IOException e) {
        return "onFailed" + e.getMessage();
    }

    public static String getMessage(int err, String msg) {
        return "onErrCode err:" + err + ", msg:" + msg;
    }

    /**
     * 获取sdcard路径
     *
     * @return sd未挂载则返回 "/"
     */
    public static String getSDCardRootPath() {
        if (isSDCardMounted()) {
            return Environment.getExternalStorageDirectory().toString();
        } else {
            return "/";
        }
    }

    /**
     * 判断sdcard是否挂载
     *
     * @return true为已挂载
     */
    public static boolean isSDCardMounted() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 只获取目录下的文件，忽视文件夹(按时间排序)
     * @param path
     * @return
     */
    public static List<File> listCatalogueFileSortByModifyTime(String path) {
        List<File> list = getCatalogueFiles(path, new ArrayList<>());

        if (list.size() > 0) {
            Collections.sort(list, (o1, o2) -> {
                if (o1.lastModified() < o2.lastModified()){
                    return -1;
                }else if (o1.lastModified() == o2.lastModified()){
                    return 0;
                }else{
                    return 1;
                }
            });
        }
        return list;
    }

    private static List<File> getCatalogueFiles(String path, List<File> files) {

        File realFile = new File(path);
        File[] catalogueFiles = realFile.listFiles();
        if (catalogueFiles != null) {
            for (File file : catalogueFiles){
                if (!file.isDirectory()
                        && file.getName().startsWith("XpestLog_")){
                    files.add(file);
                }
            }
        }
        return files;
    }

    /**判断超出大小**/
    public static boolean needCreateFileBeyondMB(long fileLength,int rangeMB) {
        long size = 1048576 * rangeMB;
        Log.d(TAG," fileLength : " + fileLength + "， range size : " + size);
        return fileLength >= size;
    }



}
