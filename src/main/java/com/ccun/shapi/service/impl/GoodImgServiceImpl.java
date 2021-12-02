package com.ccun.shapi.service.impl;

import com.ccun.shapi.entity.GoodImg;
import com.ccun.shapi.repository.GoodImgRepository;
import com.ccun.shapi.service.GoodImgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class GoodImgServiceImpl implements GoodImgService {

    private static final List<String> IMG_TYPE = Arrays.asList(
            ".bmp", ".jpg", ".png", ".tif", ".webp"
    );
    @Value("${img.upload-dir}")
    private String upload_dir;
    @Value("${img.mapper-path}")
    private String mapper_path;

    @Resource
    GoodImgRepository goodImgRepository;

    @Override
    public String uploadImg(MultipartFile img) {

        String imgName = img.getOriginalFilename();
        assert imgName != null;
        String suffix = imgName.substring(imgName.lastIndexOf("."));

        //判断图片格式是否为以下格式：bmp，jpg，png，tif，webp
        if(!isImg(suffix)){
            return "1";
        }

        String newImgName = UUID.randomUUID().toString() + suffix;
        String realPath = upload_dir + "/" + newImgName;
        File dest = new File(realPath);

        //判断父目录是否存在，不存在则创建该目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdir();
        }

        //保存图片文件
        try {
            img.transferTo(dest);
            return mapper_path + newImgName;
        } catch (IOException e) {
            e.printStackTrace();
            return "2";
        }
    }

    @Override
    public void insertImgUrls(Integer goodId, String[] imgUrls) {
        for(String imgUrl: imgUrls){
            goodImgRepository.save(new GoodImg(goodId, imgUrl));
        }
    }

    @Override
    public List<GoodImg> selectGoodImgByGoodId(Integer goodId) {
        return goodImgRepository.findGoodImgsByGoodId(goodId);
    }

    @Override
    public void deleteGoodImgByGoodId(Integer goodId) {
        goodImgRepository.deleteGoodImgsByGoodId(goodId);
    }

    //文件格式判断
    private boolean isImg(String fileSuffix){
        int i = 0;

        for(String imgType: IMG_TYPE){
            if(imgType.equalsIgnoreCase(fileSuffix)){
                break;
            }
            i++;
        }

        return i != IMG_TYPE.size();
    }
}
