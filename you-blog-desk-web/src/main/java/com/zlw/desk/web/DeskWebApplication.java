package com.zlw.desk.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Dirk
 * @date 2020-04-17 8:02
 */

@ImportResource({"classpath:dubbo-consumer.xml"})
@ServletComponentScan   //扫描三大器,不然过滤器会过滤所有URL
@SpringBootApplication
public class DeskWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeskWebApplication.class, args);
    }

}
