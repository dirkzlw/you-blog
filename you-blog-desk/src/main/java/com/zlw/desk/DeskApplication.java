package com.zlw.desk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ImportResource;


/**
 * @author Dirk
 * @date 2020-04-17 8:02
 */
@ImportResource({"classpath:dubbo-provider.xml"})
@EntityScan("com.zlw.common.po")
@SpringBootApplication
public class DeskApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeskApplication.class, args);
    }

}
