package com.zlw.common;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author Dirk
 * @date 2020-04-26 12:37
 */
@SpringBootApplication
public class CommonApplication {

    public static void main(String[] args){
        SpringApplication.run(CommonApplication.class, args);
    }

}
