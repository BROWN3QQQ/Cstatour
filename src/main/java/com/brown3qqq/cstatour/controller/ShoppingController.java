package com.brown3qqq.cstatour.controller;

import com.alibaba.fastjson.JSONObject;
import com.brown3qqq.cstatour.auxiliary.response;
import com.brown3qqq.cstatour.pojo.State.Statecode;
import com.brown3qqq.cstatour.service.entertainmentService;

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
 * @Classname ShoppingController
 * @Description TODO
 * @Date 2019/2/16 21:28
 * @Created by CQ
 */
public class ShoppingController {
    private static final Logger logger = LoggerFactory.getLogger(ShoppingController.class);


    @Autowired
    shoppingService shoppingService;

    //添加新购物
    @RequestMapping(value = "/addshopping", method = RequestMethod.POST)
    public JSONObject add(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){

        try {
            Map<String, String> map = shoppingService.add(jsonObject);

            if (map.containsKey("state")) {

                return new response(Statecode.SUCCESS).getJsonObject();

            } else {
                //model.addAttribute("msg", map.get("msg"));
                return new response(Statecode.FAIL).getJsonObject();

            }

        }catch (Exception e){
            logger.error("添加购物信息异常:" + e.getMessage());
            return new response(Statecode.ABNORMAL).getJsonObject();

        }

    }

    //更新购物
    @RequestMapping(value = "/updateshopping", method = RequestMethod.POST)
    public JSONObject update(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){

        try {
            Map<String, String> map = shoppingService.update(jsonObject);

            if (map.containsKey("state")) {

                return new response(Statecode.SUCCESS).getJsonObject();

            } else {
                //model.addAttribute("msg", map.get("msg"));
                return new response(Statecode.FAIL).getJsonObject();

            }

        }catch (Exception e){
            logger.error("更行购物信息异常" + e.getMessage());
            return new response(Statecode.ABNORMAL).getJsonObject();

        }

    }

    //删除购物
    @RequestMapping(value = "/deleteshopping", method = RequestMethod.POST)
    public JSONObject delete(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){
        JSONObject jsonObject1 = new JSONObject();

        try {
            Map<String, String> map = shoppingService.delete(jsonObject);

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

    //获取全部购物信息
    @RequestMapping(value = "/getshopping", method = RequestMethod.POST)
    public JSONObject get(){
        try {
            return shoppingService.getallshopping();

        }catch (Exception e){
            return shoppingService.getallshopping();
        }

    }
}
