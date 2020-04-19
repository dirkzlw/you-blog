package com.zlw.manager.web.controller;

import com.zlw.manager.po.Tag;
import com.zlw.manager.service.TagService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Dirk
 * @date 2020-04-19 10:58
 */
@Controller
public class TagController {

    @Autowired(required = false)
    private TagService tagService;

    /**
     * 获取标签管理页面
     * @return
     */
    @GetMapping("/tag-list")
    public String toTagList(Model model){

        List<Tag> tagList = tagService.findAllTag();
        model.addAttribute("tagList", tagList);

        return "tag/tag-list";
    }

    /**
     * 获取添加标签页面
     * @return
     */
    @GetMapping("/tag-add")
    public String toTagAdd(){
        return "tag/tag-add";
    }

    /**
     * 添加标签
     * @param tag
     * @return
     */
    @PostMapping("/tag/add")
    @ResponseBody
    public String addTag(Tag tag){
        if(tag.getType() == null || "".equals(tag.getType())){
            return "fail";
        }else {
            tagService.saveTag(tag);
            return "success";
        }
    }

    /**
     * 删除标签
     * @param tagId
     * @return
     */
    @PostMapping("/tag/del")
    @ResponseBody
    public String delTag(Integer tagId) {
        if (null == tagId) {
            return "fail";
        }else {
            tagService.deeTagById(tagId);
            return "success";
        }
    }

    /**
     * 获取编辑标签页面
     * @param tagId
     * @param model
     * @return
     */
    @GetMapping("/tag-edit")
    public String toTagEdit(Integer tagId,Model model) {
        Tag tag = tagService.findTagById(tagId);
        model.addAttribute("tag", tag);
        return "tag/tag-edit";
    }

    /**
     * 保存编辑的标签
     * @param tag
     * @return
     */
    @PostMapping("/tag/edit")
    @ResponseBody
    public String editTag(Tag tag) {
        if (tag.getTagId() == null || tag.getType() == null) {
            return "fail";
        }else {
            tagService.saveTag(tag);
            return "success";
        }
    }

}
