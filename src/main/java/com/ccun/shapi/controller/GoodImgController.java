package com.ccun.shapi.controller;

import com.ccun.shapi.service.GoodImgService;
import com.ccun.shapi.utils.Msg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/shapi/img")
public class GoodImgController {

    @Resource
    GoodImgService goodImgService;

    @PostMapping("/upload")
    //图片上传
    public Msg uploadImg(
            @RequestParam("img") MultipartFile imgFile
    ){

        String flag = goodImgService.uploadImg(imgFile);

        if(flag.equals("1")){
            return Msg.fail("上传文件不是规定图片格式(bmp，jpg，png，tif，webp)");
        }else if (flag.equals("2")){
            return Msg.fail("图片上传未知错误，请联系管理员");
        }else {
            return Msg.success().add("imgUrl", flag);
        }
    }
}
