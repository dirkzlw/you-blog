package com.zlw.desk.service.impl;

import com.zlw.common.po.User;
import com.zlw.desk.dao.UserRepository;
import com.zlw.desk.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dirk
 * @date 2020-04-29 16:15
 */
@Service(value="userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    /**
     * 获取用户排行
     * @return
     */
    @Override
    public List<User> getUserRanks() {
        return userRepository.getUserRanks();
    }
}
