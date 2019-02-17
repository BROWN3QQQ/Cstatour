package com.brown3qqq.cstatour.service;

import com.alibaba.fastjson.JSONObject;
import com.brown3qqq.cstatour.dao.BannerRepository;
import com.brown3qqq.cstatour.pojo.Banner;
import com.brown3qqq.cstatour.pojo.Hotel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @Classname BannerService
 * @Description TODO
 * @Date 2019/2/16 19:04
 * @Created by CQ
 */
@Service
public class BannerService {
    @Autowired
    BannerRepository bannerRepository;

    //添加新Baneer
    public Map<String,String > add(JSONObject jsonObject){

        Map<String,String> map = new HashMap<String, String >();

        //过滤字段是否为空
        if(StringUtils.isEmpty(jsonObject.getString("name")) ){
            map.put("msg", "首页滚动图片名不能为空");
            return map;
        }
        Banner banner = new Banner(jsonObject.getString("name"),jsonObject.getString("imgcontent"),jsonObject.getString("imgadres"),jsonObject.getIntValue("index"));
        bannerRepository.save(banner);
        map.put("state","成功");
        map.put("msg","添加首页滚动图片信息成功");
        return map;

    }

    //更新Baneer信息内容
    public Map<String,String > update(JSONObject jsonObject){

        Map<String,String> map = new HashMap<String, String >();

        //过滤字段是否为空
        if(StringUtils.isEmpty(jsonObject.getString("name")) ){
            map.put("msg", "首页滚动图片名不能为空");
            return map;
        }
        Banner banner = bannerRepository.findById(jsonObject.getString("id")).get();

        if (!bannerRepository.findById(jsonObject.getString("id")).isPresent()){
            map.put("msg","首页滚动图片内容信息不存在");
            return map;
        }else{

            Banner newbanner = new Banner(jsonObject.getString("name"),jsonObject.getString("imgcontent"),jsonObject.getString("imgadres"),jsonObject.getIntValue("index"));
            bannerRepository.save(newbanner);

            //更新字段
            map.put("state","成功");
            map.put("msg","更新首页滚动图片信息成功");
            return map;
        }
    }

    //删除Banner
    public Map<String,String > delete(JSONObject jsonObject){

        Map<String,String> map = new HashMap<String, String >();

        //过滤字段是否为空

        if(StringUtils.isEmpty(jsonObject.getString("id"))){
            map.put("msg", "首页滚动图片信息唯一id为空");
            return map;
        }
        Banner banner = bannerRepository.findById(jsonObject.getString("id")).get();

        if (!bannerRepository.findById(jsonObject.getString("id")).isPresent()){
            map.put("msg","首页滚动图片信息内容不存在");
            return map;
        }else{

            bannerRepository.delete(banner);

            map.put("state","成功");
            map.put("msg","删除首页滚动图片信息成功");

            return map;
        }

    }

    public JSONObject getallBanner(){

        JSONObject jsonObject = new JSONObject();

        Iterable<Banner> iterable = bannerRepository.findAll();
        Iterator<Banner> iterator = iterable.iterator();

        int sum = 1;
        while (iterator.hasNext()){

            Banner banner= iterator.next();
            String SUM="";
            SUM = sum + "";
            jsonObject.put(SUM,banner);
            ++sum;

        }

        return jsonObject;
    }
}
