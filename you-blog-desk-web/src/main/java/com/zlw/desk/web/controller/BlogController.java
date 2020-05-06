package com.zlw.desk.web.controller;

import com.sun.org.apache.xpath.internal.operations.Mod;
import com.zlw.common.po.User;
import com.zlw.common.utils.FastDFSUtils;
import com.zlw.common.utils.SessionUtils;
import com.zlw.common.vo.Page;
import com.zlw.common.vo.ResultObj;
import com.zlw.common.vo.SessionUser;
import com.zlw.desk.service.BlogService;
import com.zlw.common.po.Blog;
import com.zlw.common.po.Tag;
import com.zlw.manager.service.AttentionService;
import com.zlw.manager.service.NoticeService;
import com.zlw.manager.service.TagService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
    private com.zlw.desk.service.UserService userServiceDesk;
    @Autowired(required = false)
    private com.zlw.manager.service.UserService userServiceManager;

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
     * 跳转至编辑博客界面
     *
     * @return
     */
    @GetMapping("/blog/edit")
    private String toBlogEdit(Model model, Integer blogId) {

        List<Tag> tagList = tagService.findAllTag();
        model.addAttribute("tagList", tagList);
        Blog blog = blogService.findBlogById(blogId);
        model.addAttribute("blog", blog);

        return "blog/edit";
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
    public ResultObj addBlog(Blog blog, Integer tagId, MultipartFile coverImg,
                             HttpSession session) {

        ResultObj rtnObj;
        if (blog.getTitle() == null || tagId == null || coverImg == null) {
            rtnObj = new ResultObj("fail", null);
        } else {
            //上传封面
            String coverImgUrl = FastDFSUtils.uploadFile(FDFS_CLIENT_PAHT, FDFS_ADDRESS, coverImg);
            if (coverImgUrl == null) {
                rtnObj = new ResultObj("fail", null);
            } else {
                Tag tag = tagService.findTagById(tagId);
                SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
                User user = userServiceManager.findUserById(sessionUser.getUserId());
                rtnObj = blogService.addBlog(blog, tag, coverImgUrl, user);
                //更新积分
                userServiceDesk.updateScore(blogService.findBlogById(rtnObj.getObjId()).getUser());
                //更新sessionUser
                SessionUtils.userToSessionUser(session, userServiceManager.findUserById(sessionUser.getUserId()));
            }
        }

        return rtnObj;
    }

    /**
     * 编辑博客
     *
     * @param blog     博客：标题，正文，类型
     * @param tagId    博客标签
     * @param coverImg 封面
     * @return
     */
    @PostMapping("/blog/edit")
    @ResponseBody
    public ResultObj editBlog(Blog blog, Integer tagId, MultipartFile coverImg) {

        ResultObj rtnObj;
        if (blog.getTitle() == null || tagId == null) {
            rtnObj = new ResultObj("fail", null);
        } else {
            //上传封面
            String coverImgUrl;
            Tag tag = tagService.findTagById(tagId);
            if (coverImg != null) {
                coverImgUrl = FastDFSUtils.uploadFile(FDFS_CLIENT_PAHT, FDFS_ADDRESS, coverImg);
                if (coverImgUrl == null) {
                    rtnObj = new ResultObj("fail", null);
                }else {
                    rtnObj = blogService.editBlog(blog, tag, coverImgUrl);
                }
            }else {
                rtnObj = blogService.editBlog(blog, tag);
            }
        }

        return rtnObj;
    }

    /**
     * 删除博客
     *
     * @param blogId
     * @return
     */
    @PostMapping("/blog/del")
    @ResponseBody
    public String delBlog(Integer blogId) {
        if (null == blogId) {
            return "fail";
        } else {
            return blogService.delBlog(blogId);
        }
    }

    /**
     * 查看博客
     *
     * @return
     */
    @GetMapping("/blog/show")
    public String showBlog(Integer blogId, Model model, HttpSession session) {
        MainController.sessionAddThreeList(session, noticeService, tagService, attentionService);
        Blog blog = blogService.findBlogById(blogId);
        //获取用户排行榜
        List<User> userRanks = userServiceDesk.getUserRanks();
        //获取博客排行榜
        List<Blog> blogRanks = blogService.getUserRanks();
        //获取今日推荐
        Blog blogRecommender = blogService.findBlogTodayRecommander();
        model.addAttribute("blog", blog);
        model.addAttribute("userRanks", userRanks);
        model.addAttribute("blogRanks", blogRanks);
        model.addAttribute("blogRecommender", blogRecommender);

        //更新积分
        userServiceDesk.updateScore(blog.getUser());
        //判断是否需要更新sessionUser
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        User user = blog.getUser();
        if (user.getUserId() == sessionUser.getUserId()) {
            SessionUtils.userToSessionUser(session, user);
        }
        return "blog/show";
    }

    /**
     * 博客点赞
     *
     * @param blogId
     * @return
     */
    @PostMapping("/blog/zan")
    @ResponseBody
    public String zanBlog(Integer blogId, HttpSession session) {
        if (blogId == null)
            return "fail";
        else {
            blogService.zanBlog(blogId);
            //更新积分
            User user = blogService.findBlogById(blogId).getUser();
            userServiceDesk.updateScore(user);
            //判断是否需要更新sessionUser
            SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
            if (user.getUserId() == sessionUser.getUserId()) {
                SessionUtils.userToSessionUser(session, user);
            }
            return "success";
        }
    }

    /**
     * 根据用户查询博客
     *
     * @param userId
     * @param model
     * @return
     */
    @GetMapping("/blog/user")
    public String blogByUser(Integer userId, Model model,
                             HttpServletRequest request) {
        HttpSession session = request.getSession();
        MainController.sessionAddThreeList(session, noticeService, tagService, attentionService);
        //获取博客列表
        List<Blog> blogList = blogService.findBlogByUserId(userId);
        //获取用户排行榜
        List<User> userRanks = userServiceDesk.getUserRanks();
        //获取博客排行榜
        List<Blog> blogRanks = blogService.getUserRanks();
        //获取今日推荐
        Blog blogRecommender = blogService.findBlogTodayRecommander();
        model.addAttribute("blogList", blogList);
        model.addAttribute("userRanks", userRanks);
        model.addAttribute("blogRanks", blogRanks);
        model.addAttribute("blogRecommender", blogRecommender);

        return "blog/user";
    }

    /**
     * 根据标签查看博客列表
     *
     * @param tagId
     * @return
     */
    @GetMapping("/blog/tag")
    public String blogByTag(Integer tagId, Model model,
                            HttpServletRequest request) {
        HttpSession session = request.getSession();
        MainController.sessionAddThreeList(session, noticeService, tagService, attentionService);
        //获取博客列表
        List<Blog> blogList = blogService.findBlogByTagId(tagId);
        //获取用户排行榜
        List<User> userRanks = userServiceDesk.getUserRanks();
        //获取博客排行榜
        List<Blog> blogRanks = blogService.getUserRanks();
        //获取今日推荐
        Blog blogRecommender = blogService.findBlogTodayRecommander();
        model.addAttribute("blogList", blogList);
        model.addAttribute("userRanks", userRanks);
        model.addAttribute("blogRanks", blogRanks);
        model.addAttribute("blogRecommender", blogRecommender);

        return "blog/tag";
    }

}
