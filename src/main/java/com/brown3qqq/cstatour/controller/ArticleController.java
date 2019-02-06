package com.brown3qqq.cstatour.controller;


import com.alibaba.fastjson.JSONObject;
import com.brown3qqq.cstatour.service.columnService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.brown3qqq.cstatour.service.articleService;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/admin")
public class ArticleController {
    private static final Logger logger = LoggerFactory.getLogger(ColumnCategoryController.class);

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    articleService articleService;




    //添加文章
    @RequestMapping(value = "/addarticle", method = RequestMethod.POST)
    public String add(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){

        try {
            Map<String, String> map = articleService.add(jsonObject);

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

    //更新栏目类内容
    @RequestMapping(value = "/updatearticle", method = RequestMethod.POST)
    public String update(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){

        try {
            Map<String, String> map = articleService.update(jsonObject);

            if (map.containsKey("state")) {

                return "更新栏目成功";
            } else {
                //model.addAttribute("msg", map.get("msg"));
                return "更新栏目失败，" + map.get("msg") ;
            }

        }catch (Exception e){
            return "更新栏目异常";
        }

    }

    //删除栏目内容
    @RequestMapping(value = "/deletearticle", method = RequestMethod.POST)
    public String delete(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){
        JSONObject jsonObject1 = new JSONObject();

        try {
            Map<String, String> map = articleService.delete(jsonObject.getString("id"));

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

    //获取全部栏目
    @RequestMapping(value = "/getarticle", method = RequestMethod.POST)
    public JSONObject get(@RequestBody JSONObject jsonObject, HttpServletResponse httpServletResponse){
        try {
            return articleService.getallarticle();
        }catch (Exception e){
            return articleService.getallarticle();
        }

    }
}
