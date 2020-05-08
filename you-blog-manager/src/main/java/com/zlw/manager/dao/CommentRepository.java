package com.zlw.manager.dao;

import com.zlw.common.po.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Dirk
 * @date 2020-05-08 18:41
 */
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    //自定义检索并分页
    @Query(nativeQuery = true,value = "SELECT c.* " +
            "FROM t_comment AS c " +
            "WHERE c.content LIKE CONCAT('%',?1,'%') " +
            "LIMIT ?2,?3 ;")
    List<Comment> findBySearchAndPage(String search, int i, Integer page_size);

    @Query(nativeQuery = true,value = "SELECT count(*) " +
            "FROM t_comment AS c " +
            "WHERE c.content LIKE CONCAT('%',?1,'%') ;")
    Integer countCommentByStatusAndSearch(String search);
}
