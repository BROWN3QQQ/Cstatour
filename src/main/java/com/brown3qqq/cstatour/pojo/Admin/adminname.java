package com.brown3qqq.cstatour.pojo.Admin;

public enum adminname {
    ADMINNAME("admin");
    private String name;

    adminname(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
