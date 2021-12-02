package com.ccun.shapi.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@DynamicUpdate
@DynamicInsert
@Table(name = "goods")
public class Good {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "good_id")
    private Integer goodId;
    @Column(name = "user_id")
    private Integer userId;
    private String  title;
    private Integer category;
    @Column(name = "is_new")
    private Boolean isNew;
    private String  introduction;
    private Integer price;
    private Integer discount;
    private String  tag;
    @Column(name = "accept_user_id")
    private Integer acceptUserId;
    private Integer status;
    @Column(name = "confirm_id")
    private String  confirmId;
    private Integer pv;
    @Column(name = "create_time")
    private Timestamp createTime;
    @Column(name = "last_update_time")
    private Timestamp lastUpdateTime;

    public Good() {
    }

    public Good(Integer userId, String title, Integer category, Boolean isNew, String introduction, Integer price, Integer discount, String tag, Timestamp createTime, Timestamp lastUpdateTime) {
        this.userId = userId;
        this.title = title;
        this.category = category;
        this.isNew = isNew;
        this.introduction = introduction;
        this.price = price;
        this.discount = discount;
        this.tag = tag;
        this.createTime = createTime;
        this.lastUpdateTime = lastUpdateTime;
    }

    public Boolean getNew() {
        return isNew;
    }

    public void setNew(Boolean aNew) {
        isNew = aNew;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public Integer getDiscount() {
        return discount;
    }

    public void setDiscount(Integer discount) {
        this.discount = discount;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Integer getAcceptUserId() {
        return acceptUserId;
    }

    public void setAcceptUserId(Integer acceptUserId) {
        this.acceptUserId = acceptUserId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getConfirmId() {
        return confirmId;
    }

    public void setConfirmId(String confirmId) {
        this.confirmId = confirmId;
    }

    public Integer getPv() {
        return pv;
    }

    public void setPv(Integer pv) {
        this.pv = pv;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public Timestamp getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Timestamp lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }
}
