package com.zlw.desk.service;

import com.zlw.common.po.Blog;
import com.zlw.common.po.Tag;
import com.zlw.common.vo.Page;

/**
 * @author Dirk
 * @date 2020-04-26 9:55
 */
public interface BlogService {
    String addBlog(Blog blog, Tag tag, String coverImgUrl);

    Page<Blog> findBlogByPageAndSearch(Integer page, String search);

    Blog findBlogById(Integer blogId);

    void addViewNum(Integer blogId);

    void zanBlog(Integer blogId);
}
