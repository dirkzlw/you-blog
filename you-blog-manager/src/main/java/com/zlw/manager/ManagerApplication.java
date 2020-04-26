package com.zlw.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;


/**
 * @author Dirk
 * @date 2020-04-17 8:02
 */
@ImportResource({"classpath:dubbo-provider.xml"})
@EntityScan("com.zlw.common.po")
@SpringBootApplication
public class ManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ManagerApplication.class, args);
    }

}
