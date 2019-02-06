package com.brown3qqq.cstatour.service;

import com.alibaba.fastjson.JSONObject;
import com.brown3qqq.cstatour.dao.ColumnRepository;
import com.brown3qqq.cstatour.dao.CommodityRepository;
import com.brown3qqq.cstatour.pojo.Commodity;
import com.brown3qqq.cstatour.pojo.Kind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class commodityService {
    @Autowired
    CommodityRepository commodityrepository;
    //添加新商品
    public Map<String,String > add(JSONObject jsonObject){

        Map<String,String> map = new HashMap<String, String >();

        //过滤字段是否为空
        if(StringUtils.isEmpty(jsonObject.getString("name")) ){
            map.put("msg", "商品名不能为空");
            return map;
        }
        if(StringUtils.isEmpty(jsonObject.getString("imgadres")) ){
            map.put("msg", "商品图片地址不能为空");
            return map;
        }

        BigDecimal money = new BigDecimal(jsonObject.getString("moneystr"));
        Commodity commodity = new Commodity(jsonObject.getString("name"),jsonObject.getString("imgadres"),jsonObject.getString("moneystr"),money,jsonObject.getString("company"),jsonObject.getIntValue("index"),jsonObject.getString("motherkind"),jsonObject.getBoolean("hot"),jsonObject.getBoolean("useful"),jsonObject.getString("contnet"));

        commodityrepository.save(commodity);

        map.put("state","添加栏目成功");
        map.put("msg","添加栏目成功");

        return map;


    }
    //更新商品
    public Map<String,String > update(JSONObject jsonObject) {


        Map<String,String> map = new HashMap<String, String >();

        //过滤字段是否为空
        if(StringUtils.isEmpty(jsonObject.getString("name")) ){
            map.put("msg", "商品名不能为空");
            return map;
        }
        if(StringUtils.isEmpty(jsonObject.getString("imgadres")) ){
            map.put("msg", "商品图片地址不能为空");
            return map;
        }

        BigDecimal money = new BigDecimal(jsonObject.getString("moneystr"));

        Commodity commodity = commodityrepository.findById(jsonObject.getString("id")).get();

        if (commodity == null){
            map.put("msg", "商品不存在");
            return map;
        }

        commodity.setCommodityname(jsonObject.getString("name"));
        commodity.setCompany(jsonObject.getString("company"));
        commodity.setContent(jsonObject.getString("content"));
        commodity.setHot(jsonObject.getBoolean("hot"));
        commodity.setMoney(money);
        commodity.setMoneystr(jsonObject.getString("moneystr"));
        commodity.setMotherkind(jsonObject.getString("motherkind"));
        commodity.setImgadres(jsonObject.getString("imgadres"));
        commodity.setIndex(jsonObject.getIntValue("index"));

        commodityrepository.save(commodity);

        map.put("state", "成功");
        map.put("msg", "更新商品成功");

        return map;
    }

    //删除商品
    public Map<String,String > delete(JSONObject jsonObject) {

        Map<String, String> map = new HashMap<String, String>();

        Commodity commodity = commodityrepository.findById(jsonObject.getString("id")).get();

        if (commodity == null) {
            map.put("msg", " 商品不存在");
            return map;
        }

        commodityrepository.delete(commodity);
        map.put("state", "成功");
        map.put("msg", "删除文章成功");

        return map;
    }

    //获取commodity库里所有商品
    public JSONObject getallcommodity(){

        JSONObject jsonObject = new JSONObject();

        Iterable<Commodity> iterable = commodityrepository.findAll();
        Iterator<Commodity> iterator = iterable.iterator();
        int sum = 1;
        while (iterator.hasNext()){
            Commodity commodity = iterator.next();

            String SUM="";
            SUM = sum + "";
            jsonObject.put(SUM,commodity);
            ++sum;
        }
        return jsonObject;
    }
}
