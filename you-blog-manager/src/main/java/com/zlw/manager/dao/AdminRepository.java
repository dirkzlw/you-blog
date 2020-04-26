package com.zlw.manager.dao;

import com.zlw.common.po.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Dirk
 * @date 2020-04-18 8:40
 */
public interface AdminRepository extends JpaRepository<Admin,Integer> {

    Admin findByUsername(String username);

}
