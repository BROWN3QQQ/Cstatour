package com.brown3qqq.cstatour.service;

import com.alibaba.fastjson.JSONObject;
import com.brown3qqq.cstatour.dao.HotelRepository;
import com.brown3qqq.cstatour.dao.OrderRepository;
import com.brown3qqq.cstatour.pojo.Kind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Classname oderService
 * @Description TODO
 * @Date 2019/3/2 10:58
 * @Created by CQ
 */
public class oderService {
    @Autowired
    OrderRepository orderRepository;

    //添加新类别
    public Map<String,String > add(JSONObject jsonObject){

        Map<String,String> map = new HashMap<String, String >();

        //过滤字段是否为空
        if(StringUtils.isEmpty(jsonObject.getString("name")) ){
            map.put("msg", "类名不能为空");
            return map;
        }
        if (jsonObject.getBoolean("state")){
            int sum = 0;
            ArrayList<Kind> sonkind = new ArrayList<Kind>();
            Kind kind = new Kind(jsonObject.getString("name"),jsonObject.getBoolean("state"),sonkind,jsonObject.getIntValue("index"),jsonObject.getString("imgadres"),null,sum,jsonObject.getString("content"));

            kindRepository.save(kind);
            map.put("state","添加栏目成功");
            map.put("msg","添加栏目成功");

            return map;
        }else{
            Kind moterkind = kindRepository.findById(jsonObject.getString("motherid")).get();
            if(StringUtils.isEmpty(jsonObject.getString("name")) ){
                map.put("msg", "母类不存在");
                return map;
            }
            int sum = moterkind.getSum() + 1;
            moterkind.setSum(sum);
            String id = "" + sum;

            Kind sonlkind  = new Kind(id,jsonObject.getString("name"),jsonObject.getBoolean("state"),null,jsonObject.getIntValue("index"),jsonObject.getString("imgadres"),jsonObject.getString("motherid"),0,jsonObject.getString("content"));

            moterkind.getSonkind().add(sonlkind);

            kindRepository.save(moterkind);

            map.put("state","添加栏目成功");
            map.put("msg","添加栏目成功");

            return map;
        }



    }
    //更新类别
    public Map<String,String > update(JSONObject jsonObject) {


        Map<String, String> map = new HashMap<String, String>();

        //过滤字段是否为空
        if (StringUtils.isEmpty(jsonObject.getString("name"))) {
            map.put("msg", "类名不能为空");
            return map;
        }

        Kind motherkind = kindRepository.findById(jsonObject.getString("motherid")).get();

        if (motherkind == null) {
            map.put("msg", "母类不存在");
            return map;
        }

        ArrayList<Kind> sonlist = motherkind.getSonkind();

        for (Kind newkind : sonlist) {

            if (newkind.getId().equals(jsonObject.getString("id"))) {
                //更新字段
                newkind.setName(jsonObject.getString("name"));
                newkind.setContent(jsonObject.getString("content"));
                newkind.setIndex(jsonObject.getIntValue("index"));
                newkind.setImgadres(jsonObject.getString("imgadres"));
            }
        }

        motherkind.setSonkind(sonlist);
        kindRepository.save(motherkind);

        map.put("state", "成功");
        map.put("msg", "更新栏目成功");

        return map;
    }

    //删除类别
    public Map<String,String > delete(JSONObject jsonObject) {

        Map<String, String> map = new HashMap<String, String>();

        Kind motherkind = kindRepository.findById(jsonObject.getString("motherid")).get();

        if (motherkind == null) {
            map.put("msg", " 母类不存在");
            return map;

        }
        ArrayList<Kind> sonlist = motherkind.getSonkind();

        for (Kind newkind : sonlist) {

            if (newkind.getId().equals(jsonObject.getString("id"))) {
                //更新字段
                motherkind.getSonkind().remove(newkind);
                kindRepository.save(motherkind);
                map.put("state", "成功");
                map.put("msg", "删除栏目成功");
                return map;
            }
        }
        return map;
    }


    //获取kind库里所有栏目
    public JSONObject getallkind(){

        JSONObject jsonObject = new JSONObject();

        Iterable<Kind> iterable = kindRepository.findAll();
        Iterator<Kind> iterator = iterable.iterator();
        int sum = 1;
        while (iterator.hasNext()){
            Kind kind = iterator.next();
            String SUM="";
            SUM = sum + "";
            jsonObject.put(SUM,kind);
            ++sum;
        }

        return jsonObject;
    }


}
