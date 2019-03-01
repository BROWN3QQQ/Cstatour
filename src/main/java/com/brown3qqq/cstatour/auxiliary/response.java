package com.brown3qqq.cstatour.auxiliary;

import com.brown3qqq.cstatour.pojo.State.Statecode;
import com.alibaba.fastjson.JSONObject;
/**
 * @Classname response
 * @Description 辅助层
 * @Date 2019/2/25 18:45
 * @Created by CQ
 */
public class response {
    private Statecode statecode;
    private JSONObject jsonObject;

    public response(){}

    public response(Statecode statecode) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",statecode.getCode());
        jsonObject.put("msg",statecode.getMsg());

        this.jsonObject  = jsonObject;
        this.statecode = statecode;
    }


    public response(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public JSONObject getJsonObject() {
        return jsonObject;
    }

    public void setJsonObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public Statecode getStatecode() {
        return statecode;
    }

    public void setStatecode(Statecode statecode) {
        this.statecode = statecode;
    }
}
