package cn.orditech.tool;

import com.alibaba.fastjson.JSONObject;

import java.util.Map;

/**
 * Created by Administrator on 2017/3/26.
 */
public class MyUtil {
    public static String getJSONString(int code , Map<String,Object>map){
        JSONObject json = new JSONObject();
        json.put("code",code);
        for(Map.Entry entry :map.entrySet()){
            json.put((String) entry.getKey(),entry.getValue());
        }
        return json.toJSONString();
    }

    public static String getJSONString(int code, String msg) {
        JSONObject json = new JSONObject();
        json.put("code", code);
        json.put("msg", msg);
        return json.toJSONString();
    }
}
