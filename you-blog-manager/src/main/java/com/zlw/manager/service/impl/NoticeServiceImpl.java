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

    /**
     * 根据Id删除公告
     * @param noticeId
     * @return
     */
    @Override
    public String delNoticeById(Integer noticeId) {

        noticeRepository.deleteById(noticeId);

        return "success";
    }

    /**
     * 根据id获取公告
     * @param noticeId
     * @return
     */
    @Override
    public Notice findNoticeById(Integer noticeId) {

        return noticeRepository.findByNoticeId(noticeId);
    }

    /**
     * 保存公告
     * @param notice
     * @return
     */
    @Override
    public String saveNotice(Notice notice) {
        noticeRepository.save(notice);
        return "success";
    }
}
