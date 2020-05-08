package com.zlw.desk.web.controller;

import com.zlw.common.po.Blog;
import com.zlw.common.po.IcoInfo;
import com.zlw.common.po.Resource;
import com.zlw.common.po.User;
import com.zlw.common.utils.FastDFSUtils;
import com.zlw.common.vo.Page;
import com.zlw.desk.service.BlogService;
import com.zlw.desk.service.IcoInfoService;
import com.zlw.desk.service.ResourceService;
import com.zlw.manager.service.AttentionService;
import com.zlw.manager.service.NoticeService;
import com.zlw.manager.service.TagService;
import java.util.List;
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
 * @date 2020-05-07 7:45
 */
@Controller
public class ResourceController {

    @Autowired(required = false)
    private NoticeService noticeService;
    @Autowired(required = false)
    private TagService tagService;
    @Autowired(required = false)
    private AttentionService attentionService;
    @Autowired(required = false)
    private BlogService blogService;
    @Autowired(required = false)
    private ResourceService resourceService;
    @Autowired(required = false)
    private IcoInfoService icoInfoService;
    @Autowired(required = false)
    private com.zlw.desk.service.UserService userServiceDesk;
    @Autowired(required = false)
    private com.zlw.manager.service.UserService userServiceManager;

    //引入图片服务器地址
    @Value("${FDFS_ADDRESS}")
    private String FDFS_ADDRESS;
    @Value("${FDFS_CLIENT_PAHT}")
    private String FDFS_CLIENT_PAHT;


    @GetMapping("/resource/list")
    public String toResourceList(HttpSession session,Model model,
                                 @RequestParam(required = false, defaultValue = "0") Integer page,
                                 @RequestParam(required = false, defaultValue = "") String search){

        //获取资源列表
        Page<Resource> resourcePage = resourceService.findResourceByPageAndSearch(page, search);
        model.addAttribute("resourcePage", resourcePage);
        session.setAttribute("search", search);
        session.setAttribute("page", page);
        //获取三个列表
        MainController.sessionAddThreeList(session, noticeService, tagService, attentionService);
        //获取用户排行榜
        List<User> userRanks = userServiceDesk.getUserRanks();
        //获取博客排行榜
        List<Blog> blogRanks = blogService.getUserRanks();
        //获取今日推荐
        Blog blogRecommender = blogService.findBlogTodayRecommander();
        model.addAttribute("userRanks", userRanks);
        model.addAttribute("blogRanks", blogRanks);
        model.addAttribute("blogRecommender", blogRecommender);

        return "resource/list";
    }

    /**
     * 跳转至资源分享页面
     *
     * @return
     */
    @GetMapping("/resource/add")
    public String toResourceAdd(HttpSession session, Model model) {

        MainController.sessionAddThreeList(session, noticeService, tagService, attentionService);
        //获取用户排行榜
        List<User> userRanks = userServiceDesk.getUserRanks();
        //获取博客排行榜
        List<Blog> blogRanks = blogService.getUserRanks();
        //获取今日推荐
        Blog blogRecommender = blogService.findBlogTodayRecommander();
        model.addAttribute("userRanks", userRanks);
        model.addAttribute("blogRanks", blogRanks);
        model.addAttribute("blogRecommender", blogRecommender);

        return "resource/add";
    }

    /**
     * 上传资源文件
     *
     * @param file
     * @return
     */
    @PostMapping("/resource/file/upload")
    @ResponseBody
    public String resourceUpload(MultipartFile file) {
        if (file == null) {
            return "fail";
        } else {
            String fileUrl = FastDFSUtils.uploadFile(FDFS_CLIENT_PAHT, FDFS_ADDRESS, file);
            if (null == fileUrl || "".equals(fileUrl)) {
                return "fail";
            }
            return fileUrl;
        }
    }

    /**
     * 上传资源
     * @param resource
     * @param userId
     * @return
     */
    @PostMapping("/resource/save")
    @ResponseBody
    public String saveResource(Resource resource, Integer userId) {
        if (userId == null || resource == null ||resource.getDownUrl() == null) {
            return "fail";
        }else {
            //判断资源文件的图标
            String[] split = resource.getDownUrl().split("\\.");
            String suf = split[split.length-1];
            System.out.println("suf = " + suf);
            String icoUrl = icoInfoService.findIcoInfoByIcoName(suf);
            resource.setCoverImgUrl(icoUrl);

            User user = userServiceManager.findUserById(userId);
            //保存资源
            resourceService.saveResource(resource, user);
            return "success";
        }
    }

}
