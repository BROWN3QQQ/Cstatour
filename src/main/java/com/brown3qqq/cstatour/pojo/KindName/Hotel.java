package com.brown3qqq.cstatour.pojo.KindName;

public enum Hotel {
    HOTELBED("酒店床型管理",11),HOTELBREAK("早餐类型",12);
    ;

    private String name;
    private int index;

    Hotel(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
