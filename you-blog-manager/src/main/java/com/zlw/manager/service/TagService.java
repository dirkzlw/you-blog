package com.zlw.manager.service;

import com.zlw.common.po.Tag;
import java.util.List;

/**
 * @author Dirk
 * @date 2020-04-19 10:53
 */
public interface TagService {

    void saveTag(Tag tag);

    List<Tag> findAllTag();

    void deeTagById(Integer tagId);

    Tag findTagById(Integer tageId);
}
