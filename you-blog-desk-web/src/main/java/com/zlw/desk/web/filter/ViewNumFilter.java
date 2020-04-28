package com.zlw.desk.web.filter;

import com.zlw.desk.service.BlogService;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 监听器，实现博客访问量统计
 * @author Dirk
 * @date 2020-04-28 16:53
 */
@Component
@WebFilter(filterName = "viewNumFilter",urlPatterns = "/blog/show")
public class ViewNumFilter implements Filter {

    //注入blog业务层接口
    @Autowired(required = false)
    private BlogService blogService;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        String blogId = request.getParameter("blogId");
        blogService.addViewNum(Integer.parseInt(blogId));
        //放行，执行下一个过滤器
        chain.doFilter(request, response);
    }
}
