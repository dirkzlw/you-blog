package com.zlw.manager.service;

import com.zlw.common.po.Blog;
import com.zlw.common.vo.Page;

/**
 * @author Dirk
 * @date 2020-05-08 16:34
 */
public interface BlogService {
    Page<Blog> findBlogBySearchAndPage(String search, Integer page);

    String delBlog(Integer blogId);
}
