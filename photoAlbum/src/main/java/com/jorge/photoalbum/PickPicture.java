package com.jorge.photoalbum;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by Jorge on 7/23/21.
 */

public class PickPicture {

    private static final String TAG = "PickPicture : ";
    private Context mContext;
    private HashMap<String, List<String>> mGroupMap = new HashMap<>();
    private List<AlbumBean> mPictureItems = new ArrayList<>();
    private PickPictureThread mThread;
    private PickPictureHandler mHandler;
    private PickPictureCallback mCallback;

    public PickPicture(Context context, PickPictureCallback callback) {
        this.mContext = context;
        this.mCallback = callback;
        mThread = new PickPictureThread() {
            @Override
            public void pickPictureThreadRun() {
                readPicture();
//                readVideo();
            }
        };
        mHandler = new PickPictureHandler(mCallback);
    }

    void start() {
        mThread.start();
    }

    private void readPicture() {
        int count = 0;

        mGroupMap.clear();
        mPictureItems.clear();
        Uri pictureUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
        ContentResolver contentResolver = mContext.getContentResolver();
        //只查询jpeg和png的图片
        Cursor cursor = contentResolver.query(pictureUri, null,
                MediaStore.Images.Media.MIME_TYPE + "=? or "
                        + MediaStore.Images.Media.MIME_TYPE + "=?",
                new String[]{"image/jpeg", "image/png"}, MediaStore.Images.Media.DATE_MODIFIED);
        if (cursor == null || cursor.getCount() == 0) {
            mHandler.sendEmptyMessage(PickPictureHandler.SCAN_ERROR);
        } else {
            while (cursor.moveToNext()) {
                count ++;
                //获取图片的路径
                String path = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA));
                try {
                    //获取该图片的父路径名
                    String parentName = new File(path).getParentFile().getName();

                    int pathIndex = path.lastIndexOf("/" + parentName);
                    String fileKey = path.substring(0,pathIndex + parentName.length() + 1);//通过文件夹路径为key

                    //根据父路径名将图片放入到groupMap中
                    if (!mGroupMap.containsKey(fileKey)) {
                        List<String> chileList = new ArrayList<>();
                        chileList.add(path);
                        mGroupMap.put(fileKey, chileList);
                    } else {
                        mGroupMap.get(fileKey).add(path);
                    }

//                    if (!mGroupMap.containsKey(parentName)) {
//                        List<String> chileList = new ArrayList<>();
//                        chileList.add(path);
//                        mGroupMap.put(parentName, chileList);
//                    } else {
//                        mGroupMap.get(parentName).add(path);
//                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d(TAG,"readPicture Exception : " + e);
                }
            }

            readVideo();

            Log.d(TAG,"readVideo count : " + count);

            cursor.close();
            //通知Handler扫描图片完成
            mPictureItems = subGroupOfPicture(mGroupMap);
            Message message = mHandler.obtainMessage();
            message.obj = mPictureItems;
            message.what = PickPictureHandler.SCAN_OK;
            message.sendToTarget();

        }
    }

    private void readVideo() {
        int count = 0;

        Uri videoUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
        ContentResolver contentResolver = mContext.getContentResolver();

        Cursor cursor = contentResolver.query(videoUri,null,null,null, MediaStore.Images.Media.DATE_MODIFIED);
        if (cursor == null || cursor.getCount() == 0){

        }else {
            while (cursor.moveToNext()){
                count ++;

                //获取视频的路径
                String videoPath = cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA));

                try {
                    //获取该图片的父路径名
                    String parentName = new File(videoPath).getParentFile().getName();

                    int pathIndex = videoPath.lastIndexOf("/" + parentName);
                    String fileKey = videoPath.substring(0,pathIndex + parentName.length() + 1);//通过文件夹路径为key

                    //根据父路径名将视频放入到groupMap中
                    if (!mGroupMap.containsKey(fileKey)) {
                        List<String> chileList = new ArrayList<>();
                        chileList.add(videoPath);
                        mGroupMap.put(fileKey, chileList);
                    } else {
                        mGroupMap.get(fileKey).add(videoPath);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    Log.d(TAG,"readVideo Exception : " + e);
                }
            }
            Log.d(TAG,"readVideo count : " + count);
            cursor.close();
            //通知Handler扫描图片完成
//            mPictureItems = subGroupOfPicture(mGroupMap);
//            Message message = mHandler.obtainMessage();
//            message.obj = mPictureItems;
//            message.what = PickPictureHandler.SCAN_OK;
//            message.sendToTarget();
        }

    }

    /**
     * 组装分组数据源，因为我们扫描手机的时候将图片信息放在HashMap中
     * 所以需要遍历HashMap将数据组装成List
     *
     * @param groupMap
     * @return
     */
    private List<AlbumBean> subGroupOfPicture(HashMap<String, List<String>> groupMap) {
        List<AlbumBean> list = new ArrayList<>();
        if (groupMap.size() == 0) {
            return list;
        }
        Iterator<Map.Entry<String, List<String>>> it = groupMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, List<String>> entry = it.next();
            AlbumBean pictureTotal = new AlbumBean();
            String key = entry.getKey();
            List<String> value = entry.getValue();
            SortPictureList sortList = new SortPictureList();
            Collections.sort(value, sortList);//按修改时间排序

//            int lastIndex = key.lastIndexOf("/");
//            pictureTotal.setFolderName(key.substring(lastIndex + 1));

            pictureTotal.setFolderName(key.substring(key.lastIndexOf("/") + 1));
            pictureTotal.setFolderPath(key);
            pictureTotal.setPhotoPathList(value);
            pictureTotal.setPictureCount(value.size());
            pictureTotal.setTopPicturePath(value.get(0));//获取该组的第一张图片
            list.add(pictureTotal);
        }
        return list;
    }

    List<String> getChildPathList(int position) {
        List<String> childList = new ArrayList<>();
        if (mPictureItems.size() == 0)
            return childList;
        AlbumBean pictureTotal = mPictureItems.get(position);
        childList = mGroupMap.get(pictureTotal.getFolderPath());
        SortPictureList sortList = new SortPictureList();
        Collections.sort(childList, sortList);
        return childList;
    }
}

