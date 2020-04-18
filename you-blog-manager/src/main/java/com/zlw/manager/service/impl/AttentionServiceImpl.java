package com.zlw.manager.service.impl;

import com.zlw.manager.dao.AttentionRepository;
import com.zlw.manager.po.Attention;
import com.zlw.manager.service.AttentionService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dirk
 * @date 2020-04-18 19:59
 */
@Service(value = "attentionService")
public class AttentionServiceImpl implements AttentionService {

    @Autowired
    private AttentionRepository attentionRepository;

    /**
     * 保存关注
     * @param imgUrl
     */
    @Override
    public void addAttention(String imgUrl) {
        Attention attention = new Attention(imgUrl);
        attentionRepository.save(attention);
    }

    /**
     * 返回关注
     * @return
     */
    @Override
    public List<Attention> getAllAttention() {

        return attentionRepository.findAll();

    }
}
