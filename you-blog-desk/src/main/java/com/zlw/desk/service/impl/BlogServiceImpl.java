package com.zlw.desk.service.impl;

import com.zlw.common.po.Blog;
import com.zlw.common.po.Tag;
import com.zlw.common.po.User;
import com.zlw.common.utils.DateUtils;
import com.zlw.common.vo.Page;
import com.zlw.common.vo.ResultObj;
import com.zlw.desk.dao.BlogRepository;
import com.zlw.desk.service.BlogService;
import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
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
     *
     * @param blog
     * @param tag
     * @param coverImgUrl
     * @return
     */
    @Override
    public ResultObj addBlog(Blog blog, Tag tag, String coverImgUrl, User user) {

        blog.setTag(tag);
        blog.setCoverImgUrl(coverImgUrl);
        blog.setCreateTime(DateUtils.getStringTime());
        blog.setUser(user);
        blogRepository.save(blog);

        return new ResultObj("success", blog.getBlogId());
    }

    /**
     * 分页检索博客
     *
     * @param page
     * @param search
     * @return
     */
    @Override
    public Page<Blog> findBlogByPageAndSearch(Integer page, String search) {
        List<Blog> blogList = blogRepository.findBlogByPageAndSearch(search, page * BLOG_PAGE_SIZE, BLOG_PAGE_SIZE);
        int totalElements = blogRepository.countBlogBySearch(search);
        int totalPages = (int) Math.ceil(totalElements * 1.0 / BLOG_PAGE_SIZE);
        Page<Blog> userPage = new Page<>(blogList, page, totalPages, totalElements, BLOG_PAGE_SIZE);
        return userPage;
    }

    @Override
    public Blog findBlogById(Integer blogId) {
        return blogRepository.findByBlogId(blogId);
    }

    /**
     * 指定博客增加一次访问量
     *
     * @param blogId
     */
    @Override
    public void addViewNum(Integer blogId) {
        Blog blog = blogRepository.findByBlogId(blogId);
        blog.setViewNum(blog.getViewNum() + 1);
        blogRepository.save(blog);
    }

    /**
     * 博客点赞
     * @param blogId
     */
    @Override
    public void zanBlog(Integer blogId) {
        Blog blog = blogRepository.findByBlogId(blogId);
        blog.setZanNum(blog.getZanNum()+1);
        blogRepository.save(blog);
    }

    /**
     * 获取博客排行
     * @return
     */
    @Override
    public List<Blog> getUserRanks() {
        return blogRepository.getBlogRanks();
    }

    /**
     * 根据用户查询博客列表
     * @param userId
     * @return
     */
    @Override
    public List<Blog> findBlogByUserId(Integer userId) {

        return blogRepository.findBlogByUserId(userId);
    }

    /**
     * 根据标签获取博客列表
     * @param tagId
     * @return
     */
    @Override
    public List<Blog> findBlogByTagId(Integer tagId) {
        return blogRepository.findBlogByTagId(tagId);
    }
}
