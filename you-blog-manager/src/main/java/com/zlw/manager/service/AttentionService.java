package com.zlw.manager.service;

import com.zlw.manager.po.Attention;
import java.util.List;

/**
 * @author Dirk
 * @date 2020-04-18 19:58
 */
public interface AttentionService {
    void addAttention(String imgUrl);

    List<Attention> getAllAttention();
}
