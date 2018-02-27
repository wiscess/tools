package com.wiscess.simpleutil.common;

/**
 * Created by liuBo
 * 2018/2/27.
 */
public class StringUtil {
    public static boolean isEmpty(Object str){
        if (str==null||"".equals(str.toString().trim())){
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(Object str){
        return !isEmpty(str);
    }


    public static String preventCardNo(String cardNo){
        if (StringUtil.isEmpty(cardNo)||cardNo.trim().length()<13) return "";
        return "XXXXXXXXXXXX"+cardNo.substring(12);
    }
}
