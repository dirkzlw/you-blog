package com.zlw.manager.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.zlw.manager.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Dirk
 * @date 2020-04-17 8:04
 */
@Controller
public class MainController {

    @Autowired(required = false)
    private NoticeService noticeService;
    /**
     * 跳转至主页
     * @return
     */
    @GetMapping("/")
    public String toIndex(){

        noticeService.test();

        return "index";
    }


}
