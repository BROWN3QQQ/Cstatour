package com.brown3qqq.cstatour.service;

import com.alibaba.fastjson.JSONObject;
import com.brown3qqq.cstatour.dao.HotelRepository;
import com.brown3qqq.cstatour.pojo.Column;
import com.brown3qqq.cstatour.pojo.Hotel;
import com.brown3qqq.cstatour.pojo.Travel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @Classname hotelService
 * @Description
 * @Date 2019/2/16 18:27
 * @Created by CQ
 */
@Service
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
        Hotel hotel = new Hotel(jsonObject.getString("name"),jsonObject.getString("imgadres"),jsonObject.getString("telnum"),jsonObject.getString("hoteladdress"),jsonObject.getString("motherspot"),jsonObject.getIntValue("index"),jsonObject.getBoolean("hot"),jsonObject.getBoolean("useful"),jsonObject.getString("hotelcontent"));

        hotelRepository.save(hotel);

        map.put("state","成功");
        map.put("msg","添加酒店信息成功");
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
        Hotel hotel = hotelRepository.findById(jsonObject.getString("id")).get();

        if (!hotelRepository.findById(jsonObject.getString("id")).isPresent()){
            map.put("msg","酒店信息内容不存在");
            return map;
        }else{

            int old = hotelRepository.findById(jsonObject.getString("id")).get().getIndex();
            int newindex = jsonObject.getIntValue("index");
                if (old != newindex){
                    if (old >newindex){
                        Iterable<Hotel> iterable = hotelRepository.findAll();
                        Iterator<Hotel> iterator = iterable.iterator();
                        while (iterator.hasNext()){
                            Hotel newhotel = iterator.next();
                            if (newhotel.getIndex() >= newindex && newhotel.getIndex() < old){
                                newhotel.setIndex((newhotel.getIndex()+ 1));
                            }
                            hotelRepository.save(newhotel);
                        }

                    }else {

                        Iterable<Hotel> iterable = hotelRepository.findAll();
                        Iterator<Hotel> iterator = iterable.iterator();
                        while (iterator.hasNext()){
                            Hotel newhotel = iterator.next();
                            if (newhotel.getIndex() <= newindex && newhotel.getIndex() > old){
                                newhotel.setIndex((newhotel.getIndex()- 1));
                            }
                            hotelRepository.save(newhotel);
                        }
                    }
                }
            Hotel newhotel = new Hotel(jsonObject.getString("id"),jsonObject.getString("name"),jsonObject.getString("imgadres"),jsonObject.getString("telnum"),jsonObject.getString("hoteladdress"),jsonObject.getString("motherspot"),jsonObject.getIntValue("index"),jsonObject.getBoolean("hot"),jsonObject.getBoolean("useful"),jsonObject.getString("hotelcontent"));

            hotelRepository.save(newhotel);

            //更新字段
            map.put("state","成功");
            map.put("msg","更新酒店信息成功");
            return map;
        }
    }

    //删除酒店容内容信息
    public Map<String,String > delete(JSONObject jsonObject){

        Map<String,String> map = new HashMap<String, String >();

        //过滤字段是否为空

        if(StringUtils.isEmpty(jsonObject.getString("id"))){
            map.put("msg", "酒店信息唯一id为空");
            return map;
        }
        Hotel hotel = hotelRepository.findById(jsonObject.getString("id")).get();

        if (!hotelRepository.findById(jsonObject.getString("id")).isPresent()){
            map.put("msg","酒店信息内容不存在");
            return map;
        }else{

            hotelRepository.delete(hotel);
            map.put("state","成功");
            map.put("msg","删除酒店信息成功");

            return map;
        }

    }

    public JSONObject getallHotel(){

        JSONObject jsonObject = new JSONObject();

        Iterable<Hotel> iterable = hotelRepository.findAll();
        Iterator<Hotel> iterator = iterable.iterator();

        List<Hotel> list = new ArrayList<>();
        int sum = 0;

        while (iterator.hasNext()){

            Hotel hotel = iterator.next();
            list.add(hotel);
            sum = sum + 1;
        }
        int k = 1;
        for (int u = 0; u < (sum + 1);u++){
            for(int i = 0;i<(sum + 1);i++){
                for(Hotel hotel : list){
                    if (k == hotel.getIndex() ){
                        String K = "";
                        K = k + "";
                        jsonObject.put(K,hotel);
                    }
                }
                k = k + 1;
            }
        }


        return jsonObject;
    }

}
