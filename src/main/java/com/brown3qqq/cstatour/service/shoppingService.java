package com.brown3qqq.cstatour.service;

import com.alibaba.fastjson.JSONObject;
import com.brown3qqq.cstatour.dao.EntertainmentRepository;
import com.brown3qqq.cstatour.dao.ShoppingRepository;
import com.brown3qqq.cstatour.pojo.Entertainment;
import com.brown3qqq.cstatour.pojo.Shopping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Classname shoppingService
 * @Description TODO
 * @Date 2019/2/16 15:09
 * @Created by CQ
 */
public class shoppingService {
    @Autowired
    ShoppingRepository shoppingRepository;

    //添加新购物
    public Map<String,String > add(JSONObject jsonObject){

        Map<String,String> map = new HashMap<String, String >();

        //过滤字段是否为空
        if(StringUtils.isEmpty(jsonObject.getString("name")) ){
            map.put("msg", "标题名不能为空");
            return map;
        }
        BigDecimal money = new BigDecimal(jsonObject.getString("moneystr"));
        Shopping shopping = new Shopping(jsonObject.getString("name"),jsonObject.getString("imgadres"),jsonObject.getString("motherspot"),jsonObject.getString("spendtime"),jsonObject.getString("moneystr"),money,jsonObject.getString("moneyintroduce"),jsonObject.getString("timeinterval"),jsonObject.getString("level"),jsonObject.getIntValue("ineex"),jsonObject.getBoolean("hot"),jsonObject.getBoolean("useful"),jsonObject.getString("introduce"),jsonObject.getString("remarks"));

        shoppingRepository.save(shopping);

        map.put("state","成功");
        map.put("msg","添加购物信息成功");
        return map;

    }

    //更新购物内容
    public Map<String,String > update(JSONObject jsonObject){

        Map<String,String> map = new HashMap<String, String >();

        //过滤字段是否为空
        if(StringUtils.isEmpty(jsonObject.getString("name")) ){
            map.put("msg", "标题名不能为空");
            return map;
        }
        Shopping shopping = shoppingRepository.findById(jsonObject.getString("id")).get();

        if (!shoppingRepository.findById(jsonObject.getString("id")).isPresent()){
            map.put("msg","购物信息内容不存在");
            return map;
        }else{
            BigDecimal money = new BigDecimal(jsonObject.getString("moneystr"));
            Shopping newshopping = new Shopping(jsonObject.getString("id"),jsonObject.getString("name"),jsonObject.getString("imgadres"),jsonObject.getString("motherspot"),jsonObject.getString("spendtime"),jsonObject.getString("moneystr"),money,jsonObject.getString("moneyintroduce"),jsonObject.getString("timeinterval"),jsonObject.getString("level"),jsonObject.getIntValue("ineex"),jsonObject.getBoolean("hot"),jsonObject.getBoolean("useful"),jsonObject.getString("introduce"),jsonObject.getString("remarks"));

            shoppingRepository.save(newshopping);

            //更新字段
            map.put("state","成功");
            map.put("msg","更新购物信息成功");
            return map;
        }
    }

    //删除购物信息
    public Map<String,String > delete(JSONObject jsonObject){

        Map<String,String> map = new HashMap<String, String >();

        //过滤字段是否为空

        if(StringUtils.isEmpty(jsonObject.getString("id"))){
            map.put("msg", "娱乐唯一id为空");
            return map;
        }
        Shopping shopping = shoppingRepository.findById(jsonObject.getString("id")).get();

        if (!shoppingRepository.findById(jsonObject.getString("id")).isPresent()){
            map.put("msg","购物信息内容不存在");
            return map;
        }else{

            shoppingRepository.delete(shopping);
            map.put("state","成功");
            map.put("msg","删除文章成功");

            return map;
        }

    }

    public JSONObject getallentertainment(){

        JSONObject jsonObject = new JSONObject();

        Iterable<Shopping> iterable = shoppingRepository.findAll();
        Iterator<Shopping> iterator = iterable.iterator();

        int sum = 1;
        while (iterator.hasNext()){
            Shopping shopping = iterator.next();
            String SUM="";
            SUM = sum + "";
            jsonObject.put(SUM,shopping);
            ++sum;
        }

        return jsonObject;
    }

}
