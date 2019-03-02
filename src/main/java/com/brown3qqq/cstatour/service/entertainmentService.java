package com.brown3qqq.cstatour.service;

import com.alibaba.fastjson.JSONObject;
import com.brown3qqq.cstatour.pojo.Entertainment;
import com.brown3qqq.cstatour.pojo.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import com.brown3qqq.cstatour.dao.EntertainmentRepository;

import java.math.BigDecimal;
import java.util.*;

@Service
public class entertainmentService{

    @Autowired
    EntertainmentRepository entertainmentRepository;


    //添加新娱乐信息
    public Map<String,String > add(JSONObject jsonObject){

        Map<String,String> map = new HashMap<String, String >();

        //过滤字段是否为空
        if(StringUtils.isEmpty(jsonObject.getString("name")) ){
            map.put("msg", "标题名不能为空");
            return map;
        }
        BigDecimal money = new BigDecimal(jsonObject.getString("moneystr"));
        Entertainment entertainment = new Entertainment(jsonObject.getString("name"),jsonObject.getString("imgadres"),jsonObject.getString("motherspot"),jsonObject.getString("spendtime"),jsonObject.getString("moneystr"),money,jsonObject.getString("moneyintroduce"),jsonObject.getString("timeinterval"),jsonObject.getString("level"),jsonObject.getIntValue("index"),jsonObject.getBoolean("hot"),jsonObject.getBoolean("useful"),jsonObject.getString("introduce"),jsonObject.getString("remarks"));

        entertainmentRepository.save(entertainment);

        map.put("state","成功");
        map.put("msg","添加景点成功");
        return map;

    }

    //更新娱乐内容
    public Map<String,String > update(JSONObject jsonObject){


        Map<String,String> map = new HashMap<String, String >();

        //过滤字段是否为空
        if(StringUtils.isEmpty(jsonObject.getString("name")) ){
            map.put("msg", "标题名不能为空");
            return map;
        }
        Entertainment entertainment =entertainmentRepository.findById(jsonObject.getString("id")).get();

        if (!entertainmentRepository.findById(jsonObject.getString("id")).isPresent()){
            map.put("msg","景点内容不存在");
            return map;
        }else{

            //代码太冗余了，令人抓狂
            //            。。。。
            BigDecimal money = new BigDecimal(jsonObject.getString("moneystr"));

            int old = entertainmentRepository.findById(jsonObject.getString("id")).get().getIndex();
            int newindex = jsonObject.getIntValue("index");
            if (old != newindex){
                if (old >newindex){
                    Iterable<Entertainment> iterable = entertainmentRepository.findAll();
                    Iterator<Entertainment> iterator = iterable.iterator();
                    while (iterator.hasNext()){
                        Entertainment newentertainment1 = iterator.next();
                        if (newentertainment1.getIndex() >= newindex && newentertainment1.getIndex() < old){
                            newentertainment1.setIndex((newentertainment1.getIndex()+ 1));
                        }
                        entertainmentRepository.save(newentertainment1);
                    }

                }else {

                    Iterable<Entertainment> iterable = entertainmentRepository.findAll();
                    Iterator<Entertainment> iterator = iterable.iterator();
                    while (iterator.hasNext()){
                        Entertainment newentertainment1 = iterator.next();
                        if (newentertainment1.getIndex() <= newindex && newentertainment1.getIndex() > old){
                            newentertainment1.setIndex((newentertainment1.getIndex() -1 ));
                        }
                        entertainmentRepository.save(newentertainment1);
                    }
                }
            }

            Entertainment newentertainment = new Entertainment(jsonObject.getString("id"),jsonObject.getString("name"),jsonObject.getString("imgadres"),jsonObject.getString("motherspot"),jsonObject.getString("spendtime"),jsonObject.getString("moneystr"),money,jsonObject.getString("moneyintroduce"),jsonObject.getString("timeinterval"),jsonObject.getString("level"),jsonObject.getIntValue("index"),jsonObject.getBoolean("hot"),jsonObject.getBoolean("useful"),jsonObject.getString("introduce"),jsonObject.getString("remarks"));

            entertainmentRepository.save(newentertainment);

            //更新字段
            map.put("state","成功");
            map.put("msg","更新景点成功");
            return map;
        }


    }

    //删除娱乐信息
    public Map<String,String > delete(JSONObject jsonObject){

        Map<String,String> map = new HashMap<String, String >();

        //过滤字段是否为空

        if(StringUtils.isEmpty(jsonObject.getString("id"))){
            map.put("msg", "娱乐唯一id为空");
            return map;
        }
        Entertainment entertainment = entertainmentRepository.findById(jsonObject.getString("id")).get();

        if (entertainment == null){
            map.put("msg","景点不存在");
            return map;
        }

        //删除字段
        entertainmentRepository.delete(entertainment);


        map.put("state","成功");
        map.put("msg","删除文章成功");

        return map;
    }

    public JSONObject getallentertainment(){

        JSONObject jsonObject = new JSONObject();

        Iterable<Entertainment> iterable = entertainmentRepository.findAll();
        Iterator<Entertainment> iterator = iterable.iterator();

        List<Entertainment> list = new ArrayList<>();
        int sum = 0;
        while (iterator.hasNext()){
            Entertainment entertainment = iterator.next();
            list.add(entertainment);
            sum = sum + 1;
        }
        int k = 1;
        for (int y = 0 ; y< sum; y++){
            for(int i = 0;i<sum;i++){
                for(Entertainment entertainment : list){
                    if (k == entertainment.getIndex() ){
                        String K = "";
                        K = k + "";
                        jsonObject.put(K,entertainment);
                    }
                }
                k = k + 1;
            }
        }

        return jsonObject;
    }
}
