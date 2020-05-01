package com.zlw.desk.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Dirk
 * @date 2020-05-01 8:00
 */
@Controller
public class UserController {

    @Autowired(required = false)
    private com.zlw.manager.service.UserService userServiceManager;
    @Autowired(required = false)
    private com.zlw.desk.service.UserService userServiceDesk;

    @GetMapping("/to/login")
    public String toLogin() {
        return "login";
    }

    @PostMapping("/register")
    @ResponseBody
    public String register(String username, String email, String password) {

        if (username == null || password == null || email == null) {
            return "fail";
        }else if (userServiceManager.findUserByUsername(username) !=null){
            return "username_repeat";
        } else if (userServiceManager.findUserByEmail(email) != null) {
            return "email_repeat";
        }else {
            userServiceDesk.addUser(username,email,password);
        }

        return "success";
    }

}
