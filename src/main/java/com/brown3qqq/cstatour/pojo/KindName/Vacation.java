package com.brown3qqq.cstatour.pojo.KindName;

public enum Vacation {

    ADMIN("景区管理",31),PLAYKIND("游玩类型",32),PLAYKINDtTIME("游玩时段类型",33),LEVEL("推荐等级类型",34),FOOD("餐饮菜系",35),LINE("线路类型",36);
    ;

    private String name;
    private int index;

    Vacation(String name, int index) {
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
