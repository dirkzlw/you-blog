package com.zlw.desk.web.controller;

import com.zlw.common.po.Attention;
import com.zlw.common.po.Blog;
import com.zlw.common.po.Notice;
import com.zlw.common.po.Tag;
import com.zlw.common.po.User;
import com.zlw.common.utils.HttpUtils;
import com.zlw.common.vo.BlogInfo;
import com.zlw.common.vo.Page;
import com.zlw.desk.service.BlogService;
import com.zlw.manager.service.AttentionService;
import com.zlw.manager.service.NoticeService;
import com.zlw.manager.service.TagService;
import java.util.ArrayList;
import java.util.List;
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

/**
 * @author Dirk
 * @date 2020-04-23 15:40
 */
@Controller
public class MainController {

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

    @Value("${ATTENTION_IMG_URL}")
    private static String ATTENTION_IMG_URL;

    /**
     * 获取主页，加载相关数据
     * @param model
     * @param request
     * @param page
     * @param search
     * @return
     */
    @GetMapping("/")
    public String toIndex(Model model,
                          HttpServletRequest request,
                          @RequestParam(required = false, defaultValue = "0") Integer page,
                          @RequestParam(required = false, defaultValue = "") String search) {

        //获取博客列表
        Page<Blog> blogPage = blogService.findBlogByPageAndSearch(page, search);
        HttpSession session = request.getSession();
        sessionAddThreeList(session, noticeService, tagService, attentionService);
        //获取用户排行榜
        List<User> userRanks = userServiceDesk.getUserRanks();
        //获取博客排行榜
        List<Blog> blogRanks = blogService.getUserRanks();
        //获取今日推荐
        Blog blogRecommender = blogService.findBlogTodayRecommander();
        model.addAttribute("blogPage", blogPage);
        session.setAttribute("search", search);
        session.setAttribute("page", page);
        model.addAttribute("userRanks", userRanks);
        model.addAttribute("blogRanks", blogRanks);
        model.addAttribute("blogRecommender", blogRecommender);

        return "index";
    }

    /**
     * 异步加载更多数据
     * @param page
     * @param search
     * @param request
     * @return
     */
    @PostMapping("/blog/more")
    @ResponseBody
    public Page<BlogInfo> getMoreBlog(Integer page,
                                      String search,
                                      HttpServletRequest request) {

        Page<BlogInfo> blogInfoPage = new Page<>();
        //加锁，防止多次同意页面请求
        synchronized (request.getSession().getClass()) {
            HttpSession session = request.getSession();
            Integer page1 = (Integer) session.getAttribute("page");
            if (page1 == page) {
                blogInfoPage.setPage(page);
                return blogInfoPage;
            }
            //获取博客列表
            Page<Blog> blogPage = blogService.findBlogByPageAndSearch(page, search);
            //重置content
            List<BlogInfo> blogInfoList = new ArrayList<>();
            for (Blog blog : blogPage.content) {
                BlogInfo blogInfo = new BlogInfo();
                blogInfo.setBlogId(blog.getBlogId());
                blogInfo.setCoverImgUrl(blog.getCoverImgUrl());
                blogInfo.setTitle(blog.getTitle());
                blogInfo.setText(blog.getText());
                blogInfo.setCreateTime(blog.getCreateTime());
                blogInfo.setUserId(blog.getUser().getUserId());
                blogInfo.setUsername(blog.getUser().getUsername());
                blogInfo.setZanNum(blog.getZanNum());
                blogInfo.setViewNum(blog.getViewNum());
                blogInfo.setTagId(blog.getTag().getTagId());
                blogInfo.setTag(blog.getTag().getType());
                blogInfoList.add(blogInfo);
            }
            blogInfoPage.setContent(blogInfoList);
            blogInfoPage.setPage(blogPage.getPage());
            blogInfoPage.setTotalPages(blogPage.getTotalPages());
            blogInfoPage.setTotalElements(blogPage.getTotalElements());
            blogInfoPage.setPageSize(blogPage.getPageSize());
            session.setAttribute("page", page);
        }

        return blogInfoPage;
    }


    /**
     * 异常处理
     *
     * @param model     模型
     * @param code http状态码
     * @return 执行错误处理页面
     */
    @GetMapping("/to/error")
    public String toError(Model model, Integer code) {
        String msg = HttpUtils.getMessageByCode(code);
        model.addAttribute("code", code);
        model.addAttribute("msg", msg);
        return "error";
    }

    /**
     * 将三个数据列表加入session
     * @param session
     * @param noticeService
     * @param tagService
     * @param attentionService
     */
    public static void sessionAddThreeList(HttpSession session,
                                           NoticeService noticeService,
                                           TagService tagService,
                                           AttentionService attentionService) {
        //获取公告列表
        List<Notice> noticeList = (List<Notice>) session.getAttribute("noticeList");
        if (noticeList == null) {
            noticeList = noticeService.findAllNotice();
            session.setAttribute("noticeList", noticeList);
        }
        //获取标签列表
        List<Tag> tagList = (List<Tag>) session.getAttribute("tagList");
        if (tagList == null) {
            tagList = tagService.findAllTag();
            session.setAttribute("tagList", tagList);
        }
        //获取关注
        List<Attention> attentionList = (List<Attention>) session.getAttribute("attentionList");
        if (attentionList == null) {
            attentionList = attentionService.getAllAttention();
            //数据库中不存在，则显示默认关注二维码
            if (attentionList.size() == 0) {
                session.setAttribute("attentionImgUrl", ATTENTION_IMG_URL);
            } else {
                session.setAttribute("attentionImgUrl", attentionList.get(0).getImgUrl());
            }
        }
    }

}
