package com.brown3qqq.cstatour.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Document(collection = "order")
public class Order {
    //订单类
    @Id
    @Indexed
    private String id;
    private String name;

    //订单创建时间
    private Date expired;
    //图片路径
    private String imgadres1;
    private String imgadres2;
    private String imgadres3;


    private String moneystr;
    private BigDecimal money;
    //订单状态
    private String state;

    private String getman;
    private String getmannum;
    private String getmanadres;

    //产品信息列表
    List<CommdityInfo> list = new ArrayList<>();

    //订单操作记录
    private String stateinfo;
    private Date czexpired;
    private String czman;
    private String ipadres;
    private String remarks;

    public Order(){}

    public Order(String name, Date expired, String imgadres1, String imgadres2, String imgadres3, String moneystr, BigDecimal money, String state, String getman, String getmannum, String getmanadres, List<CommdityInfo> list, String stateinfo, Date czexpired, String czman, String ipadres, String remarks) {
        this.name = name;
        this.expired = expired;
        this.imgadres1 = imgadres1;
        this.imgadres2 = imgadres2;
        this.imgadres3 = imgadres3;
        this.moneystr = moneystr;
        this.money = money;
        this.state = state;
        this.getman = getman;
        this.getmannum = getmannum;
        this.getmanadres = getmanadres;
        this.list = list;
        this.stateinfo = stateinfo;
        this.czexpired = czexpired;
        this.czman = czman;
        this.ipadres = ipadres;
        this.remarks = remarks;
    }

    public Order(String id, String name, Date expired, String imgadres1, String imgadres2, String imgadres3, String moneystr, BigDecimal money, String state, String getman, String getmannum, String getmanadres, List<CommdityInfo> list, String stateinfo, Date czexpired, String czman, String ipadres, String remarks) {
        this.id = id;
        this.name = name;
        this.expired = expired;
        this.imgadres1 = imgadres1;
        this.imgadres2 = imgadres2;
        this.imgadres3 = imgadres3;
        this.moneystr = moneystr;
        this.money = money;
        this.state = state;
        this.getman = getman;
        this.getmannum = getmannum;
        this.getmanadres = getmanadres;
        this.list = list;
        this.stateinfo = stateinfo;
        this.czexpired = czexpired;
        this.czman = czman;
        this.ipadres = ipadres;
        this.remarks = remarks;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getExpired() {
        return expired;
    }

    public void setExpired(Date expired) {
        this.expired = expired;
    }

    public String getImgadres1() {
        return imgadres1;
    }

    public void setImgadres1(String imgadres1) {
        this.imgadres1 = imgadres1;
    }

    public String getImgadres2() {
        return imgadres2;
    }

    public void setImgadres2(String imgadres2) {
        this.imgadres2 = imgadres2;
    }

    public String getImgadres3() {
        return imgadres3;
    }

    public void setImgadres3(String imgadres3) {
        this.imgadres3 = imgadres3;
    }

    public String getMoneystr() {
        return moneystr;
    }

    public void setMoneystr(String moneystr) {
        this.moneystr = moneystr;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getGetman() {
        return getman;
    }

    public void setGetman(String getman) {
        this.getman = getman;
    }

    public String getGetmannum() {
        return getmannum;
    }

    public void setGetmannum(String getmannum) {
        this.getmannum = getmannum;
    }

    public String getGetmanadres() {
        return getmanadres;
    }

    public void setGetmanadres(String getmanadres) {
        this.getmanadres = getmanadres;
    }

    public List<CommdityInfo> getList() {
        return list;
    }

    public void setList(List<CommdityInfo> list) {
        this.list = list;
    }

    public String getStateinfo() {
        return stateinfo;
    }

    public void setStateinfo(String stateinfo) {
        this.stateinfo = stateinfo;
    }

    public Date getCzexpired() {
        return czexpired;
    }

    public void setCzexpired(Date czexpired) {
        this.czexpired = czexpired;
    }

    public String getCzman() {
        return czman;
    }

    public void setCzman(String czman) {
        this.czman = czman;
    }

    public String getIpadres() {
        return ipadres;
    }

    public void setIpadres(String ipadres) {
        this.ipadres = ipadres;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
