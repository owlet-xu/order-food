package com.orderfood.springboot.seed.bz1.contract.model;

import io.swagger.annotations.ApiModel;

/**
 * Created by xuguoyuan on 2018/7/11.
 */
@ApiModel
public class OrderUpdateStatusInfo {
    private String id;
    private String status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "OrderUpdateStatusInfo{" +
                "id='" + id + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}
