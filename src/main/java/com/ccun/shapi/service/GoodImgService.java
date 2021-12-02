package com.ccun.shapi.service;

import com.ccun.shapi.entity.GoodImg;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GoodImgService{

    /**
     * 图片上传云服务器
     * @param img: 图片文件
     * @return  保存成功: 返回图片Url；
     *          保存失败："1"：文件格式不正确；
     *                  "2"：图片保存失败。
     */
    String uploadImg(MultipartFile img);

    /**
     * 图片URL批量保存到数据库
     * @param goodId: 商品id
     * @param imgUrls：URL数组
     */
    void insertImgUrls(Integer goodId, String[] imgUrls);

    /**
     * 根据商品id获取商品图片
     * @param goodId：商品id
     * @return 图片列表
     */
    List<GoodImg> selectGoodImgByGoodId(Integer goodId);

    /**
     * 根据商品id删除商品图片
     * @param goodId：商品id
     */
    void deleteGoodImgByGoodId(Integer goodId);
}
