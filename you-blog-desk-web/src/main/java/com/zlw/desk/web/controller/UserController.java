package com.zlw.desk.web.controller;

import com.zlw.common.po.Blog;
import com.zlw.common.po.User;
import com.zlw.common.utils.SessionUtils;
import com.zlw.common.vo.SessionUser;
import com.zlw.desk.service.BlogService;
import com.zlw.manager.service.AttentionService;
import com.zlw.manager.service.NoticeService;
import com.zlw.manager.service.TagService;
import java.security.Signature;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    private NoticeService noticeService;
    @Autowired(required = false)
    private TagService tagService;
    @Autowired(required = false)
    private AttentionService attentionService;
    @Autowired(required = false)
    private BlogService blogService;
    @Autowired(required = false)
    private com.zlw.manager.service.UserService userServiceManager;
    @Autowired(required = false)
    private com.zlw.desk.service.UserService userServiceDesk;

    @GetMapping("/to/login")
    public String toLogin() {
        return "user/login";
    }

    /**
     * 注册
     *
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
        } else if (userServiceManager.findUserByUsername(username) != null) {
            return "username_repeat";
        } else if (userServiceManager.findUserByEmail(email) != null) {
            return "email_repeat";
        } else {
            User user = userServiceDesk.addUser(username, email, password);
            //user转sessionUser，保存在session
            SessionUtils.userToSessionUser(session, user);
        }

        return "success";
    }

    /**
     * 登录
     *
     * @param username
     * @param password
     * @param session
     * @return
     */
    @PostMapping("/login")
    @ResponseBody
    public String login(String username, String password, HttpSession session) {
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

    /**
     * 展示用户个人信息
     *
     * @param request
     * @param model
     * @return
     */
    @GetMapping("/user/self")
    public String toUserSelf(HttpServletRequest request, Model model) {

        HttpSession session = request.getSession();
        MainController.sessionAddThreeList(session, noticeService, tagService, attentionService);
        //获取博客列表
        //获取用户排行榜
        List<User> userRanks = userServiceDesk.getUserRanks();
        //获取博客排行榜
        List<Blog> blogRanks = blogService.getUserRanks();
        //获取今日推荐
        Blog blogRecommender = blogService.findBlogTodayRecommander();
        model.addAttribute("userRanks", userRanks);
        model.addAttribute("blogRanks", blogRanks);
        model.addAttribute("blogRecommender", blogRecommender);

        return "user/self";
    }

    /**
     * 用户修改用户名
     *
     * @param userId
     * @param newUsername
     * @param session
     * @return
     */
    @PostMapping("/user/username/reset")
    @ResponseBody
    public String usernameReset(Integer userId, String newUsername,
                                HttpSession session) {
        String rtn;
        if (userId == null || newUsername == null) {
            rtn = "fail";
        } else {
            rtn = userServiceDesk.resetUsername(userId, newUsername);
            if ("success".equals(rtn)) {
                //用户名修改成功，更新session
                SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
                sessionUser.setUsername(newUsername);
                session.setAttribute("sessionUser", sessionUser);
            }
        }
        return rtn;
    }

    /**
     * 用户修改邮箱
     *
     * @param userId
     * @param newEmail
     * @param session
     * @return
     */
    @PostMapping("/user/email/reset")
    @ResponseBody
    public String emailReset(Integer userId, String newEmail,
                             HttpSession session) {
        String rtn;
        if (userId == null || newEmail == null) {
            rtn = "fail";
        } else {
            rtn = userServiceDesk.resetEmail(userId, newEmail);
            if ("success".equals(rtn)) {
                //用户名修改成功，更新session
                SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
                sessionUser.setEmail(newEmail);
                session.setAttribute("sessionUser", sessionUser);
            }
        }
        return rtn;
    }

    /**
     * 用户修改密码
     *
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @PostMapping("/user/password/reset")
    @ResponseBody
    public String resetPassword(Integer userId, String oldPassword, String newPassword) {
        String rtn;
        if (userId == null || oldPassword == null || newPassword == null) {
            rtn = "fail";
        } else {
            rtn = userServiceDesk.resetPassword(userId,oldPassword, newPassword);
        }
        return rtn;
    }

    @PostMapping("/user/signStr/reset")
    @ResponseBody
    public String resetSignReset(Integer userId, String newSignStr,
                                 HttpSession session) {
        String rtn;
        if (userId == null || newSignStr == null) {
            rtn = "fail";
        }else {
            rtn = userServiceDesk.resetSignStr(userId, newSignStr);
            if ("success".equals(rtn)) {
                //用户名修改成功，更新session
                SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
                sessionUser.setSignStr(newSignStr);
                session.setAttribute("sessionUser", sessionUser);
            }
        }
        return rtn;
    }
}
