package com.brown3qqq.cstatour.controller;

import com.alibaba.fastjson.JSONObject;
import com.brown3qqq.cstatour.auxiliary.response;
import com.brown3qqq.cstatour.pojo.State.Statecode;
import com.brown3qqq.cstatour.service.articleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.brown3qqq.cstatour.service.oderService;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;


/**
 * @Classname OrderController
 * @Description TODO
 * @Date 2019/3/2 15:56
 * @Created by CQ
 */
@RestController

public class OrderController {
    private static final Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    oderService oderService;

    //添加订单
    @RequestMapping(value = "/addorder", method = RequestMethod.POST)
    public JSONObject add(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){

        try {
            Map<String, String> map = oderService.add(jsonObject);

            if (map.containsKey("state")) {


                return new response(Statecode.SUCCESS).getJsonObject();
            } else {
                //model.addAttribute("msg", map.get("msg"));

                return new response(Statecode.FAIL).getJsonObject();
            }

        }catch (Exception e){
            logger.error("添加订单异常:" + e.getMessage());

            return new response(Statecode.ABNORMAL).getJsonObject();
        }

    }

    //更新栏目类内容
    @RequestMapping(value = "/admin/updateorder", method = RequestMethod.POST)
    public JSONObject update(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){

        try {
            Map<String, String> map = oderService.update(jsonObject);

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

    //删除栏目内容
    @RequestMapping(value = "/admin/deleteorder", method = RequestMethod.POST)
    public JSONObject delete(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){
        JSONObject jsonObject1 = new JSONObject();

        try {
            Map<String, String> map = oderService.delete(jsonObject);

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

    //获取全部栏目
    @RequestMapping(value = "/admin/getorder", method = RequestMethod.POST)
    public JSONObject get(){
        try {
            return oderService.getallkind();

        }catch (Exception e){
            return oderService.getallkind();
        }

    }


}
