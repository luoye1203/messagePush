package com.xzst.relation.mp.util;

import com.google.common.base.Strings;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.Map;

/**
 * Created by li on 2017/9/18.
 */
public class JSONTools {
    private static Gson gson=new Gson();

    public static  <T> T string2JavaBean(String jsonStr,Class<T> cls){
        if(Strings.isNullOrEmpty(jsonStr)){
            return null;
        }
        T re=gson.fromJson(jsonStr,cls);
        return re;
    }

    public static Map json2Map(String json) {
        if(Strings.isNullOrEmpty(json)){
            return null;
        }
        Map<String, String> maps = gson.fromJson(json, new TypeToken<Map<String, String>>() {}.getType());
        return maps;
    }


    /**
     * javabean to json
     *
     * @param bean
     * @return
     */
    public static String javabean2Json(Object bean) {
        String json = gson.toJson(bean);
        return json;
    }

}
