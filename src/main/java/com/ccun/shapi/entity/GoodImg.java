package com.ccun.shapi.entity;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;

@Entity
@DynamicInsert
@DynamicUpdate
@Table(name = "good_images")
public class GoodImg {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "img_id")
    private Integer imgId;

    @Column(name = "good_id")
    private Integer goodId;

    @Column(name = "img_url")
    private String imgUrl;

    public GoodImg() {
    }

    public GoodImg(Integer goodId, String imgUrl) {
        this.goodId = goodId;
        this.imgUrl = imgUrl;
    }

    public Integer getImgId() {
        return imgId;
    }

    public void setImgId(Integer imgId) {
        this.imgId = imgId;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
        this.goodId = goodId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
