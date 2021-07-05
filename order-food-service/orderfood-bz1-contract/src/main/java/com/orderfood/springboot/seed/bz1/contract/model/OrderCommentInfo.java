package com.orderfood.springboot.seed.bz1.contract.model;

import io.swagger.annotations.ApiModel;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

@ApiModel
public class OrderCommentInfo {
    /**
     * 评价信息
     */
    @Size(min=1, max =255)
    @NotEmpty
    private String id;
    /**
     * 订单id
     */
    @Size(min=1, max =255)
    private String orderId;
    /**
     * 评价人id
     */
    @Size(min=1, max =255)
    @NotEmpty
    private String evaluateId;
    /**
     * 评价人姓名
     */
    private String evaluateName;
    /**
     * 评价时间
     */
    private String time;
    /**
     * 喜好数量
     */
    private String likes;
    /**
     * 评价等级
     */
    private String likeLevle;
    /**
     * 评价内容
     */
    private String content;
    /**
     * 头像
     */
    private String headImg;

    public String getHeadImg() {
        return headImg;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(String evaluateId) {
        this.evaluateId = evaluateId;
    }

    public String getEvaluateName() {
        return evaluateName;
    }

    public void setEvaluateName(String evaluateName) {
        this.evaluateName = evaluateName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }

    public String getLikeLevle() {
        return likeLevle;
    }

    public void setLikeLevle(String likeLevle) {
        this.likeLevle = likeLevle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "OrderCommentInfo{" +
                "id='" + id + '\'' +
                ", orderId='" + orderId + '\'' +
                ", evaluateId='" + evaluateId + '\'' +
                ", evaluateName='" + evaluateName + '\'' +
                ", time='" + time + '\'' +
                ", likes='" + likes + '\'' +
                ", likeLevle='" + likeLevle + '\'' +
                ", content='" + content + '\'' +
                ", headImg='" + headImg + '\'' +
                '}';
    }
}
