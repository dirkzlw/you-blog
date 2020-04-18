package com.zlw.manager.web.controller;

import com.zlw.manager.po.Admin;
import com.zlw.manager.service.NoticeService;
import javax.servlet.http.HttpServletRequest;
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
    public String toIndex(HttpServletRequest request){

        //判断是否登录，未登录则重定向至登录
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        if(null == admin){
            return "redirect:/to/login";
        }

        return "index";
    }

    /**
     * 获取我的桌面页面
     * @return
     */
    @GetMapping("/desktop")
    public String toDesktop(){
        return "desktop";
    }

}
