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
    public int compare(Object o, Object t1) {
        String path1 = o.toString();
        String path2 = t1.toString();
        File file1 = new File(path1);
        File file2 = new File(path2);
        return (int) (file2.lastModified() - file1.lastModified());
//        if (file1.lastModified() < file2.lastModified()) {
//            return 1;
//        } else return -1;
    }
}
