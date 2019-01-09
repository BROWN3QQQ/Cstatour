package com.brown3qqq.cstatour.pojo;


import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class example {

        private String id;
        private String picture;

    public example(String id, String picture) {
        this.id = id;
        this.picture = picture;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }



}
