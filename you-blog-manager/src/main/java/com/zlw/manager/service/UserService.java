package com.zlw.manager.service;

import com.zlw.common.vo.Page;
import com.zlw.manager.po.Staff;
import com.zlw.manager.po.User;

/**
 * @author Dirk
 * @date 2020-04-19 16:30
 */
public interface UserService {

    void saveUser(User user);

    User findUserByUsername(String username);

    User findUserByEmail(String email);

    void addUser(Staff staff, String email);

    Page<User> findUserByStatusAndSearchAndPage(Integer status, String search,Integer page);

    void noUser(Integer userId);

    void yesUser(Integer userId);

    User findUserById(Integer userId);
}
