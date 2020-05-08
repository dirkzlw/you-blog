package com.zlw.manager.web.controller;

import com.zlw.common.po.Blog;
import com.zlw.common.po.Comment;
import com.zlw.common.vo.Page;
import com.zlw.manager.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Dirk
 * @date 2020-05-08 18:41
 */
@Controller
public class CommentController {

    @Autowired(required = false)
    private CommentService commentService;

    /**
     * 展示博客列表
     * @param search
     * @param page
     * @param model
     * @return
     */
    @GetMapping("/comment-list")
    private String toCommentList(@RequestParam(required = false, defaultValue = "") String search,
                                 @RequestParam(required = false, defaultValue = "0") Integer page,
                                 Model model){

        Page<Comment> commentPage = commentService.findCommentBySearchAndPage(search, page);
        model.addAttribute("commentPage", commentPage);
        model.addAttribute("search", search);

        return "blog/comment-list";
    }

}
