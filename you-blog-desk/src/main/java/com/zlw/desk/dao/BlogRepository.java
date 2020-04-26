package com.zlw.desk.dao;

import com.zlw.manager.po.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Dirk
 * @date 2020-04-26 9:54
 */
public interface BlogRepository extends JpaRepository<Blog,Integer> {
}
