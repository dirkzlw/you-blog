package com.zlw.manager.dao;

import com.zlw.manager.po.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Dirk
 * @date 2020-04-19 16:29
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByUserId(Integer userId);

    User findByUsername(String u);

    User findByEmail(String e);

    //自定义检索并分页
    @Query(nativeQuery = true,value = "SELECT u.* " +
                    "FROM t_user AS u,t_staff AS s " +
                    "WHERE u.staff_id=s.staff_id " +
                    "AND u.status=?1 " +
                    "AND (u.username LIKE CONCAT('%',?2,'%') OR s.staff_no LIKE CONCAT('%',?2,'%') OR s.realname LIKE CONCAT('%',?2,'%')) " +
                    "LIMIT ?3,?4 ;")
    List<User> findUserByStatusAndSearchAndPage(Integer status,String search, Integer start, Integer pageSize);

    //根据用户状态和关键字统计个数
    @Query(nativeQuery = true,value = "SELECT count(*) " +
            "FROM t_user AS u,t_staff AS s " +
            "WHERE u.staff_id=s.staff_id " +
            "AND u.status=?1 " +
            "AND (u.username LIKE CONCAT('%',?2,'%') OR s.staff_no LIKE CONCAT('%',?2,'%') OR s.realname LIKE CONCAT('%',?2,'%'))")
    Integer countUserByStatusAndSearch(Integer status,String search);

}
