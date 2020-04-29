package com.zlw.desk.web.controller;

import com.zlw.common.utils.FastDFSUtils;
import com.zlw.desk.service.BlogService;
import com.zlw.common.po.Blog;
import com.zlw.common.po.Tag;
import com.zlw.manager.service.AttentionService;
import com.zlw.manager.service.NoticeService;
import com.zlw.manager.service.TagService;
import com.zlw.desk.service.UserService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Dirk
 * @date 2020-04-25 15:55
 */
@Controller
public class BlogController {

    @Autowired(required = false)
    private NoticeService noticeService;
    @Autowired(required = false)
    private TagService tagService;
    @Autowired(required = false)
    private AttentionService attentionService;
    @Autowired(required = false)
    private BlogService blogService;
    @Autowired(required = false)
    private UserService userService;

    //引入图片服务器地址
    @Value("${FDFS_ADDRESS}")
    private String FDFS_ADDRESS;
    @Value("${FDFS_CLIENT_PAHT}")
    private String FDFS_CLIENT_PAHT;

    /**
     * 跳转至编写博客界面
     *
     * @return
     */
    @GetMapping("/blog/add")
    private String toBlogAdd(Model model) {

        List<Tag> tagList = tagService.findAllTag();
        model.addAttribute("tagList", tagList);

        return "blog/add";
    }

    /**
     * 博文图片上传
     *
     * @return
     */
    @PostMapping("/blog/img/upload")
    @ResponseBody
    public Map<String, String> upload(@RequestParam("file") MultipartFile file) {

        String imgUrl = FastDFSUtils.uploadFile(FDFS_CLIENT_PAHT, FDFS_ADDRESS, file);

        Map<String, String> map = new HashMap<>();
        //map存储图片Url
        map.put("data", imgUrl);

        return map;
    }

    /**
     * 添加博客
     *
     * @param blog     博客：标题，正文，类型
     * @param tagId    博客标签
     * @param coverImg 封面
     * @return
     */
    @PostMapping("/blog/add")
    @ResponseBody
    public String addBlog(Blog blog, Integer tagId, MultipartFile coverImg) {

        String rtn;
        if (blog.getTitle() == null || tagId == null || coverImg == null) {
            rtn = "fail";
        } else {
            //上传封面
            String coverImgUrl = FastDFSUtils.uploadFile(FDFS_CLIENT_PAHT, FDFS_ADDRESS, coverImg);
            if (coverImg == null) {
                rtn = "fail";
            } else {
                Tag tag = tagService.findTagById(tagId);
                rtn = blogService.addBlog(blog, tag, coverImgUrl);
            }
        }

        return rtn;
    }

    /**
     * 查看博客
     *
     * @return
     */
    @GetMapping("/blog/show")
    public String showBlog(Integer blogId, Model model, HttpServletRequest request) {
        MainController.sessionAddThreeList(request.getSession(), noticeService, tagService, attentionService,userService);
        Blog blog = blogService.findBlogById(blogId);
        model.addAttribute("blog", blog);
        return "blog/show";
    }

    /**
     * 博客点赞
     * @param blogId
     * @return
     */
    @PostMapping("/blog/zan")
    @ResponseBody
    public String zanBlog(Integer blogId){
        if(blogId == null)
            return "fail";
        else {
            blogService.zanBlog(blogId);
            return "success";
        }
    }

}
