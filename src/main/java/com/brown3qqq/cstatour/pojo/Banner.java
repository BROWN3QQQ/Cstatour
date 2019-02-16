package com.brown3qqq.cstatour.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Classname Banner
 * @Description 我就是真》码农,等我闲下来我就重构这些代码
 * @Date 2019/2/16 16:39
 * @Created by CQ
 */
@Document(collection = "banner")
public class Banner {
    @Id
    @Indexed
    private String id;
    private String name;

    private String imgcontent;
    //图片路径
    private String imgadres;

    private int index;

    public Banner (){}

    public Banner(String id, String name, String imgcontent, String imgadres, int index) {
        this.id = id;
        this.name = name;
        this.imgcontent = imgcontent;
        this.imgadres = imgadres;
        this.index = index;
    }

    public Banner(String name, String imgcontent, String imgadres, int index) {
        this.name = name;
        this.imgcontent = imgcontent;
        this.imgadres = imgadres;
        this.index = index;
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

    public String getImgcontent() {
        return imgcontent;
    }

    public void setImgcontent(String imgcontent) {
        this.imgcontent = imgcontent;
    }

    public String getImgadres() {
        return imgadres;
    }

    public void setImgadres(String imgadres) {
        this.imgadres = imgadres;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
