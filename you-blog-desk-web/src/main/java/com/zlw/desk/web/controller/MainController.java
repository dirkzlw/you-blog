package com.zlw.desk.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Dirk
 * @date 2020-04-23 15:40
 */
@Controller
public class MainController {

    @GetMapping("/")
    public String toIndex(){
        return "index";
    }

}
