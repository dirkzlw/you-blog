package com.zlw.desk.dao;

import com.zlw.common.po.Resource;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Dirk
 * @date 2020-05-07 13:18
 */
public interface ResourceRepository extends JpaRepository<Resource,Integer> {
}
