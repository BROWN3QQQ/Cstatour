package com.brown3qqq.cstatour.controller;

import com.alibaba.fastjson.JSONObject;
import com.brown3qqq.cstatour.pojo.State.Statecode;
import com.brown3qqq.cstatour.service.entertainmentService;
import com.brown3qqq.cstatour.service.restaurantService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.brown3qqq.cstatour.auxiliary.response;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;


@RestController
@RequestMapping("/admin")
public class EntertainmentController {

    private static final Logger logger = LoggerFactory.getLogger(ColumnCategoryController.class);

    @Autowired
    entertainmentService entertainmentService;

    //添加娱乐
    @RequestMapping(value = "/addentertainment", method = RequestMethod.POST)
    public JSONObject add(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){

        try {
            Map<String, String> map = entertainmentService.add(jsonObject);

            if (map.containsKey("state")) {

                return new response(Statecode.SUCCESS).getJsonObject();

            } else {
                //model.addAttribute("msg", map.get("msg"));
                return new response(Statecode.FAIL).getJsonObject();

            }

        }catch (Exception e){
            logger.error("添加栏目异常:" + e.getMessage());
            return new response(Statecode.ABNORMAL).getJsonObject();

        }

    }

    //更新娱乐
    @RequestMapping(value = "/updateentertainment", method = RequestMethod.POST)
    public JSONObject update(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){

        try {
            Map<String, String> map = entertainmentService.update(jsonObject);

            if (map.containsKey("state")) {

                return new response(Statecode.SUCCESS).getJsonObject();

            } else {
                //model.addAttribute("msg", map.get("msg"));
                return new response(Statecode.FAIL).getJsonObject();

            }

        }catch (Exception e){
            logger.error("注册异常" + e.getMessage());
            return new response(Statecode.ABNORMAL).getJsonObject();

        }

    }

    //删除娱乐
    @RequestMapping(value = "/deleteentertainment", method = RequestMethod.POST)
    public String delete(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){
        JSONObject jsonObject1 = new JSONObject();

        try {
            Map<String, String> map = entertainmentService.delete(jsonObject);

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

    //获取全部娱乐
    @RequestMapping(value = "/getentertainment", method = RequestMethod.POST)
    public JSONObject get( HttpServletResponse httpServletResponse){
        try {
            return entertainmentService.getallentertainment();
        }catch (Exception e){
            return entertainmentService.getallentertainment();
        }

    }
}
