package com.zlw.desk.service.impl;

import com.zlw.common.po.Blog;
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
@Service(value = "userService")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StringEncryptor encryptor;
    @Value("${user.init.headImgUrl}")
    private String USER_INIT_HEADIMGURL;

    /**
     * 获取用户排行
     *
     * @return
     */
    @Override
    public List<User> getUserRanks() {
        return userRepository.getUserRanks();
    }

    /**
     * 注册时保存用户
     *  @param username
     * @param email
     * @param password
     */
    @Override
    public User addUser(String username, String email, String password) {
        password = encryptor.encrypt(password);
        User user = new User(username, password, email, USER_INIT_HEADIMGURL, null);
        userRepository.save(user);
        return user;
    }

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public String login(String username, String password) {
        User user = userRepository.findByUsernameOrEmail(username,username);
        if (user == null) {
            return "username_error";
        }else if(user.getStatus() == 2){
            return "user_no";
        }else if(!password.equals(encryptor.decrypt(user.getPassword()))){
            return "password_error";
        }
        return "success";
    }

    /**
     * 根据用户名或邮箱查询用户
     * @param username
     * @return
     */
    @Override
    public User findUserByUsernameOrEmail(String username) {
        return userRepository.findByUsernameOrEmail(username, username);
    }

    /**
     * 更新积分
     * @param user
     */
    @Override
    public User updateScore(User user) {
        int score = 0;
        List<Blog> blogList = user.getBlogList();
        for (Blog blog : blogList) {
            //计算规则1/2
            if (blog.getArtType() == 1 || blog.getArtType() == 3) {
                score += 10;
            } else {
                score += 2;
            }
            //规则3
            score += blog.getZanNum();
            //规则4
            score += blog.getViewNum() / 100;
        }
        user.setScore(score);
        userRepository.save(user);

        return user;
    }

}
