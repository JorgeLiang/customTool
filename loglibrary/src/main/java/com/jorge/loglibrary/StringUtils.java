package com.jorge.loglibrary;

/**
 * Created by Jorge on 4/29/21.
 */

public class StringUtils {

    /**
     * @param beginStr  "_"
     * @param endStr    "."
     * @param totalStr  AAA_1.txt
     * @return          1
     */
    public static String cutMiddleString(String beginStr,String endStr,String totalStr){
        String cutFinish = "";
        if (totalStr.contains(beginStr) && totalStr.contains(endStr)){
//            int beginIndex = totalStr.indexOf(beginStr);
            int beginIndex = totalStr.lastIndexOf(beginStr);
            int lastIndex = totalStr.lastIndexOf(endStr);

            int begin = beginIndex + beginStr.length();

            cutFinish = totalStr.substring(begin,lastIndex);
        }

        return cutFinish.isEmpty() ? totalStr : cutFinish;
    }

}
