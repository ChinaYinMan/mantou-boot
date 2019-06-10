package com.mantou.boot.config.mybatis;


import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

/**
 * 配置类
 *
 * @author mantou
 */
@Configuration
public class MybatisPlusConfig {


    /**
     * SQL执行效率插件
     * 设置 dev test 环境开启
     */
    @Bean
    @Profile({"dev", "test"})
    public PerformanceInterceptor performanceInterceptor() {

        //SQL 执行性能分析，开发环境使用，线上不推荐
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        //SQL是否格式化 默认false
        performanceInterceptor.setFormat(false);
        //maxTime 指的是 sql 最大执行时长 s
        performanceInterceptor.setMaxTime(1000);

        return performanceInterceptor;
    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        //分页插件
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();

        String dialectType = "mysql";
        paginationInterceptor.setDialectType(dialectType);
        return paginationInterceptor;
    }

}
