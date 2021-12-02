package com.ccun.shapi.repository;

import com.ccun.shapi.entity.Good;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.HashMap;

public interface GoodRepository extends JpaRepository<Good, Integer> {

    @Query(value = "select * from goods", nativeQuery = true)
    Page<Good> findAll(Pageable pageable);

    @Query(value = "select * from goods where user_id = ?1 and status = ?2", nativeQuery = true)
    Page<Good> findGoodsByUserIdAndStatus(Integer userId, Integer status, Pageable pageable);

    @Query(value = "select * from goods where good_id = ?1", nativeQuery = true)
    Good findGoodByGoodId(Integer goodId);

    @Query(value = "select * from goods where status = 0 order by create_time desc", nativeQuery = true)
    Page<Good> findGoodsOrderByCreateTime(Pageable pageable);

    @Query(value = "select * from goods where status = 0 and is_new = true", nativeQuery = true)
    Page<Good> findGoodsByNew(Pageable pageable);

    @Query(value = "select * from goods where status = 0 and discount <= 9 order by discount, price", nativeQuery = true)
    Page<Good> findGoodsOrderByDiscount(Pageable pageable);

    @Query(value = "select * from goods where status = 0 order by pv desc", nativeQuery = true)
    Page<Good> findGoodsOrderByPV(Pageable pageable);

    @Query(value = "select * from goods where status = 0 and category = ?1", nativeQuery = true)
    Page<Good> findGoodsByCategory(Integer category, Pageable pageable);

    @Query(value = "select * from goods where status = 0 and match (title,introduction,tag) against (?1 in natural language mode)", nativeQuery = true)
    Page<Good> searchGoodsWithMatch(String term, Pageable pageable);

    @Query(value = "select * from goods where status = 0 and (title like concat('%',?1,'%') or introduction like concat('%',?1,'%') or tag like concat('%',?1,'%'))", nativeQuery = true)
    Page<Good> searchGoodsWithLike(String term, Pageable pageable);

    @Query(value = "SELECT * FROM goods WHERE MATCH (title,introduction,tag) AGAINST ('一 毛沛之' IN NATURAL LANGUAGE MODE)", nativeQuery = true)
    Page<Good> test(Pageable pageable);
}
