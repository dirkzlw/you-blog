package com.zlw.desk.service.impl;

import com.zlw.common.po.Blog;
import com.zlw.common.po.Tag;
import com.zlw.common.utils.DateUtils;
import com.zlw.common.vo.Page;
import com.zlw.desk.dao.BlogRepository;
import com.zlw.desk.service.BlogService;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Dirk
 * @date 2020-04-26 9:55
 */
@Service(value = "blogService")
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Value("${blog.page.size}")
    private Integer BLOG_PAGE_SIZE;

    /**
     * 添加博客
     * @param blog
     * @param tag
     * @param coverImgUrl
     * @return
     */
    @Override
    public String addBlog(Blog blog, Tag tag, String coverImgUrl) {

        blog.setTag(tag);
        blog.setCoverImgUrl(coverImgUrl);
        blog.setCreateTime(DateUtils.getStringTime());
        blogRepository.save(blog);

        return "success";
    }

    /**
     * 分页检索博客
     * @param page
     * @param search
     * @return
     */
    @Override
    public Page<Blog> findBlogByPageAndSearch(Integer page, String search) {
        List<Blog> blogList = blogRepository.findBlogByPageAndSearch(search, page * BLOG_PAGE_SIZE, BLOG_PAGE_SIZE);
        int totalElements = blogRepository.countBlogBySearch( search);
        int totalPages = (int) Math.ceil(totalElements * 1.0 / BLOG_PAGE_SIZE);
        Page<Blog> userPage = new Page<>(blogList, page, totalPages, totalElements, BLOG_PAGE_SIZE);
        return userPage;
    }
}
