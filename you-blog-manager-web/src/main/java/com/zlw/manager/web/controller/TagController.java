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

}
