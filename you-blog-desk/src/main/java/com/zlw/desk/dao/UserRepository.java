package com.zlw.desk.dao;

import com.zlw.common.po.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Dirk
 * @date 2020-04-29 16:14
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    //获取用户排行
    @Query(nativeQuery = true,value = "SELECT * FROM t_user " +
            "ORDER BY score DESC " +
            "LIMIT 0,5 ;")
    List<User> getUserRanks();

    User findByUsernameOrEmail(String username,String email);

}
