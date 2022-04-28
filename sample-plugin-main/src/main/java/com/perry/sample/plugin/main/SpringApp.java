package com.perry.sample.plugin.main;

import cn.hutool.core.util.IdUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
@EnableCaching
public class SpringApp {

    public static void main(String[] args) {
        String simpleUUID = IdUtil.randomUUID();
        System.out.println("I'm a spring application! " + simpleUUID);
        SpringApplication.run(SpringApp.class, args);
    }

}
