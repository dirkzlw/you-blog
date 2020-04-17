package com.zlw.manager.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author Dirk
 * @date 2020-04-17 8:02
 */
@ImportResource({"classpath:dubbo-consumer.xml"})
@SpringBootApplication
public class ManagerWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerWebApplication.class, args);
    }

}
