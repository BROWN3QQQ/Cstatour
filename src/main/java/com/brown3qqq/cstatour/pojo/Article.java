package com.brown3qqq.cstatour.pojo;

import com.alibaba.fastjson.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "article")
public class Article {
    @Id
    @Indexed
    private String id;
    private String name;

    private Column monthercolumn;
    private Column soncolumn;

    private String imgadres;
    private boolean stick;

    private int index;
    private String content;

    public Article(){}
    public Article(String id, String name, Column monthercolumn, Column soncolumn, String imgadres, boolean stick, int index, String content) {
        this.id = id;
        this.name = name;
        this.monthercolumn = monthercolumn;
        this.soncolumn = soncolumn;
        this.imgadres = imgadres;
        this.stick = stick;
        this.index = index;
        this.content = content;
    }

    public Article(String name, Column monthercolumn, Column soncolumn, String imgadres, boolean stick, int index, String content) {
        this.name = name;
        this.monthercolumn = monthercolumn;
        this.soncolumn = soncolumn;
        this.imgadres = imgadres;
        this.stick = stick;
        this.index = index;
        this.content = content;
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

    public Column getMonthercolumn() {
        return monthercolumn;
    }

    public void setMonthercolumn(Column monthercolumn) {
        this.monthercolumn = monthercolumn;
    }

    public Column getSoncolumn() {
        return soncolumn;
    }

    public void setSoncolumn(Column soncolumn) {
        this.soncolumn = soncolumn;
    }

    public String getImgadres() {
        return imgadres;
    }

    public void setImgadres(String imgadres) {
        this.imgadres = imgadres;
    }

    public boolean isStick() {
        return stick;
    }

    public void setStick(boolean stick) {
        this.stick = stick;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
