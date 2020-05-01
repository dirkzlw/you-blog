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

    Blog findByBlogId(Integer blogId);

    List<Blog> findByCreateTimeContaining(String createTime);

    //自定义检索并分页
    @Query(nativeQuery = true,
            value = "SELECT * FROM t_blog AS b " +
                    "WHERE b.title LIKE CONCAT('%',?1,'%') " +
                    "order by blog_id desc LIMIT ?2,?3 ;")
    List<Blog> findBlogByPageAndSearch(String search, Integer start, Integer pageSize);

    //自定义检索统计数据
    @Query(nativeQuery = true,
            value = "SELECT count(*) FROM t_blog AS b " +
                    "WHERE b.title LIKE CONCAT('%',?1,'%') ;")
    Integer countBlogBySearch(String search);

    //获取博客排行榜
    @Query(nativeQuery = true,value = "SELECT * FROM t_blog " +
            "ORDER BY view_num DESC " +
            "LIMIT 0,5 ;")
    List<Blog> getBlogRanks();

    //查询指定用户的博客列表
    @Query(nativeQuery = true,value = "SELECT * FROM t_blog AS b WHERE b.user_id=?1 ;")
    List<Blog> findBlogByUserId(Integer userId);

    //查询指定标签的博客列表
    @Query(nativeQuery = true,value = "SELECT * FROM t_blog AS b WHERE b.tag_id=?1 ;")
    List<Blog> findBlogByTagId(Integer tagId);

}
