package com.zlw.desk.web.controller;

import com.zlw.common.po.Attention;
import com.zlw.common.po.Blog;
import com.zlw.common.po.Notice;
import com.zlw.common.po.Tag;
import com.zlw.common.vo.Page;
import com.zlw.desk.service.BlogService;
import com.zlw.manager.service.AttentionService;
import com.zlw.manager.service.NoticeService;
import com.zlw.manager.service.TagService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Dirk
 * @date 2020-04-23 15:40
 */
@Controller
public class MainController {

    @Autowired(required = false)
    private NoticeService noticeService;
    @Autowired(required = false)
    private TagService tagService;
    @Autowired(required = false)
    private AttentionService attentionService;
    @Autowired(required = false)
    private BlogService blogService;

    @Value("${ATTENTION_IMG_URL}")
    private String ATTENTION_IMG_URL;

    @GetMapping("/")
    public String toIndex(Model model,
                          @RequestParam(required = false, defaultValue = "0") Integer page,
                          @RequestParam(required = false, defaultValue = "") String search) {

        //获取公告列表
        List<Notice> noticeList = noticeService.findAllNotice();
        model.addAttribute("noticeList", noticeList);
        //获取标签列表
        List<Tag> tagList = tagService.findAllTag();
        model.addAttribute("tagList", tagList);
        //获取关注
        List<Attention> attentionList = attentionService.getAllAttention();
        if (attentionList.size() == 0) {
            model.addAttribute("attentionImgUrl", ATTENTION_IMG_URL);
        } else {
            model.addAttribute("attentionImgUrl", attentionList.get(0).getImgUrl());
        }
        //获取博客列表
        Page<Blog> blogPage = blogService.findBlogByPageAndSearch(page, search);
        for (Blog blog : blogPage.content) {
            System.out.println(blog.getUser());
        }
        model.addAttribute("blogPage", blogPage);
        model.addAttribute("search", search);

        return "index";
    }

}
