package com.brown3qqq.cstatour.controller;

import com.alibaba.fastjson.JSONObject;
import com.brown3qqq.cstatour.service.hotelService;
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
 * @Classname HotelController
 * @Description TODO
 * @Date 2019/2/16 22:20
 * @Created by CQ
 */
public class HotelController {
    private static final Logger logger = LoggerFactory.getLogger(HotelController.class);


    @Autowired
    hotelService hotelService;
    //添加新酒店
    @RequestMapping(value = "/addhotel", method = RequestMethod.POST)
    public String add(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){

        try {
            Map<String, String> map = hotelService.add(jsonObject);

            if (map.containsKey("state")) {

                return "添加酒店信息成功";
            } else {
                //model.addAttribute("msg", map.get("msg"));
                return "添加酒店信息失败" + map.get("msg") ;
            }

        }catch (Exception e){
            logger.error("添加酒店信息异常:" + e.getMessage());
            return "添加酒店信息异常";
        }

    }

    //更新酒店信息
    @RequestMapping(value = "/updatehotel", method = RequestMethod.POST)
    public String update(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){

        try {
            Map<String, String> map = hotelService.update(jsonObject);

            if (map.containsKey("state")) {

                return "更新酒店信息成功";
            } else {

                return "更新酒店信息失败，" + map.get("msg") ;
            }

        }catch (Exception e){
            logger.error("更行酒店信息异常" + e.getMessage());
            return "更新酒店信息异常";
        }

    }

    //删除酒店信息
    @RequestMapping(value = "/deletehotel", method = RequestMethod.POST)
    public String delete(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){
        JSONObject jsonObject1 = new JSONObject();

        try {
            Map<String, String> map = hotelService.delete(jsonObject);

            if (map.containsKey("state")) {

                return "删除酒店信息成功";
            } else {
                //model.addAttribute("msg", map.get("msg"));
                return "删除酒店信息失败，" + map.get("msg") ;
            }

        }catch (Exception e){
            return "删除酒店信息异常";
        }


    }

    //获取全部购物酒店信息
    @RequestMapping(value = "/gethotel", method = RequestMethod.POST)
    public JSONObject get(){
        try {
            return hotelService.getallHotel();

        }catch (Exception e){
            return hotelService.getallHotel();
        }

    }
}
