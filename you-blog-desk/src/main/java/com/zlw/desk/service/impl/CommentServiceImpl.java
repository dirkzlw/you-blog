package com.zlw.desk.service.impl;

import com.zlw.common.po.Blog;
import com.zlw.common.po.Comment;
import com.zlw.common.po.User;
import com.zlw.common.utils.DateUtils;
import com.zlw.desk.dao.CommentRepository;
import com.zlw.desk.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dirk
 * @date 2020-05-03 17:03
 */
@Service(value = "commentService")
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    /**
     * 添加评论
     * @param user
     * @param blog
     * @param content
     */
    @Override
    public Comment addComment(User user, Blog blog, String content) {
        Comment comment = new Comment(DateUtils.getStringTime(), content, blog, user);
        commentRepository.save(comment);
        return comment;
    }

    /**
     * 删除评论
     * @param commentId
     * @return
     */
    @Override
    public String delComment(Integer commentId) {
        commentRepository.deleteById(commentId);
        return "success";
    }

    /**
     * 编辑评论
     * @param commentId
     * @param content
     * @return
     */
    @Override
    public void editComment(Integer commentId, String content) {

        Comment comment = commentRepository.findByCommentId(commentId);
        comment.setContent(content);
        commentRepository.save(comment);
    }

}
