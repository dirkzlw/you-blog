package com.zlw.desk.web.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

/**
 * @author Ranger
 * @create 2019-04-11 14:57
 */
@Component
public class ErrorInterceptor implements HandlerInterceptor {
    private List<Integer> errorCodeList = Arrays.asList(403, 404, 405, 500, 501);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //当拦截成功时候，转发跳转错误页面
        if (errorCodeList.contains(response.getStatus())) {
            response.sendRedirect("/to/error?code="+response.getStatus());
            return false;
        }
        return true;
    }
}
