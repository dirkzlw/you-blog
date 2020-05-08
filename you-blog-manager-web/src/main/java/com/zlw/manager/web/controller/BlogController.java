package com.zlw.manager.web.controller;

import com.zlw.common.po.Blog;
import com.zlw.common.vo.Page;
import com.zlw.manager.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Dirk
 * @date 2020-05-08 16:33
 */
@Controller
public class BlogController {

    @Autowired(required = false)
    private BlogService blogService;

    /**
     * 展示博客列表
     * @param search
     * @param page
     * @param model
     * @return
     */
    @GetMapping("/blog-list")
    private String toBlogList(@RequestParam(required = false, defaultValue = "") String search,
                              @RequestParam(required = false, defaultValue = "0") Integer page,
                              Model model){

        Page<Blog> blogPage = blogService.findBlogBySearchAndPage(search, page);
        model.addAttribute("blogPage", blogPage);
        model.addAttribute("search", search);

        return "blog/blog-list";
    }

    /**
     * 删除博客
     *
     * @param blogId
     * @return
     */
    @PostMapping("/blog/del")
    @ResponseBody
    public String delBlog(Integer blogId) {
        if (null == blogId) {
            return "fail";
        } else {
            return blogService.delBlog(blogId);
        }
    }
}
