package com.bys.work.model;

/**
 * @PackageName:com.bys.work.model
 * @ClassName:RespBean
 * @Description
 * @Author: yushengbi
 * @Date:2020/3/22 13:26
 */
public class RespBean {
    private Integer status;
    private String msg;
    private Object object;

    public static RespBean build() {
        return new RespBean();
    }

    public static RespBean ok(String msg) {
        return new RespBean(200, msg, null);
    }

    public static RespBean ok(String msg, Object object) {
        return new RespBean(200, msg, object);
    }

    public static RespBean error(String msg) {
        return new RespBean(500, msg, null);
    }

    public static RespBean error(String msg, Object o) {
        return new RespBean(200, msg, o);
    }

    private RespBean() {

    }

    private RespBean(Integer status, String msg, Object object) {
        this.status = status;
        this.msg = msg;
        this.object = object;
    }

    @Override
    public String toString() {
        return "RespBean{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", object=" + object +
                '}';
    }

    public Integer getStatus() {
        return status;
    }

    public RespBean setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public RespBean setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getObject() {
        return object;
    }

    public RespBean setObject(Object object) {
        this.object = object;
        return this;
    }
}
