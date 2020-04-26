package com.zlw.manager.web.controller;

import com.zlw.common.po.Notice;
import com.zlw.manager.service.NoticeService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Dirk
 * @date 2020-04-18 12:28
 */
@Controller
public class NoticeController {

    @Autowired(required = false)
    private NoticeService noticeService;

    /**
     * 获取公告管理页面
     * @return
     */
    @GetMapping("/notice-list")
    public String toNotice(Model model){

        List<Notice> noticeList = noticeService.findAllNotice();
        model.addAttribute("noticeList", noticeList);

        return "notice/notice-list";
    }

    /**
     * 获取添加公告页面
     * @return
     */
    @GetMapping("/notice-add")
    public String toNoticeAdd(){
        return "notice/notice-add";
    }

    /**
     * 添加公告
     * @param info
     * @return
     */
    @PostMapping("/notice/add")
    @ResponseBody
    public String addNotice(String info){
        if(null == info){
            return "fail";
        }else {
            return noticeService.addNotice(info);
        }
    }

    /**
     * 删除公告
     * @param noticeId
     * @return
     */
    @PostMapping("/notice/del")
    @ResponseBody
    private String delNotice(Integer noticeId){
        if(null == noticeId){
            return "fail";
        }else {
            return noticeService.delNoticeById(noticeId);
        }
    }

    /**
     * 获取编辑公告页面
     * @param noticeId
     * @param model
     * @return
     */
    @GetMapping("/notice-edit")
    public String toNoticeEdit(Integer noticeId,Model model){
        Notice notice = noticeService.findNoticeById(noticeId);
        model.addAttribute("notice", notice);
        return "notice/notice-edit";
    }

    /**
     * 保存编辑后的公告
     * @param notice
     * @return
     */
    @PostMapping("/notice/edit")
    @ResponseBody
    public String editNotice(Notice notice){
        if (null == notice.getNoticeId() || null == notice.getInfo()){
            return "fail";
        }else {
            return noticeService.saveNotice(notice);
        }
    }


}
