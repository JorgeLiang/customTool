package com.jorge.loglibrary;

import java.text.DecimalFormat;

/**
 * Created by Jorge on 4/29/21.
 */

public class FileUtils {

    private static DecimalFormat df = new DecimalFormat("#0.00");

    public static String FormatFileSize(long fileS) {
        String fileSizeString = "";
        String wrongSize = "0B";
        if (fileS == 0) {
            return wrongSize;
        }
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {

            fileSizeString = df.format((double) fileS / 1024) + "KB";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "MB";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "GB";
        }
        return fileSizeString;

    }

}
