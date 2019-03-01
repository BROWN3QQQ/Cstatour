package com.brown3qqq.cstatour.controller;

import com.alibaba.fastjson.JSONObject;
import com.brown3qqq.cstatour.auxiliary.response;
import com.brown3qqq.cstatour.pojo.State.Statecode;
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
    public JSONObject add(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){

        try {
            Map<String, String> map = bannerService.add(jsonObject);

            if (map.containsKey("state")) {


                return new response(Statecode.SUCCESS).getJsonObject();
            } else {
                //model.addAttribute("msg", map.get("msg"));

                return new response(Statecode.FAIL).getJsonObject();
            }

        }catch (Exception e){
            logger.error("添加新首页滚动图片信息异常:" + e.getMessage());

            return new response(Statecode.ABNORMAL).getJsonObject();
        }

    }

    //更新新首页滚动图片
    @RequestMapping(value = "/updatebanner", method = RequestMethod.POST)
    public JSONObject update(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){

        try {
            Map<String, String> map = bannerService.update(jsonObject);

            if (map.containsKey("state")) {
                return new response(Statecode.SUCCESS).getJsonObject();
            } else {

                return new response(Statecode.FAIL).getJsonObject();
            }

        }catch (Exception e){
            logger.error("更行新首页滚动图片信息异常" + e.getMessage());

            return new response(Statecode.ABNORMAL).getJsonObject();
        }

    }

    //删除新首页滚动图片
    @RequestMapping(value = "/deletebanner", method = RequestMethod.POST)
    public JSONObject delete(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){
        JSONObject jsonObject1 = new JSONObject();

        try {
            Map<String, String> map = bannerService.delete(jsonObject);

            if (map.containsKey("state")) {


                return new response(Statecode.SUCCESS).getJsonObject();
            } else {
                //model.addAttribute("msg", map.get("msg"));

                return new response(Statecode.FAIL).getJsonObject();
            }

        }catch (Exception e){

            return new response(Statecode.ABNORMAL).getJsonObject();
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
