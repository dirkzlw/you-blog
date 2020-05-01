package com.zlw.desk.service.impl;

import com.zlw.common.po.User;
import com.zlw.desk.dao.UserRepository;
import com.zlw.desk.service.UserService;
import java.util.List;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Dirk
 * @date 2020-04-29 16:15
 */
@Service(value="userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StringEncryptor encryptor;
    @Value("${user.init.headImgUrl}")
    private String USER_INIT_HEADIMGURL;
    /**
     * 获取用户排行
     * @return
     */
    @Override
    public List<User> getUserRanks() {
        return userRepository.getUserRanks();
    }

    /**
     * 注册时保存用户
     * @param username
     * @param email
     * @param password
     */
    @Override
    public void addUser(String username, String email, String password) {
        password = encryptor.encrypt(password);
        User user = new User(username, password, email, USER_INIT_HEADIMGURL, null);
        userRepository.save(user);
    }
}
