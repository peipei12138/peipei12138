package com.ccun.shapi.controller;

import com.ccun.shapi.entity.Good;
import com.ccun.shapi.entity.User;
import com.ccun.shapi.service.GoodImgService;
import com.ccun.shapi.service.GoodService;
import com.ccun.shapi.service.UserService;
import com.ccun.shapi.utils.Msg;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("/shapi")
public class GoodController {

    @Resource
    GoodService goodService;
    @Resource
    GoodImgService goodImgService;
    @Resource
    UserService userService;


    @PostMapping("/sell")
    //用户添加商品
    public Msg addGood(
           @RequestParam("token")       String token,
           @RequestParam("title")       String title,
           @RequestParam("category")    Integer category,
           @RequestParam("isNew")       Boolean isNew,
           @RequestParam("introduction")String introduction,
           @RequestParam("price")       Integer price,
           @RequestParam("discount")    Integer discount,
           @RequestParam("tag")         String tag,
           @RequestParam("imgUrls")     String[] imgUrls
    ){

        if(!isUsefulToken(token)){
            return Msg.fail("this token is useless");
        }else {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Good good = new Good(userService.selectUserByToken(token).getId(), title, category,
                    isNew, introduction, price, discount, tag, timestamp, timestamp);

            goodService.insertGood(good);
            goodImgService.insertImgUrls(good.getGoodId(), imgUrls);
            return Msg.success()
                    .add("userId", good.getUserId())
                    .add("title", good.getTitle())
                    .add("goodId", good.getGoodId());
        }
    }

    @PostMapping("/update")
    //用户修改商品
    public Msg updateGood(
            @RequestParam("token")       String token,
            @RequestParam("goodId")      Integer goodId,
            @RequestParam("title")       String title,
            @RequestParam("category")    Integer category,
            @RequestParam("isNew")       Boolean isNew,
            @RequestParam("introduction")String introduction,
            @RequestParam("price")       Integer price,
            @RequestParam("discount")    Integer discount,
            @RequestParam("tag")         String tag,
            @RequestParam("imgUrls")     String[] imgUrls
    ){
        if(!isUsefulToken(token)){
            return Msg.fail("this token is useless");
        }else {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            Good good = goodService.selectGoodByGoodId(goodId);
            good.setTitle(title);
            good.setCategory(category);
            good.setNew(isNew);
            good.setIntroduction(introduction);
            good.setPrice(price);
            good.setDiscount(discount);
            good.setTag(tag);
            good.setLastUpdateTime(timestamp);
            goodService.updateGood(good);

            goodImgService.deleteGoodImgByGoodId(goodId);
            goodImgService.insertImgUrls(goodId, imgUrls);

            return Msg.success()
                    .add("userId", good.getUserId())
                    .add("title", good.getTitle())
                    .add("goodId", good.getGoodId());
        }
    }

    @GetMapping("/-/show_all")
    //获取所有商品
    public Msg getAllGoods(
            @RequestParam("pn") Integer pageNumber
    ){

        Page<Good> page = goodService.selectAllGoods(pageNumber);
        return Msg.success().add("pageInfo", showGoodsMsg(page));
    }

    @GetMapping("/index")
    //获取主页商品
    public Msg getGoodsByStatus(
            @RequestParam("token") String token,
            @RequestParam("index") Integer index,
            @RequestParam("pn") Integer pageNumber
    ){

        if(!isUsefulToken(token)){
            return Msg.fail("this token is useless");
        }else {
            Page<Good> page = null;

            switch(index){
                case 1:{
                    page = goodService.selectGoodsOrderByCreateTime(pageNumber);
                    break;
                }
                case 2:{
                    page = goodService.selectGoodsByNew(pageNumber);
                    break;
                }
                case 3:{
                    page = goodService.selectGoodsOrderByDiscount(pageNumber);
                    break;
                }
                case 4:{
                    page = goodService.selectGoodsOrderByPV(pageNumber);
                    break;
                }
            }
            assert page != null;
            return Msg.success().add("pageInfo", showGoodsMsg(page));
        }
    }

    @GetMapping("/category")
    //获取指定类别商品
    public Msg getGoodsByCategory(
            @RequestParam("token") String token,
            @RequestParam("category") Integer category,
            @RequestParam("pn") Integer pageNumber
    ){
        if(!isUsefulToken(token)){
            return Msg.fail("this token is useless");
        }else {
            Page<Good> page = goodService.selectGoodsByCategory(category, pageNumber);
            return Msg.success().add("pageInfo", showGoodsMsg(page));
        }
    }

    @GetMapping("/my_goods")
    //获取本人闲置商品
    public Msg getGoodsByToken(
            @RequestParam("token") String token,
            @RequestParam("status") Integer status,
            @RequestParam("pn") Integer pageNumber
    ){

        if(!isUsefulToken(token)){
            return Msg.fail("this token is useless");
        }else {
            Page<Good> page = goodService.selectGoodsByUserIdAndStatus(userService.selectUserByToken(token).getId(), status, pageNumber);
            return Msg.success().add("pageInfo", showGoodsMsg(page));
        }
    }

    @GetMapping("/good")
    //根据商品id获取商品
    public Msg getGoodByGoodId(
            @RequestParam("token") String token,
            @RequestParam("goodId") Integer goodId
    ){

        if(!isUsefulToken(token)){
            return Msg.fail("this token is useless");
        }else {
            Good good = goodService.selectGoodByGoodId(goodId);
            //访客量加一
            good.setPv(good.getPv() + 1);
            goodService.updateGood(good);

            return Msg.success()
                    .add("good", good)
                    .add("goodImgs", goodImgService.selectGoodImgByGoodId(good.getGoodId()))
                    .add("user", userService.selectUserById(good.getUserId()));
        }
    }

    @GetMapping("/status")
    //修改商品状态
    public Msg altGoodStatusByGoodId(
            @RequestParam("token") String token,
            @RequestParam("status") Integer status,
            @RequestParam("goodId") Integer goodId
    ){

        if(!isUsefulToken(token)){
            return Msg.fail("this token is useless");
        }else {
            Good good = goodService.selectGoodByGoodId(goodId);
            //商品状态码设置为status
            good.setStatus(status);
            goodService.updateGood(good);

            return Msg.success();
        }
    }

    @GetMapping("/-/ban")
    //商品下架
    public Msg banGoodByGoodId(
            @RequestParam("token") String token,
            @RequestParam("goodId") Integer goodId
    ){

        if(!isUsefulToken(token)){
            return Msg.fail("this token is useless");
        }else {
            Good good = goodService.selectGoodByGoodId(goodId);
            //商品status设置为-1
            good.setStatus(-1);
            goodService.updateGood(good);

            return Msg.success();
        }
    }

    @GetMapping("/-/reshelf")
    //商品重新上架
    public Msg reshelfGoodByGoodId(
            @RequestParam("token") String token,
            @RequestParam("goodId") Integer goodId
    ){

        if(!isUsefulToken(token)){
            return Msg.fail("this token is useless");
        }else {
            Good good = goodService.selectGoodByGoodId(goodId);
            //商品status设置为0
            good.setStatus(0);
            goodService.updateGood(good);

            return Msg.success();
        }
    }

    @GetMapping("/search")
    //搜索商品
    public Msg searchGoods(
            @RequestParam("token") String token,
            @RequestParam("term") String term,
            @RequestParam("pn") Integer pageNumber
    ){

        if(!isUsefulToken(token)){
            return Msg.fail("this token is useless");
        }else {
            if(term.equals("")) {
                return Msg.fail("搜索内容不能为空");
            }else if(term.length() == 1){
                Page<Good> page = goodService.selectGoodsWithLike(term, pageNumber);
                return Msg.success().add("pageInfo", showGoodsMsg(page));
            }else {
                Page<Good> page = goodService.selectGoodsWithMatch(term, pageNumber);
                return Msg.success().add("pageInfo", showGoodsMsg(page));
            }
        }
    }

    @GetMapping("/-/test")
    public Msg test(
//            @RequestParam(name = "test", defaultValue = "false") Boolean test
            @RequestParam("pn") Integer pageName
    ){
//        if(test)
//            return "123";
//        else
//            return "456";
        Page<Good> page = goodService.test(pageName);
        return Msg.success().add("pageInfo", showGoodsMsg(page));
    }


    //自定义分页返回信息
    private Map<String, Object> showGoodsMsg(Page<Good> page){
        Map<String, Object> resultMap = new HashMap<>();
        List<Good> primGoods = page.toList();
        List<Map<String, Object>> goods = new ArrayList<>();

        for(Good good: primGoods){
            Map<String, Object> map = new HashMap<>();
            User user = userService.selectUserById(good.getUserId());

            map.put("goodId", good.getGoodId());
            map.put("title", good.getTitle());
            map.put("category", good.getCategory());
            map.put("price", good.getPrice());
            map.put("discount", good.getDiscount());
            map.put("introduction", good.getIntroduction());
            map.put("tag", good.getTag());
            map.put("pv", good.getPv());
            map.put("isNew", good.getNew());
            map.put("username", user.getUserName());
            map.put("avatarUrl", user.getAvatarUrl());
            map.put("goodImgs", goodImgService.selectGoodImgByGoodId(good.getGoodId()));

            goods.add(map);
        }
        resultMap.put("goods", goods);

        resultMap.put("totalPages", page.getTotalPages()); //总页数
        resultMap.put("totalElements", page.getTotalElements()); //总商品数
        resultMap.put("isLast", page.isLast()); //是否为最后一页
        resultMap.put("isFirst", page.isFirst()); //是否为第一页
        resultMap.put("pageNumber", page.getNumber()); //当前页面
        resultMap.put("pageSize", page.getSize()); //单页商品数量

        return resultMap;
    }

    //判断用户token是否有效
    private boolean isUsefulToken(String token){
        return userService.selectUserByToken(token) != null;
    }
}
