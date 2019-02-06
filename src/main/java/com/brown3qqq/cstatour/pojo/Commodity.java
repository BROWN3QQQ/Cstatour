package com.brown3qqq.cstatour.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;


@Document(collection = "commodity")
public class Commodity {
    @Id
    @Indexed
    private String id;
    private String commodityname;

    //图片路径
    private String imgadres;
    //商业计算金钱 要用BIGDECIMAL类
    private String moneystr;
    private BigDecimal money;
    //计量单位
    private String company;

    private String motherkind;

    private boolean hot;
    private boolean useful;

    private String content;

    public Commodity(){}
    public Commodity(String id, String commodityname, String imgadres, String moneystr, BigDecimal money, String company, String motherkind, boolean hot, boolean useful, String content) {
        this.id = id;
        this.commodityname = commodityname;
        this.imgadres = imgadres;
        this.moneystr = moneystr;
        this.money = money;
        this.company = company;
        this.motherkind = motherkind;
        this.hot = hot;
        this.useful = useful;
        this.content = content;
    }

    public Commodity(String commodityname, String imgadres, String moneystr, BigDecimal money, String company, String motherkind, boolean hot, boolean useful, String content) {
        this.commodityname = commodityname;
        this.imgadres = imgadres;
        this.moneystr = moneystr;
        this.money = money;
        this.company = company;
        this.motherkind = motherkind;
        this.hot = hot;
        this.useful = useful;
        this.content = content;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommodityname() {
        return commodityname;
    }

    public void setCommodityname(String commodityname) {
        this.commodityname = commodityname;
    }

    public String getImgadres() {
        return imgadres;
    }

    public void setImgadres(String imgadres) {
        this.imgadres = imgadres;
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

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getMotherkind() {
        return motherkind;
    }

    public void setMotherkind(String motherkind) {
        this.motherkind = motherkind;
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

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
