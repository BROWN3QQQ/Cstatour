package com.brown3qqq.cstatour.service;

import com.alibaba.fastjson.JSONObject;
import com.brown3qqq.cstatour.dao.RestaurantRepository;
import com.brown3qqq.cstatour.dao.SpotRepository;
import com.brown3qqq.cstatour.pojo.Restaurant;
import com.brown3qqq.cstatour.pojo.Spot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class restaurantService {

    @Autowired
    RestaurantRepository restaurantRepository;
    //添加新餐馆信息
    public Map<String,String > add(JSONObject jsonObject){

        Map<String,String> map = new HashMap<String, String >();

        //过滤字段是否为空
        if(StringUtils.isEmpty(jsonObject.getString("name")) ){
            map.put("msg", "标题名不能为空");
            return map;
        }
        BigDecimal money = new BigDecimal(jsonObject.getString("moneystr"));
        Restaurant restaurant = new Restaurant(jsonObject.getString("name"),jsonObject.getString("imgadres"),jsonObject.getString("motherspot"),jsonObject.getString("spendtime"),jsonObject.getString("moneystr"),money,jsonObject.getString("moneyintroduce"),jsonObject.getString("kind"),jsonObject.getString("timeinterval"),jsonObject.getString("level"),jsonObject.getIntValue("index"),jsonObject.getBoolean("hot"),jsonObject.getBoolean("useful"),jsonObject.getString("introduce"),jsonObject.getString("remarks"));

        restaurantRepository.save(restaurant);

        map.put("state","成功");
        map.put("msg","添加景点成功");
        return map;

    }

    //更新餐馆内容
    public Map<String,String > update(JSONObject jsonObject){


        Map<String,String> map = new HashMap<String, String >();

        //过滤字段是否为空
        if(StringUtils.isEmpty(jsonObject.getString("name")) ){
            map.put("msg", "标题名不能为空");
            return map;
        }

        Restaurant restaurant = restaurantRepository.findById(jsonObject.getString("id")).get();

        if (restaurant == null){
            map.put("msg","景点内容不存在");
            return map;
        }

        BigDecimal money = new BigDecimal(jsonObject.getString("moneystr"));
        Restaurant newrestaurant = new Restaurant(jsonObject.getString("id"),jsonObject.getString("name"),jsonObject.getString("imgadres"),jsonObject.getString("motherspot"),jsonObject.getString("spendtime"),jsonObject.getString("moneystr"),money,jsonObject.getString("moneyintroduce"),jsonObject.getString("kind"),jsonObject.getString("timeinterval"),jsonObject.getString("level"),jsonObject.getIntValue("index"),jsonObject.getBoolean("hot"),jsonObject.getBoolean("useful"),jsonObject.getString("introduce"),jsonObject.getString("remarks"));

        restaurantRepository.save(newrestaurant);


        //更新字段
        map.put("state","成功");
        map.put("msg","更新景点成功");
        return map;
    }

    //删除餐馆信息
    public Map<String,String > delete(JSONObject jsonObject){

        Map<String,String> map = new HashMap<String, String >();

        //过滤字段是否为空

        if(StringUtils.isEmpty(jsonObject.getString("id"))){
            map.put("msg", "景点唯一id为空");
            return map;
        }
        Restaurant restaurant = restaurantRepository.findById(jsonObject.getString("id")).get();

        if (restaurant == null){
            map.put("msg","景点不存在");
            return map;
        }

        //删除字段
        restaurantRepository.delete(restaurant);

        map.put("state","成功");
        map.put("msg","删除文章成功");

        return map;
    }

    public JSONObject getallarticle(){

        JSONObject jsonObject = new JSONObject();

        Iterable<Restaurant> iterable = restaurantRepository.findAll();
        Iterator<Restaurant> iterator = iterable.iterator();

        int sum = 1;
        while (iterator.hasNext()){
            Restaurant restaurant = iterator.next();
            String SUM="";
            SUM = sum + "";
            jsonObject.put(SUM,restaurant);
            ++sum;
        }

        return jsonObject;
    }
}
