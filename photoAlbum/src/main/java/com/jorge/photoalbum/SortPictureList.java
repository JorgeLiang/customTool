package com.jorge.photoalbum;

import java.io.File;
import java.util.Comparator;

/**
 * Created by Jorge on 7/23/21.
 *
 * 图片修改时间排序
 */

public class SortPictureList implements Comparator {
    @Override
    public int compare(Object o1, Object o2) {
//        String path1 = o1.toString();
//        String path2 = o2.toString();
        File file1 = new File(o1.toString());
        File file2 = new File(o2.toString());
        return (int) (file2.lastModified() - file1.lastModified());
//        if (file1.lastModified() < file2.lastModified()) {
//            return 1;
//        } else return -1;
    }
}
