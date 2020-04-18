package com.zlw.manager.dao;

import com.zlw.manager.po.Notice;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author Dirk
 * @date 2020-04-18 14:20
 */
public interface NoticeRepository extends JpaRepository<Notice,Integer> {

    Notice findByNoticeId(Integer noticeId);

}
