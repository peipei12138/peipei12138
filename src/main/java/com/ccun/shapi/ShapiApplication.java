package com.ccun.shapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan
@SpringBootApplication
public class ShapiApplication {


    public static void main(String[] args) {
        SpringApplication.run(ShapiApplication.class, args);
    }

}
