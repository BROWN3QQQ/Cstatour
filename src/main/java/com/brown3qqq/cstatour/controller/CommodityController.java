package com.brown3qqq.cstatour.controller;

import com.alibaba.fastjson.JSONObject;
import com.brown3qqq.cstatour.service.columnService;
import com.brown3qqq.cstatour.service.commodityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class CommodityController {

    private static final Logger logger = LoggerFactory.getLogger(ColumnCategoryController.class);

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    commodityService commodityService;


    //添加商品
    @RequestMapping(value = "/addcommodity", method = RequestMethod.POST)
    public String add(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){

        try {
            Map<String, String> map = commodityService.add(jsonObject);
            if (map.containsKey("state")) {

                return "添加栏目成功";
            } else {
                //model.addAttribute("msg", map.get("msg"));
                return "添加栏目失败" + map.get("msg") ;
            }

        }catch (Exception e){
            logger.error("添加栏目异常" + e.getMessage());
            return "添加栏目异常";
        }

    }

    //更新商品
    @RequestMapping(value = "/updatecommodity", method = RequestMethod.POST)
    public String update(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){

        try {
            Map<String, String> map = commodityService.update(jsonObject);

            if (map.containsKey("state")) {

                return "更新商品成功";
            } else {
                //model.addAttribute("msg", map.get("msg"));
                return "更新商品失败，" + map.get("msg") ;
            }

        }catch (Exception e){
            return "更新商品异常";
        }

    }

    //删除商品
    @RequestMapping(value = "/deletecommodity", method = RequestMethod.POST)
    public String delete(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){
        JSONObject jsonObject1 = new JSONObject();

        try {
            Map<String, String> map = commodityService.delete(jsonObject);

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

    //获取商品
    @RequestMapping(value = "/getcommodity", method = RequestMethod.POST)
    public JSONObject get(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){
        try {
            return commodityService.getallcommodity();

        }catch (Exception e){
            return commodityService.getallcommodity();
        }

    }

}
