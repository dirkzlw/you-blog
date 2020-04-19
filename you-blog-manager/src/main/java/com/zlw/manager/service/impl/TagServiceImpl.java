package com.zlw.manager.service.impl;

import com.zlw.manager.dao.TagRepository;
import com.zlw.manager.po.Tag;
import com.zlw.manager.service.TagService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dirk
 * @date 2020-04-19 10:53
 */
@Service(value = "tagService")
public class TagServiceImpl implements TagService {

    @Autowired
    private TagRepository tagRepository;

    /**
     * 保存标签
     * @param tag
     */
    @Override
    public void saveTag(Tag tag) {
        tagRepository.save(tag);
    }

    /**
     * 查询搜索标签
     * @return
     */
    @Override
    public List<Tag> findAllTag() {
        return tagRepository.findAll();
    }
}
