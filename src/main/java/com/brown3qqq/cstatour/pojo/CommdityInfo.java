package com.brown3qqq.cstatour.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigDecimal;

/**
 * @Classname CommdityInfo
 * @Description TODO
 * @Date 2019/3/2 10:29
 * @Created by CQ
 */
public class CommdityInfo {
    @Id
    @Indexed
    private String id;
    private String name;

    private String imgadres;
    private String moneystr;
    private BigDecimal money;

    private int sum;
    private String measurement;

    public CommdityInfo(){}

    public CommdityInfo(String id, String name, String moneystr, BigDecimal money, int sum, String measurement) {
        this.id = id;
        this.name = name;
        this.moneystr = moneystr;
        this.money = money;
        this.sum = sum;
        this.measurement = measurement;
    }

    public CommdityInfo(String id, String name, String imgadres, String moneystr, BigDecimal money, int sum, String measurement) {
        this.id = id;
        this.name = name;
        this.imgadres = imgadres;
        this.moneystr = moneystr;
        this.money = money;
        this.sum = sum;
        this.measurement = measurement;
    }


    public String getImgadres() {
        return imgadres;
    }

    public void setImgadres(String imgadres) {
        this.imgadres = imgadres;
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

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getMeasurement() {
        return measurement;
    }

    public void setMeasurement(String measurement) {
        this.measurement = measurement;
    }
}
