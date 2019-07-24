package com.example.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.example.demo.mapper")
public class MybatisConfiguration {


    public MybatisConfiguration(){
        System.out.println("MybatisConfiguration对象创建·······");
    }
}
