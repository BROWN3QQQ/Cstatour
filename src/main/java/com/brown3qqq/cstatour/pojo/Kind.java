package com.brown3qqq.cstatour.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;

@Document(collection = "kind")  //写死没关系吧,先写死，后面再写统一的配置文件
public class Kind {

    @Id
    @Indexed
    private String id;
    private String name;

    //状态
    private boolean state;
    private ArrayList<Kind> sonkind;//类别名称队列
    //链接图片地址

    private int index;
    private String imgadres;
    private String montherid;
    private int sum;
    private String content;

    public Kind(){}
    public Kind(String id, String name, boolean state, ArrayList<Kind> sonkind, int index, String imgadres, String content) {
        this.id = id;
        this.name = name;
        this.state = state;
        this.sonkind = sonkind;
        this.index = index;
        this.imgadres = imgadres;
        this.content = content;
    }

    public Kind(String name, boolean state, ArrayList<Kind> sonkind, int index, String imgadres, String content) {
        this.name = name;
        this.state = state;
        this.sonkind = sonkind;
        this.index = index;
        this.imgadres = imgadres;
        this.content = content;
    }

    public Kind(String name, boolean state, ArrayList<Kind> sonkind, int index, String imgadres, String montherid, String content) {
        this.name = name;
        this.state = state;
        this.sonkind = sonkind;
        this.index = index;
        this.imgadres = imgadres;
        this.montherid = montherid;
        this.content = content;
    }

    public Kind(String name, boolean state, ArrayList<Kind> sonkind, int index, String imgadres, String montherid, int sum, String content) {
        this.name = name;
        this.state = state;
        this.sonkind = sonkind;
        this.index = index;
        this.imgadres = imgadres;
        this.montherid = montherid;
        this.sum = sum;
        this.content = content;
    }

    public int getSum() {
        return sum;
    }

    public void setSum(int sum) {
        this.sum = sum;
    }

    public String getMontherid() {
        return montherid;
    }

    public void setMontherid(String montherid) {
        this.montherid = montherid;
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

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public ArrayList<Kind> getSonkind() {
        return sonkind;
    }

    public void setSonkind(ArrayList<Kind> sonkind) {
        this.sonkind = sonkind;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getImgadres() {
        return imgadres;
    }

    public void setImgadres(String imgadres) {
        this.imgadres = imgadres;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
