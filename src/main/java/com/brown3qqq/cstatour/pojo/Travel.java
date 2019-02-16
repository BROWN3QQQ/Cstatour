package com.brown3qqq.cstatour.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

//旅游度假
@Document(collection = "travel")
public class Travel {

    @Id
    @Indexed
    private String id;
    private String name;

    //图片路径
    private String imgadres;
    private String day;
    private String night;

    private String manmoney;
    private String kidmoney;
    private String moneyintroduce;

    private String level;
    private int index;
    private String line;

    private boolean hot;
    private boolean useful;

    //关联景区是否要写
    private String conspot;
    private String trip;
    private String tripspecial;
    private String remarks;

    public Travel(){}

    public Travel(String id, String name, String imgadres, String day, String night, String manmoney, String kidmoney, String moneyintroduce, String level, int index, String line, boolean hot, boolean useful, String conspot, String trip, String tripspecial, String remarks) {
        this.id = id;
        this.name = name;
        this.imgadres = imgadres;
        this.day = day;
        this.night = night;
        this.manmoney = manmoney;
        this.kidmoney = kidmoney;
        this.moneyintroduce = moneyintroduce;
        this.level = level;
        this.index = index;
        this.line = line;
        this.hot = hot;
        this.useful = useful;
        this.conspot = conspot;
        this.trip = trip;
        this.tripspecial = tripspecial;
        this.remarks = remarks;
    }

    public Travel(String name, String imgadres, String day, String night, String manmoney, String kidmoney, String moneyintroduce, String level, int index, String line, boolean hot, boolean useful, String conspot, String trip, String tripspecial, String remarks) {
        this.name = name;
        this.imgadres = imgadres;
        this.day = day;
        this.night = night;
        this.manmoney = manmoney;
        this.kidmoney = kidmoney;
        this.moneyintroduce = moneyintroduce;
        this.level = level;
        this.index = index;
        this.line = line;
        this.hot = hot;
        this.useful = useful;
        this.conspot = conspot;
        this.trip = trip;
        this.tripspecial = tripspecial;
        this.remarks = remarks;
    }

    public String getConspot() {
        return conspot;
    }

    public void setConspot(String conspot) {
        this.conspot = conspot;
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

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getNight() {
        return night;
    }

    public void setNight(String night) {
        this.night = night;
    }

    public String getManmoney() {
        return manmoney;
    }

    public void setManmoney(String manmoney) {
        this.manmoney = manmoney;
    }

    public String getKidmoney() {
        return kidmoney;
    }

    public void setKidmoney(String kidmoney) {
        this.kidmoney = kidmoney;
    }

    public String getMoneyintroduce() {
        return moneyintroduce;
    }

    public void setMoneyintroduce(String moneyintroduce) {
        this.moneyintroduce = moneyintroduce;
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

    public String getLine() {
        return line;
    }

    public void setLine(String line) {
        this.line = line;
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

    public String getTrip() {
        return trip;
    }

    public void setTrip(String trip) {
        this.trip = trip;
    }

    public String getTripspecial() {
        return tripspecial;
    }

    public void setTripspecial(String tripspecial) {
        this.tripspecial = tripspecial;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
}