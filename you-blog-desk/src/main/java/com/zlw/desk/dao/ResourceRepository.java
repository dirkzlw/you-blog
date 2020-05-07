package com.zlw.desk.dao;

import com.zlw.common.po.Resource;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author Dirk
 * @date 2020-05-07 13:18
 */
public interface ResourceRepository extends JpaRepository<Resource,Integer> {

    @Query(nativeQuery = true,value = "SELECT * FROM t_resource AS r " +
            "WHERE r.file_name LIKE CONCAT('%',?1,'%') " +
            "ORDER BY resource_id DESC LIMIT ?2,?3 ;")
    List<Resource> findResourceByPageAndSearch(String search, int i, Integer index_page_size);

    @Query(nativeQuery = true,value = "SELECT count(*) FROM t_resource AS r " +
            "WHERE r.file_name LIKE CONCAT('%',?1,'%') ;")
    Integer countResourceBySearch(String search);
}
