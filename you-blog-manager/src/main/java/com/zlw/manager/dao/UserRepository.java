package com.zlw.manager.dao;

import com.zlw.manager.po.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Dirk
 * @date 2020-04-19 16:29
 */
public interface UserRepository extends JpaRepository<User,Integer> {

    User findByUsername(String u);

    User findByEmail(String e);

}
