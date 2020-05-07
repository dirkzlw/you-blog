package com.zlw.desk.service.impl;

import com.zlw.common.po.Resource;
import com.zlw.common.po.User;
import com.zlw.desk.dao.ResourceRepository;
import com.zlw.desk.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dirk
 * @date 2020-05-07 13:18
 */
@Service(value = "resourceService")
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    /**
     * 保存资源
     * @param resource
     * @param user
     */
    @Override
    public void saveResource(Resource resource, User user) {
        resource.setUser(user);
        resourceRepository.save(resource);
    }
}
