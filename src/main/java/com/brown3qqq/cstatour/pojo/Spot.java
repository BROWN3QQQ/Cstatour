package com.brown3qqq.cstatour.pojo;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

//景点类
@Document(collection = "spot")
public class Spot {
    @Id
    @Indexed
    private String id;
    private String name;
    //图片路径
    private String imgadres;
    private String motherspot;
    private String spendtime;
    private String moneystr;
    private BigDecimal money;
    private String moneyintroduce;

    private String kind;
    private String timeinterval; //时段
    private String level;

    private int index;

    private boolean hot;
    private boolean useful;

    private String introduce;
    private String remarks;  //备注

    public Spot(){}
    public Spot(String id, String name, String imgadres, String motherspot, String spendtime, String moneystr, BigDecimal money, String moneyintroduce, String kind, String timeinterval, String level, int index, boolean hot, boolean useful, String introduce, String remarks) {
        this.id = id;
        this.name = name;
        this.imgadres = imgadres;
        this.motherspot = motherspot;
        this.spendtime = spendtime;
        this.moneystr = moneystr;
        this.money = money;
        this.moneyintroduce = moneyintroduce;
        this.kind = kind;
        this.timeinterval = timeinterval;
        this.level = level;
        this.index = index;
        this.hot = hot;
        this.useful = useful;
        this.introduce = introduce;
        this.remarks = remarks;
    }

    public Spot(String name, String imgadres, String motherspot, String spendtime, String moneystr, BigDecimal money, String moneyintroduce, String kind, String timeinterval, String level, int index, boolean hot, boolean useful, String introduce, String remarks) {
        this.name = name;
        this.imgadres = imgadres;
        this.motherspot = motherspot;
        this.spendtime = spendtime;
        this.moneystr = moneystr;
        this.money = money;
        this.moneyintroduce = moneyintroduce;
        this.kind = kind;
        this.timeinterval = timeinterval;
        this.level = level;
        this.index = index;
        this.hot = hot;
        this.useful = useful;
        this.introduce = introduce;
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

    public String getImgadres() {
        return imgadres;
    }

    public void setImgadres(String imgadres) {
        this.imgadres = imgadres;
    }

    public String getMotherspot() {
        return motherspot;
    }

    public void setMotherspot(String motherspot) {
        this.motherspot = motherspot;
    }

    public String getSpendtime() {
        return spendtime;
    }

    public void setSpendtime(String spendtime) {
        this.spendtime = spendtime;
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

    public String getMoneyintroduce() {
        return moneyintroduce;
    }

    public void setMoneyintroduce(String moneyintroduce) {
        this.moneyintroduce = moneyintroduce;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getTimeinterval() {
        return timeinterval;
    }

    public void setTimeinterval(String timeinterval) {
        this.timeinterval = timeinterval;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public boolean isHot() {
        return hot;
    }

    public void setHot(boolean hot) {
        this.hot = hot;
    }

    public boolean isUseful() {
        return useful;
    }

    public void setUseful(boolean useful) {
        this.useful = useful;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void setIntroduce(String introduce) {
        this.introduce = introduce;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}
