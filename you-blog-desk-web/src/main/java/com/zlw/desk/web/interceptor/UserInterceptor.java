package com.zlw.desk.web.interceptor;

import com.zlw.common.vo.SessionUser;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 用户权限权限拦截器
 * @author Ranger
 * @create 2019-04-07 10:49
 */
@Component
public class UserInterceptor implements HandlerInterceptor {

    /*
     * 进入controller层之前拦截请求
     * 返回值：表示是否将当前的请求拦截下来  false：拦截请求，请求别终止。true：请求不被拦截，继续执行
     * Object obj:表示被拦的请求的目标对象（controller中方法）
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //从session中获取author，判断是否登录
        SessionUser sessionUser = (SessionUser) request.getSession().getAttribute("sessionUser");
        if(sessionUser == null || sessionUser.getUserId()==null){
            response.sendRedirect("/to/login");
            return false;
        }
        return true;
    }
}
