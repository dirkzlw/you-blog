package com.zlw.desk.service;

import com.zlw.common.po.Blog;
import com.zlw.common.po.Tag;
import com.zlw.common.po.User;
import com.zlw.common.vo.Page;
import com.zlw.common.vo.ResultObj;
import java.util.List;

/**
 * @author Dirk
 * @date 2020-04-26 9:55
 */
public interface BlogService {
    ResultObj addBlog(Blog blog, Tag tag, String coverImgUrl, User user);

    Page<Blog> findBlogByPageAndSearch(Integer page, String search);

    Blog findBlogById(Integer blogId);

    void addViewNum(Integer blogId);

    void zanBlog(Integer blogId);

    List<Blog> getUserRanks();

    List<Blog> findBlogByUserId(Integer userId);

    List<Blog> findBlogByTagId(Integer tagId);

    Blog findBlogTodayRecommander();
}
