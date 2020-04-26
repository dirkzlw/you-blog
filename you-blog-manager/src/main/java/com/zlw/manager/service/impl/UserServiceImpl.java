package com.zlw.manager.service.impl;

import com.zlw.common.utils.StringUtils;
import com.zlw.common.vo.Page;
import com.zlw.manager.dao.UserRepository;
import com.zlw.common.po.Staff;
import com.zlw.common.po.User;
import com.zlw.manager.service.UserService;
import java.util.List;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Dirk
 * @date 2020-04-19 16:30
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private StringEncryptor encryptor;
    @Value("${user.init.password}")
    private String USER_INIT_PASSWORD;
    @Value("${user.init.headImgUrl}")
    private String USER_INIT_HEADIMGURL;
    @Value("${data.page.size}")
    private Integer PAGE_SIZE;

    /**
     * 保存用户
     *
     * @param user
     */
    @Override
    public void saveUser(User user) {
        userRepository.save(user);
    }

    /**
     * 根据用户名查询用户
     *
     * @param username
     * @return
     */
    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * 根据邮箱查询用户
     *
     * @param email
     * @return
     */
    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * 管理员添加用户
     *
     * @param staff
     * @param email
     */
    @Override
    public void addUser(Staff staff, String email) {

        String username = StringUtils.getPinYin(staff.getRealname()) + staff.getStaffNo();
        String password = encryptor.encrypt(USER_INIT_PASSWORD);
        User user = new User(username, password, email, USER_INIT_HEADIMGURL, staff);
        userRepository.save(user);

    }

    /**
     * 分页查询可用用户
     *
     * @param page
     * @param search
     * @return
     */
    @Override
    public Page<User> findUserByStatusAndSearchAndPage(Integer status, String search, Integer page) {

        List<User> userList = userRepository.findUserByStatusAndSearchAndPage(status, search, page * PAGE_SIZE, PAGE_SIZE);
        int totalElements = userRepository.countUserByStatusAndSearch(status, search);
        int totalPages = (int) Math.ceil(totalElements * 1.0 / PAGE_SIZE);
        Page<User> userPage = new Page<>(userList, page, totalPages, totalElements, PAGE_SIZE);

        return userPage;
    }

    /**
     * 禁用用户
     * @param userId
     */
    @Override
    public void noUser(Integer userId) {
        User user = userRepository.findByUserId(userId);
        user.setStatus(2);
        userRepository.save(user);
    }

    /**
     * 启用用户
     * @param userId
     */
    @Override
    public void yesUser(Integer userId) {
        User user = userRepository.findByUserId(userId);
        user.setStatus(1);
        userRepository.save(user);
    }

    /**
     * 根据id查询用户
     * @param userId
     * @return
     */
    @Override
    public User findUserById(Integer userId) {
        return userRepository.findByUserId(userId);
    }

}
