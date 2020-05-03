package com.zlw.desk.service;

import com.zlw.common.po.Blog;
import com.zlw.common.po.Comment;
import com.zlw.common.po.User;

/**
 * @author Dirk
 * @date 2020-05-03 17:01
 */
public interface CommentService {
    Comment addComment(User user, Blog blog, String content);

    String delComment(Integer commentId);

    void editComment(Integer commentId, String content);
}
