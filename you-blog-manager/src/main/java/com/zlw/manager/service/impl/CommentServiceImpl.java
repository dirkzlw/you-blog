package com.zlw.manager.service.impl;

import com.zlw.common.po.Blog;
import com.zlw.common.po.Comment;
import com.zlw.common.vo.Page;
import com.zlw.manager.dao.CommentRepository;
import com.zlw.manager.service.CommentService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Dirk
 * @date 2020-05-08 18:42
 */
@Service(value = "commentService")
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Value("${data.page.size}")
    private Integer PAGE_SIZE;

    /**
     * 分页查询博客列表
     * @param search
     * @param page
     * @return
     */
    @Override
    public Page<Comment> findCommentBySearchAndPage(String search, Integer page) {
        List<Comment> commentList = commentRepository.findBySearchAndPage(search, page * PAGE_SIZE, PAGE_SIZE);
        int totalElements = commentRepository.countCommentByStatusAndSearch(search);
        int totalPages = (int) Math.ceil(totalElements * 1.0 / PAGE_SIZE);
        Page<Comment> commentPage = new Page<>(commentList, page, totalPages, totalElements, PAGE_SIZE);

        return commentPage;
    }
}
