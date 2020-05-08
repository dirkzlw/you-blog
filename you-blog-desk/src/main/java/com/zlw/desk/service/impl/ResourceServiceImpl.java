package com.zlw.desk.service.impl;

import com.zlw.common.po.Resource;
import com.zlw.common.po.User;
import com.zlw.common.vo.Page;
import com.zlw.desk.dao.ResourceRepository;
import com.zlw.desk.service.ResourceService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author Dirk
 * @date 2020-05-07 13:18
 */
@Service(value = "resourceService")
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceRepository resourceRepository;

    @Value("${index.page.size}")
    private Integer INDEX_PAGE_SIZE;
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

    /**
     * 分页检索资源列表
     * @param page
     * @param search
     * @return
     */
    @Override
    public Page<Resource> findResourceByPageAndSearch(Integer page, String search) {

        List<Resource> resourceList = resourceRepository.findResourceByPageAndSearch(search, page * INDEX_PAGE_SIZE, INDEX_PAGE_SIZE);
        int totalElements = resourceRepository.countResourceBySearch(search);
        int totalPages = (int) Math.ceil(totalElements * 1.0 / INDEX_PAGE_SIZE);
        Page<Resource> resourcePage = new Page<>(resourceList, page, totalPages, totalElements, INDEX_PAGE_SIZE);

        return resourcePage;
    }

    /**
     * 根据id查询资源
     * @param resourceId
     * @return
     */
    @Override
    public Resource findResourceById(Integer resourceId) {
        return resourceRepository.findByResourceId(resourceId);
    }

    @Override
    public void saveResource(Resource resource) {
        resourceRepository.save(resource);
    }
}
