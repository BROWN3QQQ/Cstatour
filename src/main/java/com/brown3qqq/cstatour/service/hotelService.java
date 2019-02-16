package com.brown3qqq.cstatour.service;

import com.alibaba.fastjson.JSONObject;
import com.brown3qqq.cstatour.dao.HotelRepository;
import com.brown3qqq.cstatour.pojo.Travel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Classname hotelService
 * @Description
 * @Date 2019/2/16 18:27
 * @Created by CQ
 */
public class hotelService {
    @Autowired
    HotelRepository hotelRepository;

    //添加新酒店信息
    public Map<String,String > add(JSONObject jsonObject){

        Map<String,String> map = new HashMap<String, String >();

        //过滤字段是否为空
        if(StringUtils.isEmpty(jsonObject.getString("name")) ){
            map.put("msg", "标题名不能为空");
            return map;
        }
        Travel travel = new Travel(jsonObject.getString("name"),jsonObject.getString("imgadres"),jsonObject.getString("day"),jsonObject.getString("night"),jsonObject.getString("manmoney"),jsonObject.getString("kidmoney"),jsonObject.getString("moneyintroduce"),jsonObject.getString("level"),jsonObject.getIntValue("index"),jsonObject.getString("line"),jsonObject.getBoolean("hot"),jsonObject.getBoolean("useful"),jsonObject.getString("conspot"),jsonObject.getString("trip"),jsonObject.getString("tripspecial"),jsonObject.getString("remarks"));

        travelRepository.save(travel);

        map.put("state","成功");
        map.put("msg","添加旅游信息成功");
        return map;

    }

    //更新酒店信息内容
    public Map<String,String > update(JSONObject jsonObject){

        Map<String,String> map = new HashMap<String, String >();

        //过滤字段是否为空
        if(StringUtils.isEmpty(jsonObject.getString("name")) ){
            map.put("msg", "标题名不能为空");
            return map;
        }
        Travel travel = travelRepository.findById(jsonObject.getString("id")).get();

        if (!travelRepository.findById(jsonObject.getString("id")).isPresent()){
            map.put("msg","旅游信息内容不存在");
            return map;
        }else{
            Travel newtravel = new Travel(jsonObject.getString("name"),jsonObject.getString("imgadres"),jsonObject.getString("day"),jsonObject.getString("night"),jsonObject.getString("manmoney"),jsonObject.getString("kidmoney"),jsonObject.getString("moneyintroduce"),jsonObject.getString("level"),jsonObject.getIntValue("index"),jsonObject.getString("line"),jsonObject.getBoolean("hot"),jsonObject.getBoolean("useful"),jsonObject.getString("conspot"),jsonObject.getString("trip"),jsonObject.getString("tripspecial"),jsonObject.getString("remarks"));

            travelRepository.save(newtravel);

            //更新字段
            map.put("state","成功");
            map.put("msg","更新旅游信息成功");
            return map;
        }
    }

    //删除酒店容内容信息
    public Map<String,String > delete(JSONObject jsonObject){

        Map<String,String> map = new HashMap<String, String >();

        //过滤字段是否为空

        if(StringUtils.isEmpty(jsonObject.getString("id"))){
            map.put("msg", "旅游信息唯一id为空");
            return map;
        }
        Travel travel = travelRepository.findById(jsonObject.getString("id")).get();

        if (!travelRepository.findById(jsonObject.getString("id")).isPresent()){
            map.put("msg","旅游信息内容不存在");
            return map;
        }else{

            travelRepository.delete(travel);
            map.put("state","成功");
            map.put("msg","删除文章成功");

            return map;
        }

    }

    public JSONObject getallHotel(){

        JSONObject jsonObject = new JSONObject();

        Iterable<Travel> iterable = travelRepository.findAll();
        Iterator<Travel> iterator = iterable.iterator();

        int sum = 1;
        while (iterator.hasNext()){
            Travel travel = iterator.next();
            String SUM="";
            SUM = sum + "";
            jsonObject.put(SUM,travel);
            ++sum;
        }

        return jsonObject;
    }

}
