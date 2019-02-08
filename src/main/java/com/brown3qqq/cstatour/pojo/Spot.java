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

}
