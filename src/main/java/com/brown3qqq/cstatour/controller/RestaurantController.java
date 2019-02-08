package com.brown3qqq.cstatour.controller;

import com.alibaba.fastjson.JSONObject;
import com.brown3qqq.cstatour.service.restaurantService;
import com.brown3qqq.cstatour.service.spotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class RestaurantController {
    private static final Logger logger = LoggerFactory.getLogger(ColumnCategoryController.class);


    @Autowired
    restaurantService restaurantService;
    //添加景点
    @RequestMapping(value = "/addrestaurant", method = RequestMethod.POST)
    public String add(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){

        try {
            Map<String, String> map = restaurantService.add(jsonObject);

            if (map.containsKey("state")) {

                return "添加栏目成功";
            } else {
                //model.addAttribute("msg", map.get("msg"));
                return "添加栏目失败" + map.get("msg") ;
            }

        }catch (Exception e){
            logger.error("添加栏目异常:" + e.getMessage());
            return "添加栏目异常";
        }

    }

    //更新景点
    @RequestMapping(value = "/updaterestaurant", method = RequestMethod.POST)
    public String update(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){

        try {
            Map<String, String> map = restaurantService.update(jsonObject);

            if (map.containsKey("state")) {

                return "更新栏目成功";
            } else {
                //model.addAttribute("msg", map.get("msg"));
                return "更新栏目失败，" + map.get("msg") ;
            }

        }catch (Exception e){
            logger.error("注册异常" + e.getMessage());
            return "更新栏目异常";
        }

    }

    //删除景点
    @RequestMapping(value = "/deleterestaurant", method = RequestMethod.POST)
    public String delete(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){
        JSONObject jsonObject1 = new JSONObject();

        try {
            Map<String, String> map = restaurantService.delete(jsonObject);

            if (map.containsKey("state")) {

                return "删除栏目成功";
            } else {
                //model.addAttribute("msg", map.get("msg"));
                return "删除栏目失败，" + map.get("msg") ;
            }

        }catch (Exception e){
            return "删除栏目异常";
        }


    }

    //获取全部景点
    @RequestMapping(value = "/getrestaurant", method = RequestMethod.POST)
    public JSONObject get(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){
        try {
            return restaurantService.getallarticle();
        }catch (Exception e){
            return restaurantService.getallarticle();
        }

    }
}
