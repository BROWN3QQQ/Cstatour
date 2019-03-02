package com.brown3qqq.cstatour.service;

import com.alibaba.fastjson.JSONObject;
import com.brown3qqq.cstatour.dao.ArticleRepository;
import com.brown3qqq.cstatour.dao.ColumnRepository;
import com.brown3qqq.cstatour.dao.Impl.ArticleRepositoryimpl;
import com.brown3qqq.cstatour.dao.Impl.ColumnRepositoryimpl;
import com.brown3qqq.cstatour.pojo.Article;
import com.brown3qqq.cstatour.pojo.Column;
import com.brown3qqq.cstatour.pojo.Spot;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
public class articleService {

    @Autowired
    ArticleRepository articleRepository;
    @Autowired
    ArticleRepositoryimpl articleRepositoryimpl;

    @Autowired
    ColumnRepository columnRepository;
    @Autowired
    ColumnRepositoryimpl columnRepositoryimpl;

    //添加新文章
    public Map<String,String > add(JSONObject jsonObject){

        Map<String,String> map = new HashMap<String, String >();

        //过滤字段是否为空
        if(StringUtils.isEmpty(jsonObject.getString("name")) ){
            map.put("msg", "标题名不能为空");
            return map;
        }

        Article article = articleRepositoryimpl.get(jsonObject.getString("name"));

        if (article != null){
            map.put("msg","该文章已存在");
            return map;
        }

        if(StringUtils.isEmpty(jsonObject.getString("soncolumn")) ){
            //母类下文章
            boolean mothercolumnboolean = columnRepository.findById(jsonObject.getString("mothercolumn")).isPresent();

            if (mothercolumnboolean){
                Column mothercolumn = columnRepository.findById(jsonObject.getString("mothercolumn")).get();
                Article newarticle = new Article(jsonObject.getString("name"),mothercolumn,null,jsonObject.getString("imgadres"),jsonObject.getBoolean("stick"),jsonObject.getIntValue("index"),jsonObject.getString("content"));

                articleRepository.save(newarticle);
                map.put("state","成功");
                map.put("msg","添加文章成功");
                return map;
            }else {

                map.put("msg","母栏目不存在");
                return map;
            }


        }else{
            //母类加子类文章
            boolean mothercolumnboolean = columnRepository.findById(jsonObject.getString("mothercolumn")).isPresent();
            if (mothercolumnboolean){
                Column mothercolumn = columnRepository.findById(jsonObject.getString("mothercolumn")).get();

                //代码冗余了。。。
                ArrayList<Column>sonlist = mothercolumn.getSon();
                Column soncolumn = new Column();
                for(Column newcolumn : sonlist){

                    if (newcolumn.getId().equals(jsonObject.getString("soncolumn"))){

                        soncolumn = newcolumn;
                        Article newarticle = new Article(jsonObject.getString("name"),mothercolumn,soncolumn,jsonObject.getString("imgadres"),jsonObject.getBoolean("stick"),jsonObject.getIntValue("index"),jsonObject.getString("content"));

                        articleRepository.save(newarticle);
                        map.put("state","成功");
                        map.put("msg","添加文章成功");
                        return map;
                    }
                }
                return map;
            }else {

                map.put("msg","母栏目不存在");
                return map;
            }
        }
    }
    //更新文章内容
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
            try{
                ArrayList<Column>sonlist = columnRepository.findById(jsonObject.getString("mothercolumn")).get().getSon();
                String id = jsonObject.getString("soncolumn");

                for(Column newcolumn : sonlist){

                    if (newcolumn.getId().equals(id)){

                        article.setSoncolumn(newcolumn);
                    }
                }
            }catch (Exception e){

            }




        article.setImgadres(jsonObject.getString("imgadres"));
        article.setStick(jsonObject.getBoolean("stick"));

        int old = article.getIndex();
        int newindex = jsonObject.getIntValue("index");

        if (old != newindex) {
            if (old > newindex) {
                Iterable<Article> iterable = articleRepository.findAll();
                Iterator<Article> iterator = iterable.iterator();

                while (iterator.hasNext()){
                    Article newarticle = iterator.next();
                    if (newarticle.getIndex() >= newindex && newarticle.getIndex() < old){
                        newarticle.setIndex((newarticle.getIndex() + 1));
                    }
                    articleRepository.save(newarticle);
                }

            } else {

                Iterable<Article> iterable = articleRepository.findAll();
                Iterator<Article> iterator = iterable.iterator();

                while (iterator.hasNext()){
                    Article newarticle = iterator.next();
                    if (newarticle.getIndex() <= newindex && newarticle.getIndex() > old){
                        newarticle.setIndex((newarticle.getIndex() - 1));
                    }
                    articleRepository.save(newarticle);
                }
            }
        }
        article.setIndex(jsonObject.getIntValue("index"));
        article.setContent(jsonObject.getString("content"));

        articleRepository.save(article);
        map.put("state","成功");
        map.put("msg","更新文章成功");
        return map;
    }

    //删除文章
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

    //获取article库里所有栏目
    public JSONObject getallarticle(){

        JSONObject jsonObject = new JSONObject();

        Iterable<Article> iterable = articleRepository.findAll();
        Iterator<Article> iterator = iterable.iterator();

        List<Article> list = new ArrayList<>();
        int sum = 0;
        while (iterator.hasNext()){
            Article article = iterator.next();
          list.add(article);
          sum = sum + 1;
        }

        int k = 1;
        //我会回来重构这段狗b的代码的，人为扩大取值，会很慢，但是三行代码解决，这就是之前不用脑子想好咋写代码的后果
        for(Article newarticle : list){
            for(Article nnewarticle : list){
                for(Article article : list){
                    if (k == article.getIndex() ){
                        String K = "";
                        K = k + "";
                        jsonObject.put(K,article);
                    }
                }
                k = k + 1;
            }
        }


        return jsonObject;
    }

}








