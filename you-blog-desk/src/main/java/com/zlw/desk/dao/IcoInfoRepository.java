package com.zlw.desk.dao;

import com.zlw.common.po.IcoInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Dirk
 * @date 2020-05-07 15:04
 */
public interface IcoInfoRepository extends JpaRepository<IcoInfo,Integer> {

    IcoInfo findByIcoName(String icoName);

}
