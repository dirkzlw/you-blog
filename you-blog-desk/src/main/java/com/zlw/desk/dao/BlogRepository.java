package com.zlw.desk.dao;

import com.zlw.common.po.Blog;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Dirk
 * @date 2020-04-26 9:54
 */
public interface BlogRepository extends JpaRepository<Blog,Integer> {

    //自定义检索并分页
    @Query(nativeQuery = true,
            value = "SELECT * FROM t_blog AS b " +
                    "WHERE b.title LIKE CONCAT('%',?1,'%') " +
                    "LIMIT ?2,?3 ;")
    List<Blog> findBlogByPageAndSearch(String search, Integer start, Integer pageSize);

    //自定义检索统计数据
    @Query(nativeQuery = true,
            value = "SELECT count(*) FROM t_blog AS b " +
                    "WHERE b.title LIKE CONCAT('%',?1,'%') ;")
    Integer countBlogBySearch(String search);

}
