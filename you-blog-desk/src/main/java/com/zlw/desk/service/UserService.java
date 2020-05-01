package com.zlw.desk.service;

import com.zlw.common.po.User;
import java.util.List;

/**
 * @author Dirk
 * @date 2020-04-29 16:14
 */
public interface UserService {
    List<User> getUserRanks();

    User addUser(String username, String email, String password);

    String login(String username, String password);

    User findUserByUsernameOrEmail(String username);
}
