package com.example.travel.common;

public class TravelJsonResult<T> {
    // 响应业务状态
    private Integer status;

    // 响应消息
    private String msg;

    // 响应中的数据
    private Object data;

    private String ok; // 不使用


    public static TravelJsonResult build(Integer status, String msg, Object data) {
        return new TravelJsonResult(status, msg, data);
    }

    public static TravelJsonResult ok(Object data) {
        return new TravelJsonResult(data);
    }

    public static TravelJsonResult ok() {
        return new TravelJsonResult(null);
    }

    public static TravelJsonResult errorMsg(String msg) {
        return new TravelJsonResult(500, msg, null);
    }

    public static TravelJsonResult errorMap(Object data) {
        return new TravelJsonResult(501, "error", data);
    }

    public static TravelJsonResult errorTokenMsg(String msg) {
        return new TravelJsonResult(502, msg, null);
    }

    public static TravelJsonResult errorException(String msg) {
        return new TravelJsonResult(555, msg, null);
    }

    public TravelJsonResult() {

    }

    public TravelJsonResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public TravelJsonResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public Boolean isOK() {
        return this.status == 200;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }
}

