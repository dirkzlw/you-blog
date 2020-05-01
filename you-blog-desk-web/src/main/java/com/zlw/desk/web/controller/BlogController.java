package com.zlw.desk.web.controller;

import com.zlw.common.po.User;
import com.zlw.common.utils.FastDFSUtils;
import com.zlw.common.utils.SessionUtils;
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
            rtnObj = new ResultObj("fail",null );
        } else {
            //上传封面
            String coverImgUrl = FastDFSUtils.uploadFile(FDFS_CLIENT_PAHT, FDFS_ADDRESS, coverImg);
            if (coverImg == null) {
                rtnObj = new ResultObj("fail",null );
            } else {
                Tag tag = tagService.findTagById(tagId);
                SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
                User user = userServiceManager.findUserById(sessionUser.getUserId());
                rtnObj = blogService.addBlog(blog, tag, coverImgUrl, user);
                //更新积分
                User user2 =  userServiceDesk.updateScore(user);
                //更新sessionUser
                SessionUtils.userToSessionUser(session, user2);
            }
        }

        return rtnObj;
    }

    /**
     * 查看博客
     *
     * @return
     */
    @GetMapping("/blog/show")
    public String showBlog(Integer blogId, Model model, HttpServletRequest request) {
        MainController.sessionAddThreeList(request.getSession(), noticeService, tagService, attentionService);
        Blog blog = blogService.findBlogById(blogId);
        //获取用户排行榜
        List<User> userRanks = userServiceDesk.getUserRanks();
        //获取博客排行榜
        List<Blog> blogRanks = blogService.getUserRanks();
        model.addAttribute("blog", blog);
        model.addAttribute("userRanks", userRanks);
        model.addAttribute("blogRanks", blogRanks);

        //更新积分
        HttpSession session = request.getSession();
        SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
        User user = userServiceManager.findUserById(sessionUser.getUserId());
        User user2 =  userServiceDesk.updateScore(user);
        //更新sessionUser
        SessionUtils.userToSessionUser(session, user2);
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
    public String zanBlog(Integer blogId,HttpSession session) {
        if (blogId == null)
            return "fail";
        else {
            blogService.zanBlog(blogId);
            //更新积分
            SessionUser sessionUser = (SessionUser) session.getAttribute("sessionUser");
            User user = userServiceManager.findUserById(sessionUser.getUserId());
            User user2 =  userServiceDesk.updateScore(user);
            //更新sessionUser
            SessionUtils.userToSessionUser(session, user2);
            return "success";
        }
    }

    @GetMapping("/blog/user")
    public String blogByUser(Integer userId){
        System.out.println("userId = " + userId);
        return "index";
    }

}
