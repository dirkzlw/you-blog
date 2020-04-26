package com.zlw.desk.service.impl;

import com.zlw.common.utils.DateUtils;
import com.zlw.desk.dao.BlogRepository;
import com.zlw.desk.service.BlogService;
import com.zlw.manager.po.Blog;
import com.zlw.manager.po.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Dirk
 * @date 2020-04-26 9:55
 */
@Service(value = "blogService")
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

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
}
