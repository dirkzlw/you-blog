package com.zlw.desk.web.config;

import com.zlw.desk.web.interceptor.ErrorInterceptor;
import com.zlw.desk.web.interceptor.UserInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * 权限配置类
 *
 * @author Ranger
 * @create 2019-04-07 10:56
 */
@Configuration
public class RootConfig extends WebMvcConfigurerAdapter {

    /**
     * 自定义拦截器，添加拦截路径和排除拦截路径
     * addPathPatterns():添加需要拦截的路径
     * excludePathPatterns():添加不需要拦截的路径
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //对用户进行拦截
        UserInterceptor userRoot = new UserInterceptor();
        registry.addInterceptor(userRoot)
                .addPathPatterns("/blog/add");                                               //AdminController
        //对跳转错误页面进行拦截
        ErrorInterceptor errorRoot = new ErrorInterceptor();
        registry.addInterceptor(errorRoot);
        super.addInterceptors(registry);
    }
}
