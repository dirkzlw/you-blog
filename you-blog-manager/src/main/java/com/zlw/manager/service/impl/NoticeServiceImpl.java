package com.zlw.manager.service.impl;

import com.zlw.manager.dao.NoticeRepository;
import com.zlw.manager.po.Notice;
import com.zlw.manager.service.NoticeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Dirk
 * @date 2020-04-17 15:22
 */
@Service(value = "noticeService")
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    private NoticeRepository noticeRepository;

    /**
     * 添加公告
     */
    @Override
    public String addNotice(String info) {

        Notice notice = new Notice(info);
        noticeRepository.save(notice);

        return "success";
    }

    /**
     * 查询所有公告
     * @return
     */
    @Override
    public List<Notice> findAllNotice() {
        return noticeRepository.findAll();
    }
}
