package com.zlw.manager.dao;

import com.zlw.common.po.Attention;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Dirk
 * @date 2020-04-18 19:58
 */
public interface AttentionRepository extends JpaRepository<Attention,Integer> {

    Attention findByAttentionId(Integer attentionId);

}
