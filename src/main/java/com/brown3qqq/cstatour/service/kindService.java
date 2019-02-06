package com.brown3qqq.cstatour.service;

import com.alibaba.fastjson.JSONObject;
import com.brown3qqq.cstatour.dao.ArticleRepository;
import com.brown3qqq.cstatour.dao.ColumnRepository;
import com.brown3qqq.cstatour.dao.Impl.ArticleRepositoryimpl;
import com.brown3qqq.cstatour.dao.Impl.ColumnRepositoryimpl;
import com.brown3qqq.cstatour.dao.KindRepository;
import com.brown3qqq.cstatour.pojo.Article;
import com.brown3qqq.cstatour.pojo.Column;
import com.brown3qqq.cstatour.pojo.Kind;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class kindService {


    @Autowired
    KindRepository kindRepository;
    //添加新类别
    public Map<String,String > add(JSONObject jsonObject){

        Map<String,String> map = new HashMap<String, String >();

        //过滤字段是否为空
        if(StringUtils.isEmpty(jsonObject.getString("name")) ){
            map.put("msg", "类名不能为空");
            return map;
        }
        if (jsonObject.getBoolean("state")){
            int sum = 0;
            ArrayList<Kind> sonkind = new ArrayList<Kind>();
            Kind kind = new Kind(jsonObject.getString("name"),jsonObject.getBoolean("state"),sonkind,jsonObject.getIntValue("index"),jsonObject.getString("imgadres"),null,sum,jsonObject.getString("content"))

            map.put("state","添加栏目成功");
            return map;
        }else{
            Kind moterkind = kindRepository.findById(jsonObject.getString("motherid")).get();
            if(StringUtils.isEmpty(jsonObject.getString("name")) ){
                map.put("msg", "母类不存在");
                return map;
            }
            moterkind.setSum((moterkind.getSum() + 1 ));

            Kind sonlkind  = new Kind()
        }


    }
    //更新类别
    public Map<String,String > update(JSONObject jsonObject){


        Map<String,String> map = new HashMap<String, String >();

        //过滤字段是否为空
        if(StringUtils.isEmpty(jsonObject.getString("name")) ){
            map.put("msg", "标题名不能为空");
            return map;
        }

        Article article = articleRepository.findById(jsonObject.getString("id")).get();

        if (article == null){
            map.put("msg","该文章不存在");
            return map;
        }

        //更新字段
        article.setName(jsonObject.getString("name"));
        if(StringUtils.isEmpty(jsonObject.getString("soncolumn")) ){
            //母类下文章
            Column mothercolumn = columnRepository.findById(jsonObject.getString("mothercolumn").toString()).get();
            article.setMonthercolumn(mothercolumn);
            article.setSoncolumn(null);
        }else{
            //母类加子类文章

            Column mothercolumn = columnRepository.findById(jsonObject.getString("mothercolumn").toString()).get();

            //这样写，很容易出毛病
            article.setMonthercolumn(mothercolumn);
            article.setSoncolumn(mothercolumn.getSon().get(jsonObject.getIntValue("sonmothercolumn")));
        }
        article.setImgadres(jsonObject.getString("imgadres"));
        article.setStick(jsonObject.getBoolean("stick"));
        article.setIndex(jsonObject.getIntValue("index"));
        article.setContent(jsonObject.getString("content"));

        articleRepository.save(article);
        map.put("state","成功");
        map.put("msg","更新文章成功");
        return map;
    }

    //删除类别
    public Map<String,String > delete(String id){

        Map<String,String> map = new HashMap<String, String >();

        //过滤字段是否为空

        if(StringUtils.isEmpty(id)){
            map.put("msg", "文章唯一id为空");
            return map;
        }

        Article article = articleRepository.findById(id).get();


        if (article == null){
            map.put("msg","栏目不存在");
            return map;
        }

        //删除字段
        articleRepository.delete(article);
        map.put("state","成功");
        map.put("msg","删除文章成功");

        return map;
    }

    //获取kind库里所有栏目
    public JSONObject getallarticle(){

        JSONObject jsonObject = new JSONObject();

        Iterable<Article> iterable = articleRepository.findAll();
        Iterator<Article> iterator = iterable.iterator();

        int sum = 1;
        while (iterator.hasNext()){
            Article article = iterator.next();
            String SUM="";
            SUM = sum + "";
            jsonObject.put(SUM,article);
            ++sum;
        }

        return jsonObject;
    }

}
