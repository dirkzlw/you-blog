package com.zlw.desk.web.controller;

import com.zlw.manager.po.Tag;
import com.zlw.manager.service.TagService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Dirk
 * @date 2020-04-25 15:55
 */
@Controller
public class BlogController {

    @Autowired(required = false)
    private TagService tagService;

    /**
     * 跳转至编写博客界面
     * @return
     */
    @GetMapping("/blog/add")
    private String toBlogAdd(Model model){

        List<Tag> tagList = tagService.findAllTag();
        model.addAttribute("tagList", tagList);

        return "blog/add";
    }

}
