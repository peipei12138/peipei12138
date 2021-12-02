package com.ccun.shapi.service.impl;

import com.ccun.shapi.entity.Good;
import com.ccun.shapi.repository.GoodRepository;
import com.ccun.shapi.service.GoodService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;

@Service
public class GoodServiceImpl implements GoodService {

    @Value("${page.page-size}")
    private Integer pageSize;

    @Resource
    GoodRepository goodRepository;

    @Override
    public void insertGood(Good good) {
        goodRepository.save(good);
    }

    @Override
    public void updateGood(Good good) {
        goodRepository.save(good);
    }

    @Override
    public Page<Good> selectGoodsByUserIdAndStatus(Integer userId, Integer status, Integer currentPage) {

        Pageable pageable = PageRequest.of(currentPage,pageSize);
        return goodRepository.findGoodsByUserIdAndStatus(userId, status, pageable);
    }

    @Override
    public Page<Good> selectGoodsOrderByCreateTime(Integer currentPage) {

        Pageable pageable = PageRequest.of(currentPage,pageSize);
        return goodRepository.findGoodsOrderByCreateTime(pageable);
    }

    @Override
    public Page<Good> selectGoodsByNew(Integer currentPage) {

        Pageable pageable = PageRequest.of(currentPage,pageSize);
        return goodRepository.findGoodsByNew(pageable);
    }

    @Override
    public Page<Good> selectGoodsOrderByDiscount(Integer currentPage) {

        Pageable pageable = PageRequest.of(currentPage,pageSize);
        return goodRepository.findGoodsOrderByDiscount(pageable);
    }

    @Override
    public Page<Good> selectGoodsOrderByPV(Integer currentPage) {

        Pageable pageable = PageRequest.of(currentPage,pageSize);
        return goodRepository.findGoodsOrderByPV(pageable);
    }

    @Override
    public Page<Good> selectAllGoods(Integer currentPage) {

        Pageable pageable = PageRequest.of(currentPage,pageSize);
        return goodRepository.findAll(pageable);
    }

    @Override
    public Good selectGoodByGoodId(Integer goodId) {
        return goodRepository.findGoodByGoodId(goodId);
    }

    @Override
    public Page<Good> selectGoodsByCategory(Integer category, Integer currentPage) {

        Pageable pageable = PageRequest.of(currentPage,pageSize);
        return goodRepository.findGoodsByCategory(category, pageable);
    }

    @Override
    public Page<Good> selectGoodsWithMatch(String term, Integer currentPage) {

        Pageable pageable = PageRequest.of(currentPage,pageSize);
        return goodRepository.searchGoodsWithMatch(term, pageable);
    }

    @Override
    public Page<Good> selectGoodsWithLike(String term, Integer currentPage) {

        Pageable pageable = PageRequest.of(currentPage,pageSize);
        return goodRepository.searchGoodsWithLike(term, pageable);
    }

    @Override
    public Page<Good> test(Integer currentPage) {

        Pageable pageable = PageRequest.of(currentPage,pageSize);
        return goodRepository.test(pageable);
    }
}
