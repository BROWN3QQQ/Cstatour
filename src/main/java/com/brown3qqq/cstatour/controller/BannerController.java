package com.brown3qqq.cstatour.controller;

import com.alibaba.fastjson.JSONObject;
import com.brown3qqq.cstatour.service.BannerService;
import com.brown3qqq.cstatour.service.shoppingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Classname BannerController
 * @Description TODO
 * @Date 2019/2/16 22:55
 * @Created by CQ
 */
public class BannerController {
    private static final Logger logger = LoggerFactory.getLogger(BannerController.class);

    @Autowired
    BannerService bannerService;

    //添加新首页滚动图片
    @RequestMapping(value = "/addbanner", method = RequestMethod.POST)
    public String add(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){

        try {
            Map<String, String> map = bannerService.add(jsonObject);

            if (map.containsKey("state")) {

                return "添加新首页滚动图片信息成功";
            } else {
                //model.addAttribute("msg", map.get("msg"));
                return "添加新首页滚动图片信息失败" + map.get("msg") ;
            }

        }catch (Exception e){
            logger.error("添加新首页滚动图片信息异常:" + e.getMessage());
            return "添加新首页滚动图片信息异常";
        }

    }

    //更新新首页滚动图片
    @RequestMapping(value = "/updatebanner", method = RequestMethod.POST)
    public String update(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){

        try {
            Map<String, String> map = bannerService.update(jsonObject);

            if (map.containsKey("state")) {

                return "更新新首页滚动图片信息成功";
            } else {

                return "更新新首页滚动图片信息失败，" + map.get("msg") ;
            }

        }catch (Exception e){
            logger.error("更行新首页滚动图片信息异常" + e.getMessage());
            return "更新新首页滚动图片信息异常";
        }

    }

    //删除新首页滚动图片
    @RequestMapping(value = "/deletebanner", method = RequestMethod.POST)
    public String delete(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){
        JSONObject jsonObject1 = new JSONObject();

        try {
            Map<String, String> map = bannerService.delete(jsonObject);

            if (map.containsKey("state")) {

                return "删除新首页滚动图片信息成功";
            } else {
                //model.addAttribute("msg", map.get("msg"));
                return "删除新首页滚动图片信息失败，" + map.get("msg") ;
            }

        }catch (Exception e){
            return "删除新首页滚动图片信息异常";
        }


    }

    //获取全部新首页滚动图片
    @RequestMapping(value = "/getbanner", method = RequestMethod.POST)
    public JSONObject get(){
        try {
            return bannerService.getallBanner();

        }catch (Exception e){
            return bannerService.getallBanner();
        }

    }
}
