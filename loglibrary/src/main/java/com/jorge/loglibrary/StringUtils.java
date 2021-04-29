package com.jorge.loglibrary;

/**
 * Created by Jorge on 4/29/21.
 */

public class StringUtils {

    /**
     * @param beginStr  "/文件同步备份"
     * @param endStr    "/"
     * @param totalStr  /storage/emulated/0/文件同步备份/abc/bakpal.txt
     * @return          /abc
     */
    public static String cutMiddleString_v2(String beginStr,String endStr,String totalStr){
        String cutFinish = "";
        if (totalStr.contains(beginStr) && totalStr.contains(endStr)){
            int beginIndex = totalStr.indexOf(beginStr);
            int lastIndex = totalStr.lastIndexOf(endStr);

            int begin = beginIndex + beginStr.length();

            cutFinish = totalStr.substring(begin,lastIndex);
        }

        return cutFinish.isEmpty() ? totalStr : cutFinish;
    }

}
