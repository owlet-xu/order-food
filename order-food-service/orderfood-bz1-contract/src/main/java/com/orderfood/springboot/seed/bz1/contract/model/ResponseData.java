package com.orderfood.springboot.seed.bz1.contract.model;

/**
 * 整体封装的返回对象，与前端协商的
 * Created by xuguoyuan on 2018/7/11.
 */
public class ResponseData {
    /**
     * 是否返回成功
     */
    private boolean success;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 返回的对象
     */
    private Object data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
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
}
