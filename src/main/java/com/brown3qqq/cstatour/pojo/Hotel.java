package com.brown3qqq.cstatour.pojo;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

//酒店管理
@Document(collection = "hotel")
public class Hotel {

    @Id
    @Indexed
    private String id;
    private String name;


    //图片链接
    private String imgadres;
    private String telnum;
    private String  hoteladdress;

    private String motherspot;
    //添加排序
    private  int index;

    private boolean hot;
    private boolean useful;

    private String hotelcontent;

    public Hotel(){}

    public Hotel(String id, String name, String imgadres, String telnum, String hoteladdress, String motherspot, int index, boolean hot, boolean useful, String hotelcontent) {
        this.id = id;
        this.name = name;
        this.imgadres = imgadres;
        this.telnum = telnum;
        this.hoteladdress = hoteladdress;
        this.motherspot = motherspot;
        this.index = index;
        this.hot = hot;
        this.useful = useful;
        this.hotelcontent = hotelcontent;
    }

    public Hotel(String name, String imgadres, String telnum, String hoteladdress, String motherspot, int index, boolean hot, boolean useful, String hotelcontent) {
        this.name = name;
        this.imgadres = imgadres;
        this.telnum = telnum;
        this.hoteladdress = hoteladdress;
        this.motherspot = motherspot;
        this.index = index;
        this.hot = hot;
        this.useful = useful;
        this.hotelcontent = hotelcontent;
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

    public String getTelnum() {
        return telnum;
    }

    public void setTelnum(String telnum) {
        this.telnum = telnum;
    }

    public String getHoteladdress() {
        return hoteladdress;
    }

    public void setHoteladdress(String hoteladdress) {
        this.hoteladdress = hoteladdress;
    }

    public String getMotherspot() {
        return motherspot;
    }

    public void setMotherspot(String motherspot) {
        this.motherspot = motherspot;
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

    public String getHotelcontent() {
        return hotelcontent;
    }

    public void setHotelcontent(String hotelcontent) {
        this.hotelcontent = hotelcontent;
    }
}
