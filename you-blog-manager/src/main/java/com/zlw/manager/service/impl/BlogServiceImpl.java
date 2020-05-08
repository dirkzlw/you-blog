package com.zlw.manager.service.impl;

import com.zlw.common.po.Blog;
import com.zlw.common.vo.Page;
import com.zlw.manager.dao.BlogRepository;
import com.zlw.manager.service.BlogService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Dirk
 * @date 2020-05-08 16:34
 */
@Service(value = "blogService")
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Value("${data.page.size}")
    private Integer PAGE_SIZE;
    /**
     * 分页查询博客
     * @param search
     * @param page
     * @return
     */
    @Override
    public Page<Blog> findBlogBySearchAndPage(String search, Integer page) {
        List<Blog> blogList = blogRepository.findBySearchAndPage(search, page * PAGE_SIZE, PAGE_SIZE);
        int totalElements = blogRepository.countUserNoByStatusAndSearch(search);
        int totalPages = (int) Math.ceil(totalElements * 1.0 / PAGE_SIZE);
        Page<Blog> blogPage = new Page<>(blogList, page, totalPages, totalElements, PAGE_SIZE);

        return blogPage;
    }

    /**
     * 删除博客
     * @param blogId
     * @return
     */
    @Override
    public String delBlog(Integer blogId) {
        blogRepository.deleteById(blogId);
        return "success";
    }
}
