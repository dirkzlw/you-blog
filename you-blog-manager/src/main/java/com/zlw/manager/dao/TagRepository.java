package com.zlw.manager.dao;

import com.zlw.manager.po.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Dirk
 * @date 2020-04-19 10:52
 */
public interface TagRepository extends JpaRepository<Tag,Integer> {

    Tag findByTagId(Integer tagId);

}
