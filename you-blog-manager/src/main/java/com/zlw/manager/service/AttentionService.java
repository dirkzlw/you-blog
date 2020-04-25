package com.zlw.manager.service;

import com.zlw.manager.po.Attention;
import java.util.List;

/**
 * @author Dirk
 * @date 2020-04-18 19:58
 */
public interface AttentionService {

    List<Attention> getAllAttention();

    String delAttentionById(Integer attentionId);

    Attention findAttentionById(Integer attentionId);

    void saveAttention(Attention attention);
}
