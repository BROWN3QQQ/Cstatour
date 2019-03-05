package com.brown3qqq.cstatour.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.brown3qqq.cstatour.dao.HotelRepository;
import com.brown3qqq.cstatour.dao.OrderRepository;
import com.brown3qqq.cstatour.pojo.CommdityInfo;
import com.brown3qqq.cstatour.pojo.Kind;
import com.brown3qqq.cstatour.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Classname oderService
 * @Description TODO
 * @Date 2019/3/2 10:58
 * @Created by CQ
 */
@Service
public class oderService {
    @Autowired
    OrderRepository orderRepository;

    //添加新类别
    public Map<String,String > add(JSONObject jsonObject){

        Map<String,String> map = new HashMap<String, String >();

        //过滤字段是否为空
        if(StringUtils.isEmpty(jsonObject.getString("name")) ){
            map.put("msg", "不能为空");
            return map;
        }


        try {
            JSONObject jsonObjectson = jsonObject.getJSONObject("commdityinfo");
            ArrayList<CommdityInfo> sonlist = new ArrayList<CommdityInfo>();
            String sumstr = jsonObject.getString("sum");
            int sum;
            sum = Integer.parseInt(sumstr);
            for (int i = 1;i<(sum+1);i++){
                  String I ="";
                  I = i + "";
               String name =  jsonObjectson.getJSONObject(I).getString("name");
               String moneystr = jsonObjectson.getJSONObject(I).getString("moneystr");
               BigDecimal money = new BigDecimal(jsonObject.getString("moneystr"));
               int thesum = jsonObjectson.getJSONObject(I).getIntValue("sum");
               String measurement = jsonObjectson.getJSONObject(I).getString("measurement");

               String imgadres = jsonObjectson.getJSONObject(I).getString("imgadres");
                System.out.println(imgadres);

               CommdityInfo commdityInfo = new CommdityInfo(I,name,imgadres,moneystr,money,thesum,measurement);
               sonlist.add(commdityInfo);

            }
            Order order = new Order(jsonObject.getString("name"),new Date(),jsonObject.getString("moneystr"),new BigDecimal(jsonObject.getString("moneystr")),"未付款",jsonObject.getString("getman"),jsonObject.getString("getmannum"),jsonObject.getString("getmanadres"),sonlist,null,null,null,null,null);

            orderRepository.save(order);
            map.put("msg","操作成功");
            map.put("state","成功");
            return map;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map;

    }
    //更新类别
    public Map<String,String > update(JSONObject jsonObject) {
        Map<String, String> map = new HashMap<String, String>();

        if (orderRepository.findById(jsonObject.getString("id")).isPresent()){
            Order order = orderRepository.findById(jsonObject.getString("id")).get();

            order.setStateinfo(jsonObject.getString("stateinfo"));
            order.setCzexpired(new Date());
            order.setCzman(jsonObject.getString("czman"));
            order.setIpadres(jsonObject.getString("ipadres"));
            order.setRemarks(jsonObject.getString("remarks"));

            order.setState(jsonObject.getString("state"));


            orderRepository.save(order);
            map.put("msg","操作成功");
            map.put("state","成功");
            return map;
        }else{
            map.put("msg","操作失败");
            return map;
        }

    }

    //删除类别
    public Map<String,String > delete(JSONObject jsonObject) {

        Map<String, String> map = new HashMap<String, String>();

        if (orderRepository.findById(jsonObject.getString("id")).isPresent()){
            orderRepository.deleteById(jsonObject.getString("id"));
            map.put("msg","操作成功");
            map.put("state","成功");
            return map;
        }else{
            map.put("msg","操作失败");
            return map;
        }
    }


    //获取kind库里所有栏目
    public JSONObject getallkind(){

        JSONObject jsonObject = new JSONObject();

        Iterable<Order> iterable = orderRepository.findAll();
        Iterator<Order> iterator = iterable.iterator();
        int sum = 1;
        while (iterator.hasNext()){
            Order order = iterator.next();
            String SUM="";
            SUM = sum + "";
            jsonObject.put(SUM,order);
            ++sum;
        }

        return jsonObject;
    }


}
