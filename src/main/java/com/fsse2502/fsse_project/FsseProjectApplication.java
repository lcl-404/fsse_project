package com.fsse2502.fsse_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
//@EnableCaching
public class FsseProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(FsseProjectApplication.class, args);
    }

}
