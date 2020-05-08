package com.zlw.manager.dao;

import com.zlw.common.po.Blog;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Dirk
 * @date 2020-05-08 16:35
 */
public interface BlogRepository extends JpaRepository<Blog,Integer> {

    //自定义检索并分页
    @Query(nativeQuery = true,value = "SELECT b.* " +
            "FROM t_blog AS b " +
            "WHERE b.title LIKE CONCAT('%',?1,'%') " +
            "LIMIT ?2,?3 ;")
    List<Blog> findBySearchAndPage(String search, int i, Integer page_size);

    @Query(nativeQuery = true,value = "SELECT count(*) " +
            "FROM t_blog AS b " +
            "WHERE b.title LIKE CONCAT('%',?1,'%') ;")
    Integer countBlogByStatusAndSearch(String search);
}
