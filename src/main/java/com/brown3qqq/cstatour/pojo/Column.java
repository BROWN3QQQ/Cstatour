package com.brown3qqq.cstatour.pojo;


public class Column {
    private String id;
    private String CNname;
    private String ENname;

    /*类别中有小图选项，是否添加？？？*/

    /*区别是否是母导航，若果状态是true，为母导航，false为子导航*/
    private boolean state;

    /*若果是母导航，它的MotherColumn只能是主栏目，如果是子导航,它的MotherColumn是CNname或者ENname*/
    private String MotherColumn;

    //序列
    private int sequence;

    /*是否要添加首页主窗口、是缩图列表、首页公告栏？？？*/

    //是否是顶部导航
    private boolean topnav;
    //栏目介绍
    private String ColumnContent;

    public Column(String id, String CNname, String ENname, boolean state, String motherColumn, int sequence, boolean topnav, String columnContent) {
        this.id = id;
        this.CNname = CNname;
        this.ENname = ENname;
        this.state = state;
        MotherColumn = motherColumn;
        this.sequence = sequence;
        this.topnav = topnav;
        ColumnContent = columnContent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCNname() {
        return CNname;
    }

    public void setCNname(String CNname) {
        this.CNname = CNname;
    }

    public String getENname() {
        return ENname;
    }

    public void setENname(String ENname) {
        this.ENname = ENname;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }

    public String getMotherColumn() {
        return MotherColumn;
    }

    public void setMotherColumn(String motherColumn) {
        MotherColumn = motherColumn;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public boolean isTopnav() {
        return topnav;
    }

    public void setTopnav(boolean topnav) {
        this.topnav = topnav;
    }

    public String getColumnContent() {
        return ColumnContent;
    }

    public void setColumnContent(String columnContent) {
        ColumnContent = columnContent;
    }
}
