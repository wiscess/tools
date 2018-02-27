package com.wiscess.simpleutil.common;

import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by liuBo
 * 2018/2/27.
 */
public class RegularUtil {

    public static void main(String[] args) {
        System.out.println(RegularUtil.isMoney(null));
    }
    public static boolean validate(Object input,String regular){
        Pattern pattern=Pattern.compile(regular); // 判断小数点后2位的数字的正则表达式
        Matcher match=pattern.matcher(input==null?"":input.toString());
        if(match.matches()==false){
            return false;
        }else{
            return true;
        }
    }

    public static boolean isMoney(Object input){
        return isMoney(input,false);
    }

    public static boolean isMoney(Object input,boolean allowBlank){
        if (StringUtil.isEmpty(input)) return allowBlank;
        return validate(input,"^(([1-9]{1}\\d*)|([0]{1}))(\\.(\\d){0,2})?$");
    }
}
