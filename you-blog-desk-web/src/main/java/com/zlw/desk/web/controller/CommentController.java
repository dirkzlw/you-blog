package com.zlw.desk.web.controller;

import com.zlw.common.po.Blog;
import com.zlw.common.po.Comment;
import com.zlw.common.po.User;
import com.zlw.common.vo.CommentInfo;
import com.zlw.desk.service.BlogService;
import com.zlw.desk.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Dirk
 * @date 2020-05-03 16:56
 */
@Controller
public class CommentController {

    @Autowired(required = false)
    private CommentService commentService;
    @Autowired(required = false)
    private BlogService blogService;
    @Autowired(required = false)
    private com.zlw.manager.service.UserService userServiceManager;

    /**
     * 博客新增评论
     *
     * @param userId
     * @param blogId
     * @param content
     * @return
     */
    @PostMapping("/blog/comment/add")
    @ResponseBody
    public CommentInfo addBlogComment(Integer userId, Integer blogId, String content) {
        CommentInfo commentInfo = new CommentInfo();
        if (userId == null || blogId == null || content == null) {
            commentInfo.setStatus("fail");
            return commentInfo;
        } else {
            User user = userServiceManager.findUserById(userId);
            Blog blog = blogService.findBlogById(blogId);
            Comment comment = commentService.addComment(user, blog, content);
            //封装返回的json对象
            commentInfo.setCommentId(comment.getCommentId());
            commentInfo.setContent(content);
            commentInfo.setUserHeadImgUrl(user.getHeadImgUrl());
            commentInfo.setUsername(user.getUsername());
            commentInfo.setCreateTime(comment.getCreateTime());

            return commentInfo;
        }
    }

    /**
     * 博客新增评论
     *
     * @param userId
     * @param blogId
     * @param content
     * @return
     */
    @PostMapping("/blog/comment/edit")
    @ResponseBody
    public String editBlogComment(Integer commentId, String content) {
        if (commentId == null || content == null) {
            return "fail";
        } else {
            commentService.editComment(commentId,content);
            return "success";
        }
    }

    @PostMapping("/blog/comment/del")
    @ResponseBody
    public String delComment(Integer commentId) {
        if (null == commentId) {
            return "fail";
        } else {
            return commentService.delComment(commentId);
        }
    }

}
