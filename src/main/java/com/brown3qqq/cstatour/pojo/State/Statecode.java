package com.brown3qqq.cstatour.pojo.State;

public enum Statecode {
    SUCCESS("000", "操作成功"),
    FAIL("001","操作失败"),
    ABNORMAL("002","操作异常"),
    ADMIN("111","管理员登录成功"),

    TARGETNULL("998","请求的对象不存在"),

    ;
    Statecode(String code, String msg){
        this.code = code;
        this.msg = msg;
    }
    private String code;
    private String msg;

    public String getCode() {
        return code;
    }
    public String getMsg() {
        return msg;
    }
}
