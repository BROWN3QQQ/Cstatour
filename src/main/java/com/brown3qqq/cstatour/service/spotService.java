package com.brown3qqq.cstatour.service;

import com.alibaba.fastjson.JSONObject;
import com.brown3qqq.cstatour.dao.SpotRepository;
import com.brown3qqq.cstatour.pojo.Article;
import com.brown3qqq.cstatour.pojo.Column;
import com.brown3qqq.cstatour.pojo.Spot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

@Service
public class spotService {
    @Autowired
    SpotRepository spotRepository;

    //添加新景点
    public Map<String,String > add(JSONObject jsonObject){

        Map<String,String> map = new HashMap<String, String >();

        //过滤字段是否为空
        if(StringUtils.isEmpty(jsonObject.getString("name")) ){
            map.put("msg", "标题名不能为空");
            return map;
        }
        BigDecimal money = new BigDecimal(jsonObject.getString("moneystr"));
        Spot spot = new Spot(jsonObject.getString("name"),jsonObject.getString("imgadres"),jsonObject.getString("motherspot"),jsonObject.getString("spendtime"),jsonObject.getString("moneystr"),money,jsonObject.getString("moneyintroduce"),jsonObject.getString("kind"),jsonObject.getString("timeinterval"),jsonObject.getString("level"),jsonObject.getIntValue("index"),jsonObject.getBoolean("hot"),jsonObject.getBoolean("useful"),jsonObject.getString("introduce"),jsonObject.getString("remarks"));

        spotRepository.save(spot);

        map.put("state","成功");
        map.put("msg","添加景点成功");
        return map;

    }
    //更新景点内容
    public Map<String,String > update(JSONObject jsonObject){


        Map<String,String> map = new HashMap<String, String >();

        //过滤字段是否为空
        if(StringUtils.isEmpty(jsonObject.getString("name")) ){
            map.put("msg", "标题名不能为空");
            return map;
        }

        Spot spot = spotRepository.findById(jsonObject.getString("id")).get();

        if (spot == null){
            map.put("msg","景点内容不存在");
            return map;
        }

        BigDecimal money = new BigDecimal(jsonObject.getString("moneystr"));


        int old = spot.getIndex();
        int newindex = jsonObject.getIntValue("index");
        if (old != newindex){
            if (old > newindex){
                Iterable<Spot> iterable = spotRepository.findAll();
                Iterator<Spot> iterator = iterable.iterator();


                while (iterator.hasNext()){
                    Spot newspot = iterator.next();
                    if (newspot.getIndex() >= newindex && newspot.getIndex() < old){
                        newspot.setIndex((newspot.getIndex() + 1));
                    }
                    spotRepository.save(newspot);
                }

            }else {
                Iterable<Spot> iterable = spotRepository.findAll();
                Iterator<Spot> iterator = iterable.iterator();

                while (iterator.hasNext()){
                    Spot newspot = iterator.next();
                    if (newspot.getIndex() <= newindex && newspot.getIndex() > old){
                        newspot.setIndex((newspot.getIndex() - 1));
                    }
                    spotRepository.save(newspot);
                }

            }


        }

        Spot newspot = new Spot(jsonObject.getString("id"),jsonObject.getString("name"),jsonObject.getString("imgadres"),jsonObject.getString("motherspot"),jsonObject.getString("spendtime"),jsonObject.getString("moneystr"),money,jsonObject.getString("moneyintroduce"),jsonObject.getString("kind"),jsonObject.getString("timeinterval"),jsonObject.getString("level"),jsonObject.getIntValue("index"),jsonObject.getBoolean("hot"),jsonObject.getBoolean("useful"),jsonObject.getString("introduce"),jsonObject.getString("remarks"));
        spotRepository.save(newspot);

        //更新字段
        map.put("state","成功");
        map.put("msg","更新景点成功");
        return map;
    }

    //删除景点
    public Map<String,String > delete(JSONObject jsonObject){

        Map<String,String> map = new HashMap<String, String >();

        //过滤字段是否为空

        if(StringUtils.isEmpty(jsonObject.getString("id"))){
            map.put("msg", "景点唯一id为空");
            return map;
        }
        Spot spot = spotRepository.findById(jsonObject.getString("id")).get();

        if (spot == null){
            map.put("msg","景点不存在");
            return map;
        }

        //删除字段
        spotRepository.delete(spot);
        map.put("state","成功");
        map.put("msg","删除文章成功");

        return map;
    }

    //获取spot库里所有景点
    public JSONObject getallarticle(){

        JSONObject jsonObject = new JSONObject();

        Iterable<Spot> iterable = spotRepository.findAll();
        Iterator<Spot> iterator = iterable.iterator();

        List<Spot> list = new ArrayList<>();
        int sum = 0 ;
        while (iterator.hasNext()){
            Spot spot = iterator.next();
            list.add(spot);
            sum = sum + 1;
        }

        int k = 1;
        for(int i = 0;i<sum;i++){
            for(int j = 0;j<sum;j++){
                for(Spot spot : list){
                    if (k == spot.getIndex() ){
                        String K = "";
                        K = k + "";
                        jsonObject.put(K,spot);
                    }
                }
                k = k + 1;
            }
        }


        return jsonObject;
    }


}
