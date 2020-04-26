package com.zlw.manager.web.controller;

import com.zlw.common.po.Admin;
import com.zlw.manager.service.AdminService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Dirk
 * @date 2020-04-18 7:49
 */
@Controller
public class AdminController {

    @Autowired(required = false)
    private AdminService adminService;

    /**
     * 获取登录页面
     *
     * @return
     */
    @GetMapping("/to/login")
    public String toLogin() {
        return "admin/login";
    }

    /**
     * 管理员登录
     *
     * @param admin
     * @return
     */
    @PostMapping("/admin/login")
    @ResponseBody
    public String adminLogin(Admin admin, HttpServletRequest request) {

        String rtn;
        if (admin.getUsername() == null || admin.getPassword() == null) {
            rtn = "fail";
        } else {
            rtn = adminService.login(admin);
            // 如果登录成功，将admin加入session
            if ("success".equals(rtn)) {
                request.getSession().setAttribute("username", admin.getUsername());
            }
        }

        return rtn;

    }

    /**
     * 展示管理员信息页面
     *
     * @return
     */
    @GetMapping("/admin-show")
    public String toAdminShow() {
        return "admin/admin-show";
    }

    /**
     * 管理员重置用户名
     *
     * @return
     */
    @PostMapping("/admin/username/reset")
    @ResponseBody
    public String usernameReset(String newUsername, HttpServletRequest request) {

        String rtn;

        if (null == newUsername) {
            rtn = "fail";
        } else {
            HttpSession session = request.getSession();
            String username = (String) session.getAttribute("username");
            rtn = adminService.resetUsername(username, newUsername);
            // 修改成功则更新session
            if ("success".equals(rtn)) {
                session.setAttribute("username", newUsername);
            }
        }
        return rtn;
    }

    /**
     * 管理员修改密码
     *
     * @param oldPassword
     * @param newPassword
     * @return
     */
    @PostMapping("/admin/password/reset")
    @ResponseBody
    public String resetPassword(String oldPassword, String newPassword,
                                HttpServletRequest request) {
        String rtn;
        if (oldPassword == null || newPassword == null) {
            rtn = "fail";
        } else {
            rtn = adminService.resetPassword((String) request.getSession().getAttribute("username"),
                    oldPassword, newPassword);
        }
        return rtn;
    }

    /**
     * 退出登录
     * @return
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest request){
        request.getSession().removeAttribute("username");

        return "redirect:/";
    }

}
