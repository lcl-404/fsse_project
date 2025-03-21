package com.fsse2502.fsse_project;

import com.fsse2502.fsse_project.data.transaction.entity.TransactionEntity;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Timestamp;
import java.time.LocalTime;

@SpringBootApplication
public class FsseProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(FsseProjectApplication.class, args);
    }

}
