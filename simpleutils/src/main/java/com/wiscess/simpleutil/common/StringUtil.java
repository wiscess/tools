package com.wiscess.simpleutil.common;

/**
 * 字符串处理的工具方法
 * Created by liuBo
 * 2018/2/27.
 */
public class StringUtil {
    /**
     * 字符串为空
     * @param str
     * @return
     */
    public static boolean isEmpty(Object str){
        if (str==null||"".equals(str.toString().trim())){
            return true;
        }
        return false;
    }

    /**
     * 字符串非空
     * @param str
     * @return
     */
    public static boolean isNotEmpty(Object str){
        return !isEmpty(str);
    }

    /**
     * 身份证只显示后6位
     * @param cardNo
     * @return
     */
    public static String preventCardNo(String cardNo){
        if (StringUtil.isEmpty(cardNo)||cardNo.trim().length()<13) return "";
        return "XXXXXXXXXXXX"+cardNo.substring(12);
    }
}
