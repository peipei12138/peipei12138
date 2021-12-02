package com.ccun.shapi.repository;

import com.ccun.shapi.entity.GoodImg;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface GoodImgRepository extends JpaRepository<GoodImg, Integer> {

    @Query(value = "select * from good_images where good_id = ?1", nativeQuery = true)
    List<GoodImg> findGoodImgsByGoodId(Integer goodId);

    @Modifying
    @Transactional
    @Query(value = "delete from good_images where good_id = ?1", nativeQuery = true)
    void deleteGoodImgsByGoodId(Integer goodId);
}
