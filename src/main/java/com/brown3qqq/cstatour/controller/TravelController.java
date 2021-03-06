package com.brown3qqq.cstatour.controller;

import com.alibaba.fastjson.JSONObject;
import com.brown3qqq.cstatour.auxiliary.response;
import com.brown3qqq.cstatour.pojo.State.Statecode;
import com.brown3qqq.cstatour.service.shoppingService;
import com.brown3qqq.cstatour.service.travelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @Classname TravelController
 * @Description TODO
 * @Date 2019/2/16 21:44
 * @Created by CQ
 */
public class TravelController {
    private static final Logger logger = LoggerFactory.getLogger(TravelController.class);


    @Autowired
    travelService travelService;

    //添加新旅游
    @RequestMapping(value = "/addtravel", method = RequestMethod.POST)
    public JSONObject add(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){

        try {
            Map<String, String> map = travelService.add(jsonObject);

            if (map.containsKey("state")) {

                return new response(Statecode.SUCCESS).getJsonObject();

            } else {
                //model.addAttribute("msg", map.get("msg"));
                return new response(Statecode.FAIL).getJsonObject();

            }

        }catch (Exception e){
            logger.error("添加旅游信息异常:" + e.getMessage());
            return new response(Statecode.ABNORMAL).getJsonObject();
        }

    }

    //更新旅游
    @RequestMapping(value = "/updatetravel", method = RequestMethod.POST)
    public JSONObject update(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){

        try {
            Map<String, String> map = travelService.update(jsonObject);

            if (map.containsKey("state")) {

                return new response(Statecode.SUCCESS).getJsonObject();

            } else {
                //model.addAttribute("msg", map.get("msg"));
                return new response(Statecode.FAIL).getJsonObject();

            }

        }catch (Exception e){
            logger.error("更行旅游信息异常" + e.getMessage());
            return new response(Statecode.ABNORMAL).getJsonObject();

        }

    }

    //删除旅游
    @RequestMapping(value = "/deletetravel", method = RequestMethod.POST)
    public JSONObject delete(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){
        JSONObject jsonObject1 = new JSONObject();

        try {
            Map<String, String> map = travelService.delete(jsonObject);

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

    //获取全部旅游信息
    @RequestMapping(value = "/gettravel", method = RequestMethod.POST)
    public JSONObject get(){
        try {
            return travelService.getalltravel();

        }catch (Exception e){
            return travelService.getalltravel();

        }

    }
}
