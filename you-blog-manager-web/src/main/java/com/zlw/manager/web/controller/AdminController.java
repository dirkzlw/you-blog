package com.zlw.manager.web.controller;

import com.zlw.manager.po.Admin;
import com.zlw.manager.service.AdminService;
import javax.servlet.http.HttpServletRequest;
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
     * @return
     */
    @GetMapping("/to/login")
    public String toLogin(){
        return "admin/login";
    }

    /**
     * 管理员登录
     * @param admin
     * @return
     */
    @PostMapping("/admin/login")
    @ResponseBody
    public String adminLogin(Admin admin,HttpServletRequest request){

        String rtn;
        if(admin.getUsername() == null || admin.getPassword() == null){
            rtn = "fail";
        }else {
            rtn = adminService.login(admin);
            // 如果登录成功，将admin加入session
            if("success".equals(rtn)){
                request.getSession().setAttribute("admin", admin);
            }
        }

        return rtn;

    }

    /**
     * 展示管理员信息页面
     * @return
     */
    @GetMapping("/admin-show")
    public String toAdminShow(){
        return "admin/admin-show";
    }

    /**
     * 管理员重置用户名
     * @return
     */
    @PostMapping("/admin/username/reset")
    @ResponseBody
    public String usernameReset(String newUsername) {
        if(null == newUsername){
            return "fail";
        }else {
            return "success";
        }
    }
}
