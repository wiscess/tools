package com.wiscess.simpleutil.common;

import java.util.Map;

/**
 * 项目中参数处理的工具方法
 * Created by liuBo
 * 2018/2/27.
 */
public class ParameterUtil {
    /**
     * 将参数放入到map中
     * @param name
     * @param value
     * @param map
     */
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
