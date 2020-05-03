package com.zlw.desk.dao;

import com.zlw.common.po.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Dirk
 * @date 2020-05-03 17:00
 */
public interface CommentRepository extends JpaRepository<Comment,Integer> {
}
