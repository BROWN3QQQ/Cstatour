package com.brown3qqq.cstatour.service;

import com.alibaba.fastjson.JSONObject;
import com.brown3qqq.cstatour.dao.ArticleRepository;
import com.brown3qqq.cstatour.dao.ColumnRepository;
import com.brown3qqq.cstatour.dao.Impl.ArticleRepositoryimpl;
import com.brown3qqq.cstatour.dao.Impl.ColumnRepositoryimpl;
import com.brown3qqq.cstatour.pojo.Article;
import com.brown3qqq.cstatour.pojo.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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



        Article newarticle = new Article(jsonObject.getString("name"),);


            return map;

    }
    //更新文章内容
    public Map<String,String > update(String id,String cnname, String enname, boolean state,String mothercolumn,int sequence,boolean topnav,String columncontent){

        Map<String,String> map = new HashMap<String, String >();

        //过滤字段是否为空
        if(StringUtils.isEmpty(cnname) && StringUtils.isEmpty(enname)){
            map.put("msg", "栏目名不能为空");
            return map;
        }

        if(state){
            Column column = columnRepository.findById(id).get();

            if (column == null){
                map.put("msg","栏目不存在");
                return map;
            }

            //更新字段
            column.setCNname(cnname);
            column.setENname(enname);
            column.setState(state);
            column.setMotherColumn(mothercolumn);
            column.setSequence(sequence);
            column.setTopnav(topnav);
            column.setColumnContent(columncontent);
            column.setSon(column.getSon());

            columnRepository.save(column);
            map.put("state","成功");
            map.put("msg","更新栏目成功");

        }else{
            Column column = columnRepository.findById(mothercolumn).get();

            if (column == null){
                map.put("msg"," 母导航栏目不存在");
                return map;

            }
            ArrayList<Column>sonlist = column.getSon();

            for(Column newcolumn : sonlist){

                if (newcolumn.getId().equals(id)){
                    //更新字段
                    newcolumn.setCNname(cnname);
                    newcolumn.setENname(enname);
                    newcolumn.setState(state);
                    newcolumn.setMotherColumn(mothercolumn);
                    newcolumn.setSequence(sequence);
                    newcolumn.setTopnav(topnav);
                    newcolumn.setColumnContent(columncontent);
                }
            }

            column.setSon(sonlist);
            columnRepository.save(column);
            map.put("state","成功");
            map.put("msg","更新栏目成功");

        }

        return map;
    }

    //删除文章
    public Map<String,String > delete(String id,boolean state,String mothercolumn){

        Map<String,String> map = new HashMap<String, String >();

        //过滤字段是否为空

        if(state){
            Column column = columnRepository.findById(id).get();

            if (column == null){
                map.put("msg","栏目不存在");
                return map;
            }

            //删除字段
            columnRepository.delete(column);
            map.put("state","成功");
            map.put("msg","删除栏目成功");

            return map;
        }else{
            Column column = columnRepository.findById(mothercolumn).get();

            if (column == null){
                map.put("msg"," 母导航栏目不存在");
                return map;

            }
            ArrayList<Column>sonlist = column.getSon();

            for(Column newcolumn : sonlist){

                if (newcolumn.getId().equals(id)){
                    //删除字段
                    column.getSon().remove(((Integer.parseInt(id))-1));

                    columnRepository.save(column);
                    map.put("state","成功");
                    map.put("msg","删除栏目成功");
                    return map;
                }
            }


        }
        return map;
    }

    //获取article库里所有栏目
    public JSONObject getallcolumn(){

        JSONObject jsonObject = new JSONObject();

        Iterable<Column> iterable = columnRepository.findAll();
        Iterator<Column> iterator = iterable.iterator();

        int sum = 1;
        while (iterator.hasNext()){
            Column column = iterator.next();
            String SUM="";
            SUM = sum + "";
            jsonObject.put(SUM,column);
            ++sum;
        }

        return jsonObject;
    }

}








