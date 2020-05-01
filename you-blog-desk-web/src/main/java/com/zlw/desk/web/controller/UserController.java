package com.zlw.desk.web.controller;

import com.zlw.common.po.Blog;
import com.zlw.common.po.User;
import com.zlw.common.utils.SessionUtils;
import com.zlw.common.vo.SessionUser;
import java.util.List;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;
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

    /**
     * 注册
     * @param username
     * @param email
     * @param password
     * @param session
     * @return
     */
    @PostMapping("/register")
    @ResponseBody
    public String register(String username, String email, String password,
                           HttpSession session) {

        if (username == null || password == null || email == null) {
            return "fail";
        }else if (userServiceManager.findUserByUsername(username) !=null){
            return "username_repeat";
        } else if (userServiceManager.findUserByEmail(email) != null) {
            return "email_repeat";
        }else {
            User user = userServiceDesk.addUser(username,email,password);
            //user转sessionUser，保存在session
            SessionUtils.userToSessionUser(session, user);
        }

        return "success";
    }

    /**
     * 登录
     * @param username
     * @param password
     * @param session
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public String login(String username, String password,HttpSession session) {
        String rtn;
        if (username == null || password == null) {
            rtn = "fail";
        } else {
            rtn = userServiceDesk.login(username, password);
            if ("success".equals(rtn)) {
                SessionUtils.userToSessionUser(session, userServiceDesk.findUserByUsernameOrEmail(username));
            }
        }
        return rtn;
    }

}
