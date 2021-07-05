package com.orderfood.springboot.seed.bz1.service.entity;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "os_comment", schema= "order_food")
public class OrderCommentEntity {
    @Id
    @GenericGenerator(name = "idGenerator",strategy = "uuid")
    @GeneratedValue( generator = "idGenerator")
    @Column(name = "id")
    private String id;
    private String orderId;
    private String evaluateId;
    private String evaluateName;
    private String time;
    private String likes;
    private String likeLevle;
    private String content;

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
        return "OrderCommentEntity{" +
                "id='" + id + '\'' +
                ", orderId='" + orderId + '\'' +
                ", evaluateId='" + evaluateId + '\'' +
                ", evaluateName='" + evaluateName + '\'' +
                ", time='" + time + '\'' +
                ", likes='" + likes + '\'' +
                ", likeLevle='" + likeLevle + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
