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
import java.util.Comparator;
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

    @Value("${index.page.size}")
    private Integer INDEX_PAGE_SIZE;

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
     * 编辑博客，修改封面
     * @param blog
     * @param tag
     * @param coverImgUrl
     * @return
     */
    @Override
    public ResultObj editBlog(Blog blog, Tag tag, String coverImgUrl) {

        Blog oldBlog = blogRepository.findByBlogId(blog.getBlogId());
        oldBlog.setTitle(blog.getTitle());
        oldBlog.setContent(blog.getContent());
        oldBlog.setText(blog.getText());
        oldBlog.setArtType(blog.getArtType());
        oldBlog.setTag(tag);
        oldBlog.setCoverImgUrl(coverImgUrl);
        blogRepository.save(oldBlog);

        return new ResultObj("success", blog.getBlogId());
    }

    /**
     * 编辑博客，未修改封面
     * @param blog
     * @param tag
     * @return
     */
    @Override
    public ResultObj editBlog(Blog blog, Tag tag) {

        Blog oldBlog = blogRepository.findByBlogId(blog.getBlogId());
        oldBlog.setTitle(blog.getTitle());
        oldBlog.setContent(blog.getContent());
        oldBlog.setText(blog.getText());
        oldBlog.setArtType(blog.getArtType());
        oldBlog.setTag(tag);
        blogRepository.save(oldBlog);

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

        List<Blog> blogList = blogRepository.findBlogByPageAndSearch(search, page * INDEX_PAGE_SIZE, INDEX_PAGE_SIZE);
        int totalElements = blogRepository.countBlogBySearch(search);
        int totalPages = (int) Math.ceil(totalElements * 1.0 / INDEX_PAGE_SIZE);
        Page<Blog> blogPage = new Page<>(blogList, page, totalPages, totalElements, INDEX_PAGE_SIZE);

        return blogPage;
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

    /**
     * 获取今日推荐
     * @return
     */
    @Override
    public Blog findBlogTodayRecommander() {

        //获取今日创建的博客列表
        List<Blog> blogList = blogRepository.findByCreateTimeContaining(DateUtils.getStringTime2());
        if (blogList.size() == 0) {
            return null;
        }else {
            //倒序排序
            Collections.sort(blogList, new Comparator<Blog>() {
                @Override
                public int compare(Blog o1, Blog o2) {
                    return o2.getViewNum()-o1.getViewNum();
                }
            });
            return blogList.get(0);
        }
    }

    /**
     * 根据id删除博客
     * @param blogId
     * @return
     */
    @Override
    public String delBlog(Integer blogId) {
        blogRepository.deleteById(blogId);
        return "success";
    }

}
