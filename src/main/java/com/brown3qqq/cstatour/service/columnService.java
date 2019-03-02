package com.brown3qqq.cstatour.service;

import com.alibaba.fastjson.JSONObject;
import com.brown3qqq.cstatour.dao.ColumnCustomerRepository;
import com.brown3qqq.cstatour.dao.ColumnRepository;
import com.brown3qqq.cstatour.dao.Impl.ColumnRepositoryimpl;
import com.brown3qqq.cstatour.pojo.Column;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class columnService {

    @Autowired
    ColumnRepository columnRepository;

    @Autowired
    ColumnRepositoryimpl columnRepositoryimpl;

    //添加新栏目
    public Map<String,String > add(String cnname, String enname, boolean state,String path,String imgadres,String mothercolumn,int sequence,boolean topnav,String columncontent){
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
            Column newcolumn = new Column(cnname,enname,state,path,imgadres,mothercolumn,son,sequence,topnav,columncontent);

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

            UUID uuid = UUID.randomUUID();
            String ID = "";
            ID = uuid.toString() + "";

            Column newcolumn = new Column(ID,cnname,enname,state,path,imgadres,mothercolumn,son,sequence,topnav,columncontent);
            column.getSon().add(newcolumn);

            columnRepository.save(column);
            map.put("state","成功");
            map.put("msg","添加子栏目成功");

            return map;

        }

    }

    //更新栏目内容
    public Map<String,String > update(String id,String cnname, String enname, boolean state,String path,String imgadres,String mothercolumn,int sequence,boolean topnav,String columncontent){

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
            if(sequence != column.getSequence()){
                int old = column.getSequence();
                if(old > sequence){

                    Iterable<Column> iterable = columnRepository.findAll();
                    Iterator<Column> iterator = iterable.iterator();

                    while (iterator.hasNext()){
                        Column mocolumn = iterator.next();
                        if (mocolumn.getSequence() >= sequence && mocolumn.getSequence() < old){
                            mocolumn.setSequence((mocolumn.getSequence() + 1));
                        }
                        columnRepository.save(mocolumn);
                    }
                }else {

                    Iterable<Column> iterable = columnRepository.findAll();
                    Iterator<Column> iterator = iterable.iterator();

                    while (iterator.hasNext()){
                        Column mocolumn = iterator.next();
                        if (mocolumn.getSequence() <= sequence && mocolumn.getSequence() > old){
                            mocolumn.setSequence((mocolumn.getSequence() -1));
                        }
                        columnRepository.save(mocolumn);
                    }
                }
            }
            column.setSequence(sequence);
            column.setTopnav(topnav);
            column.setColumnContent(columncontent);
            column.setSon(column.getSon());
            column.setPath(path);
            column.setImgadres(imgadres);

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
                        if(sequence != newcolumn.getSequence()){
                            int old = newcolumn.getSequence();
                            if(old > sequence){
                                for(Column rescolumn : sonlist){
                                    if(rescolumn.getSequence() >= sequence && rescolumn.getSequence() < old  ){
                                        rescolumn.setSequence((rescolumn.getSequence() + 1));
                                    }
                                }
                            }else {
                                for(Column rescolumn : sonlist){
                                    if(rescolumn.getSequence() <= sequence && rescolumn.getSequence() > old  ){
                                        rescolumn.setSequence((rescolumn.getSequence() - 1));
                                    }
                                }
                            }
                        }
                    newcolumn.setSequence(sequence);
                    newcolumn.setTopnav(topnav);
                    newcolumn.setColumnContent(columncontent);

                    newcolumn.setPath(path);
                    newcolumn.setImgadres(imgadres);
                }
            }


//            ArrayList<Column> newsonlist =
//            ArrayList<Column>newsonlist = sonlist.stream().sorted((x,y)-> x.getSequence()- y.getSequence()).collect(Collectors)

            //我有点眼睛疼，下次重构代码,这些代码我写的跟屎一样
            int a = 1 ;
            ArrayList<Column> newsonlist = new ArrayList<>();
            for(Column newnewcolumn : sonlist){
                for(Column newcolumn : sonlist){
                    if (newcolumn.getSequence() == a){
                        newsonlist.add(newcolumn);
                    }

                }
                a = a + 1;
            }

            column.setSon(newsonlist);
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
                    column.getSon().remove(newcolumn);

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

        List<Column> list = new ArrayList<>();
        int sum = 0 ;
        while (iterator.hasNext()){

            Column column = iterator.next();
            list.add(column);
            String SUM = "";
            SUM = sum + "";

            sum = sum + 1;
        }

        int k = 0;
        for (int u = 0;u<sum;u++){
            for(int i = 0;i<(sum + 1);i++){
                for(Column column : list){
                    if (k == column.getSequence() ){
                        String K = "";
                        K = k + "";
                        jsonObject.put(K,column);
                    }
                }
                k = k + 1;
            }
        }



        return jsonObject;
    }
}
