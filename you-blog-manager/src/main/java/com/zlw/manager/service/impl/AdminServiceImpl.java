package com.zlw.manager.service.impl;

import com.zlw.manager.dao.AdminRepository;
import com.zlw.manager.po.Admin;
import com.zlw.manager.service.AdminService;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dirk
 * @date 2020-04-18 9:02
 */
@Service(value = "adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private StringEncryptor encryptor;

    /**
     * 管理员登录
     * @param admin
     * @return
     */
    @Override
    public String login(Admin admin) {

        Admin one = adminRepository.findByUsername(admin.getUsername());
        if(null == one){
            return "username_error";
        }else if(!admin.getPassword().equals(encryptor.decrypt(one.getPassword()))){
            return "password_error";
        }

        return "success";
    }
}
