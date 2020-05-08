package com.zlw.manager.web.controller;

import com.zlw.common.po.Staff;
import com.zlw.common.po.User;
import com.zlw.common.vo.Page;
import com.zlw.manager.service.StaffService;
import com.zlw.manager.service.UserService;
import java.util.List;
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
     * 获取内部员工管理界面
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
     * 获取普通用户管理界面
     *
     * @return
     */
    @GetMapping("/user2-list")
    public String toUser2List(@RequestParam(required = false, defaultValue = "") String search,
                             @RequestParam(required = false, defaultValue = "0") Integer page,
                             Model model) {

        Page<User> userPage = userService.findUser2ByStatusAndSearchAndPage(1, search, page);
        model.addAttribute("userPage", userPage);
        model.addAttribute("search", search);

        return "user/user2-list";
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

    /**
     * 禁用用户
     *
     * @param userId
     * @return
     */
    @PostMapping("/user/no")
    @ResponseBody
    public String noUser(Integer userId) {
        if (null == userId) {
            return "fail";
        } else {
            userService.noUser(userId);
            return "success";
        }
    }

    /**
     * 启用用户
     *
     * @param userId
     * @return
     */
    @PostMapping("/user/yes")
    @ResponseBody
    public String yesUser(Integer userId) {
        if (null == userId) {
            return "fail";
        } else {
            userService.yesUser(userId);
            return "success";
        }
    }

    /**
     * 获取编辑用户界面
     *
     * @param userId
     * @param model
     * @return
     */
    @GetMapping("/user-edit")
    public String toUserEdit(Integer userId, Model model) {
        User user = userService.findUserById(userId);
        model.addAttribute("user", user);
        return "user/user-edit";
    }

    /**
     * 编辑用户
     *
     * @param userId
     * @param email
     * @return
     */
    @PostMapping("/user/edit")
    @ResponseBody
    public String editUser(Integer userId, String email) {
        if (null == userId || null == email) {
            return "fail";
        } else {
            User userByEmail = userService.findUserByEmail(email);
            if (null != userByEmail && userId != userByEmail.getUserId()) {
                //判断邮箱是否已存在
                return "exist_email";
            } else if (null != userByEmail && userId == userByEmail.getUserId()) {
                //没有修改信息
                return "no_reset";
            }
            User user = userService.findUserById(userId);
            user.setEmail(email);
            userService.saveUser(user);
            return "success";
        }
    }

    /**
     * 获取用户禁用列表页面
     *
     * @return
     */
    @GetMapping("/user-no-list")
    public String toUserNoList(@RequestParam(required = false, defaultValue = "") String search,
                               @RequestParam(required = false, defaultValue = "0") Integer page,
                               Model model) {

        Page<User> userPage = userService.findUserNoByStatusAndSearchAndPage(2, search, page);
        model.addAttribute("userPage", userPage);
        model.addAttribute("search", search);

        return "user/user-no-list";
    }

}
