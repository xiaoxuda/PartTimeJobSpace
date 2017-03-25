package cn.orditech.result;

import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * 响应结果封装
 * Created by kimi on 2017/3/25.
 */
public class JsonResult implements Serializable{
    /**
     * 请求状态
     */
    private Boolean success;
    /**
     * 附加消息
     */
    private String message;
    /**
     * 响应数据
     */
    private JSONObject data = new JSONObject ();

    public static JsonResult successResult(JSONObject data){
        JsonResult result = new JsonResult();
        result.success = true;
        result.data = data;
        return result;
    }

    public static JsonResult failResult(String message){
        JsonResult result = new JsonResult();
        result.success = false;
        result.message = message;
        return result;
    }

    public void addData(String key,Object value){
        data.put (key,value);
    }

    public Boolean getSuccess () {
        return success;
    }

    public void setSuccess (Boolean success) {
        this.success = success;
    }

    public String getMessage () {
        return message;
    }

    public void setMessage (String message) {
        this.message = message;
    }

    public JSONObject getData () {
        return data;
    }

    public void setData (JSONObject data) {
        this.data = data;
    }
}
