package com.mantou.boot;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * SpringBoot 启动类
 * @author mantou
 */
@SpringBootApplication
@MapperScan(basePackages = "com.mantou.boot.mapper")
public class ManTouBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManTouBootApplication.class, args);
    }
}
