package com.zlw.manager.web;

import com.zlw.common.vo.Page;
import com.zlw.manager.po.Staff;
import com.zlw.manager.po.User;
import com.zlw.manager.service.StaffService;
import com.zlw.manager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Dirk
 * @date 2020-04-19 16:33
 */
@Controller
public class UserController {

    @Autowired(required = false)
    private UserService userService;
    @Autowired(required = false)
    private StaffService staffService;

    /**
     * 获取用户管理界面
     *
     * @return
     */
    @GetMapping("/user-list")
    public String toUserList(@RequestParam(required = false, defaultValue = "") String search,
                             @RequestParam(required = false, defaultValue = "0") Integer page,
                             Model model) {

        Page<User> userPage = userService.findUserByStatusAndSearchAndPage(1, search, page);
        model.addAttribute("userPage", userPage);
        model.addAttribute("search", search);

        return "user/user-list";
    }

    /**
     * 获取添加用户界面
     *
     * @return
     */
    @GetMapping("/user-add")
    public String toUserAdd() {
        return "user/user-add";
    }

    /**
     * 添加用户
     *
     * @param staffNo
     * @param email
     * @return
     */
    @PostMapping("/user/add")
    @ResponseBody
    public String addUser(String staffNo, String email) {
        if (null == staffNo || null == email) {
            return "fail";
        } else {
            Staff staff = staffService.findStaffByStaffNo(staffNo);
            if (null == staff) {
                //判断工号是否存在
                return "no_staff";
            } else if (null != staff.getUser()) {
                //判断工号是否已分配
                return "exist_user";
            } else if (null != userService.findUserByEmail(email)) {
                //判断邮箱是否已存在
                return "exist_email";
            }
            userService.addUser(staff, email);
            return "success";
        }
    }

}
