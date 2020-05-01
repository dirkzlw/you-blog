package com.zlw.common.utils;

import com.zlw.common.po.Blog;
import com.zlw.common.po.User;
import com.zlw.common.vo.SessionUser;
import java.util.List;
import javax.servlet.http.HttpSession;

/**
 * @author Dirk
 * @date 2020-05-01 11:22
 */
public class SessionUtils {

    /**
     * 添加用户至session
     * @param session
     * @param user
     */
    public static void userToSessionUser(HttpSession session, User user){
        List<Blog> blogList = user.getBlogList();
        int zanNum = 0;
        for (Blog blog : blogList) {
            zanNum += blog.getZanNum();
        }
        SessionUser sessionUser = new SessionUser(user.getUserId(),
                user.getUsername(),
                user.getEmail(),
                user.getHeadImgUrl(),
                user.getSignStr(),
                user.getScore(),
                zanNum,
                blogList);
        session.setAttribute("sessionUser", sessionUser);
    }

}
