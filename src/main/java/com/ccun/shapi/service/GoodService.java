package com.ccun.shapi.service;

import com.ccun.shapi.entity.Good;
import org.springframework.data.domain.Page;

import java.util.HashMap;
import java.util.List;

public interface GoodService {

    /**
     * 增加商品
     * @param good: good
     */
    void insertGood(Good good);

    /**
     * 修改商品
     * @param good：good
     */
    void updateGood(Good good);

    /**
     * 分页获取本人闲置商品
     * @param userId: 用户id
     * @param currentPage：当前页
     * @return 闲置商品
     */
    Page<Good> selectGoodsByUserIdAndStatus(Integer userId, Integer status, Integer currentPage);

    /**
     * 1 按照最新上架获取商品
     * -@param status：状态码
     * @param currentPage：当前页
     * @return 当前页面所有商品
     */
    Page<Good> selectGoodsOrderByCreateTime(Integer currentPage);

    /**
     * 2 获取全新商品
     * @param currentPage：当前页
     * @return 当前页面所有商品
     */
    Page<Good> selectGoodsByNew(Integer currentPage);

    /**
     * 3 按照折扣排序获取商品
     * @param currentPage：当前页
     * @return 当前页面所有商品
     */
    Page<Good> selectGoodsOrderByDiscount(Integer currentPage);

    /**
     * 4 按照访客量排序获取商品
     * @param currentPage：当前页面
     * @return 当前页面所有商品
     */
    Page<Good> selectGoodsOrderByPV(Integer currentPage);

    /**
     * 分页获取所有商品
     * @Param currentPage: 当前页
     * -@Param pageSize: 每页最大容量
     * @return 所有商品的列表
     */
    Page<Good> selectAllGoods(Integer currentPage);

    /**
     * 通过商品id查找商品
     * @param goodId：商品id
     * @return 商品数据
     */
    Good selectGoodByGoodId(Integer goodId);

    /**
     * 根据商品类别查找商品
     * @param category：商品类别
     * @param currentPage：当前页
     * @return 当前页面所有商品
     */
    Page<Good> selectGoodsByCategory(Integer category, Integer currentPage);

    /**
     * 全文检索商品
     * @param term：检索关键字
     * @param currentPage：当前页
     * @return 当前页面所有商品
     */
    Page<Good> selectGoodsWithMatch(String term, Integer currentPage);

    /**
     * 使用like全文检索商品
     * @param term：检索关键字
     * @param currentPage：当前页
     * @return 当前页面所有商品
     */
    Page<Good> selectGoodsWithLike(String term, Integer currentPage);

    /**
     * test
     * @param currentPage
     * @return
     */
    Page<Good> test(Integer currentPage);
}
