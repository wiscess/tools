package com.wiscess.simpleutil.common;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证格式的一些工具方法
 * Created by liuBo
 * 2018/2/27.
 */
public class RegularUtil {

    public static void main(String[] args) {
        System.out.println(RegularUtil.isMoney(null));
    }

    /**
     * 根据正则表达式验证
     * @param input
     * @param regular
     * @return
     */
    public static boolean validate(Object input,String regular){
        Pattern pattern=Pattern.compile(regular); // 判断小数点后2位的数字的正则表达式
        Matcher match=pattern.matcher(input==null?"":input.toString());
        if(match.matches()==false){
            return false;
        }else{
            return true;
        }
    }

    /**
     * 判断是金钱格式
     * @param input
     * @return
     */
    public static boolean isMoney(Object input){
        return isMoney(input,false);
    }

    /**
     * 判断是否是金钱格式，指定是否支持为空
     * @param input
     * @param allowBlank
     * @return
     */
    public static boolean isMoney(Object input,boolean allowBlank){
        if (StringUtil.isEmpty(input)) return allowBlank;
        return validate(input,"^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$");
    }
}
