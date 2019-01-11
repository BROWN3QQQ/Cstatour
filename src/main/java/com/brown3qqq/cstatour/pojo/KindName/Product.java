package com.brown3qqq.cstatour.pojo.KindName;

public enum Product {
    PRODUCTKIND("产品类别", 21);

    private String name;
    private int index;

    Product(String name, int index) {
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