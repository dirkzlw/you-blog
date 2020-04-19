package com.zlw.manager.service;

import com.zlw.manager.po.Tag;
import java.util.List;

/**
 * @author Dirk
 * @date 2020-04-19 10:53
 */
public interface TagService {

    void saveTag(Tag tag);

    List<Tag> findAllTag();
}
