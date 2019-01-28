package com.brown3qqq.cstatour.service;

import com.alibaba.fastjson.JSONObject;
import com.brown3qqq.cstatour.dao.ColumnCustomerRepository;
import com.brown3qqq.cstatour.dao.ColumnRepository;
import com.brown3qqq.cstatour.dao.Impl.ColumnRepositoryimpl;
import com.brown3qqq.cstatour.pojo.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

@Service
public class columnService {

    @Autowired
    ColumnRepository columnRepository;

    @Autowired
    ColumnRepositoryimpl columnRepositoryimpl;

    //添加新栏目
    public Map<String,String > add(String cnname, String enname, boolean state,String mothercolumn,int sequence,boolean topnav,String columncontent){
        //这样设置参数的原因，是我认为这样能降低耦合
        Map<String,String> map = new HashMap<String, String >();

        //过滤字段是否为空
        if(StringUtils.isEmpty(cnname) || StringUtils.isEmpty(enname)){
            map.put("msg", "栏目名不能为空");
            return map;
        }
        if (state){
            Column column = columnRepositoryimpl.get(cnname);

            if (column != null){
                map.put("msg","栏目已存在");
                return map;

            }
            ArrayList<Column> son = new ArrayList();
            Column newcolumn = new Column(cnname,enname,state,mothercolumn,son,sequence,topnav,columncontent);

            columnRepository.save(newcolumn);
            map.put("state","成功");
            map.put("msg","添加栏目成功");

            return map;

        }else{

            Column column = columnRepository.findById(mothercolumn).get();

            if (column == null){
                map.put("msg"," 母导航栏目不存在");
                return map;

            }
            ArrayList<Column> son = new ArrayList();
//            String id = new String();
            int id = column.getSon().size() + 1;
            String ID = "";
            ID = id + "";

            Column newcolumn = new Column(ID,cnname,enname,state,mothercolumn,son,sequence,topnav,columncontent);
            column.getSon().add(newcolumn);

            columnRepository.save(column);
            map.put("state","成功");
            map.put("msg","添加子栏目成功");

            return map;

        }

    }

    //更新栏目内容
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

    //删除栏目内容
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

    //获取column库里所有栏目
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
