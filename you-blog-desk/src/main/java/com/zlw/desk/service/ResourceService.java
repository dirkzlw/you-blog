package com.zlw.desk.service;

import com.zlw.common.po.Resource;
import com.zlw.common.po.User;

/**
 * @author Dirk
 * @date 2020-05-07 13:17
 */
public interface ResourceService {
    void saveResource(Resource resource, User user);
}
