package com.zlw.manager.service.impl;

import com.zlw.manager.dao.AttentionRepository;
import com.zlw.common.po.Attention;
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
     * 返回关注
     * @return
     */
    @Override
    public List<Attention> getAllAttention() {

        return attentionRepository.findAll();

    }

    /**
     * 根据id删除关注
     * @param attentionId
     * @return
     */
    @Override
    public String delAttentionById(Integer attentionId) {
        attentionRepository.deleteById(attentionId);

        return "success";
    }

    /**
     * 根据Id获取关注
     * @param attentionId
     * @return
     */
    @Override
    public Attention findAttentionById(Integer attentionId) {
        return attentionRepository.findByAttentionId(attentionId);
    }

    /**
     * 保存关注对象
     * @param attention
     */
    @Override
    public void saveAttention(Attention attention) {
        attentionRepository.save(attention);
    }

}
