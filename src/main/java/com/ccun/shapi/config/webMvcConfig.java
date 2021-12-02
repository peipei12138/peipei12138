package com.ccun.shapi.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class webMvcConfig implements WebMvcConfigurer {

    //静态资源文件夹下文件的映射路径
    private static final String PATH_MAPPING = "/goods_img/**";
    //静态资源文件夹路径 test: "file:/Users/tornado/Downloads/test/"
//    private static final String UPLOAD_PATH  = "file:/home/webapp/2hand Api/data/images/";
    private static final String UPLOAD_PATH  = "file:/Users/tornado/Downloads/test/";

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(PATH_MAPPING).addResourceLocations(UPLOAD_PATH);
    }
}
