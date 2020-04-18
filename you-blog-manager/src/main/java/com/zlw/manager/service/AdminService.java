package com.zlw.manager.service;

import com.zlw.manager.po.Admin;

/**
 * @author Dirk
 * @date 2020-04-18 9:02
 */
public interface AdminService {
    String login(Admin admin);

    String resetUsername(String username,String newUsername);

    String resetPassword(String username, String oldPassword, String newPassword);
}
