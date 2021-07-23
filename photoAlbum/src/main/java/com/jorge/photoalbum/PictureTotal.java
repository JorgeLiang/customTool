package com.jorge.photoalbum;

/**
 * Created by Jorge on 7/23/21.
 */

public class PictureTotal {
    private String topPicturePath;  //文件夹的第一张图片路径
    private String folderName;      //文件夹名
    private int pictureCount;       //文件夹中的图片数
    private String folderPath;      //文件夹路径

    public String getFolderPath() {
        return folderPath;
    }

    public void setFolderPath(String folderPath) {
        this.folderPath = folderPath;
    }

    public String getTopPicturePath() {
        return topPicturePath;
    }

    public void setTopPicturePath(String topPicturePath) {
        this.topPicturePath = topPicturePath;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public int getPictureCount() {
        return pictureCount;
    }

    public void setPictureCount(int pictureCount) {
        this.pictureCount = pictureCount;
    }

}
