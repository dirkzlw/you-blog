package com.zlw.desk.service;

import com.zlw.common.po.Resource;
import com.zlw.common.po.User;
import com.zlw.common.vo.Page;

/**
 * @author Dirk
 * @date 2020-05-07 13:17
 */
public interface ResourceService {
    void saveResource(Resource resource, User user);

    Page<Resource> findResourceByPageAndSearch(Integer page, String search);

    Resource findResourceById(Integer resourceId);

    void saveResource(Resource resource);
}
