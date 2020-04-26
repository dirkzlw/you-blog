package com.zlw.desk.service;

import com.zlw.manager.po.Blog;
import com.zlw.manager.po.Tag;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Dirk
 * @date 2020-04-26 9:55
 */
public interface BlogService {
    String addBlog(Blog blog, Tag tag, String coverImgUrl);
}
