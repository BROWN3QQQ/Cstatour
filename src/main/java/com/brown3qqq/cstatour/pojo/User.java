package com.brown3qqq.cstatour.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;


@Document(collection = "user")  //写死没关系吧,先写死，后面再写统一的配置文件
public class User {
    @Id
    @Indexed
    private String id;
    private String realname;
    private String telnum;
    private ArrayList<String> ordernumber;//订单编号队列

    public User(){

    }
    //无参构造器，适用于实例化接受类
    public User(String id, String realname, String telnum, ArrayList<String> ordernumber) {
        this.id = id;
        this.realname = realname;
        this.telnum = telnum;
        this.ordernumber = ordernumber;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getTelnum() {
        return telnum;
    }

    public void setTelnum(String telnum) {
        this.telnum = telnum;
    }

    public ArrayList<String> getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(ArrayList<String> ordernumber) {
        this.ordernumber = ordernumber;
    }
}
