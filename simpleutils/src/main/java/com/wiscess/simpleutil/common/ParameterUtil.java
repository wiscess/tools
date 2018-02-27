package com.wiscess.simpleutil.common;

import java.util.Map;

/**
 * Created by liuBo
 * 2018/2/27.
 */
public class ParameterUtil {
    public static void process(String name , Object value , Map<String,Object> map){
        if (value ==null) return ;
        if(value instanceof String){
            if (value.toString().trim().equals("")) return;
            map.put(name,value.toString().trim());
        }else{
            map.put(name,value);
        }
    }
}
