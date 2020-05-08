package com.zlw.manager.service;

import com.zlw.common.po.Comment;
import com.zlw.common.vo.Page;

/**
 * @author Dirk
 * @date 2020-05-08 18:42
 */
public interface CommentService {
    Page<Comment> findCommentBySearchAndPage(String search, Integer page);
}
