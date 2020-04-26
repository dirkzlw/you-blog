package com.zlw.manager.service;

import com.zlw.common.po.Notice;
import java.util.List;

/**
 * @author Dirk
 * @date 2020-04-17 15:22
 */
public interface NoticeService {
    String addNotice(String info);

    List<Notice> findAllNotice();

    String delNoticeById(Integer noticeId);

    Notice findNoticeById(Integer noticeId);

    String saveNotice(Notice notice);
}
