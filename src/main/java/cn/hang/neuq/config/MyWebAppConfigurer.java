package cn.hang.neuq.config;

import cn.hang.neuq.interceptor.SessionInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author lihang15
 * @description
 * @create 2018-12-14 13:55
 **/
@Configuration
public class MyWebAppConfigurer extends WebMvcConfigurationSupport {

    @Autowired
    private SessionInterceptor sessionInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 多个拦截器组成一个拦截器链
        // addPathPatterns 用于添加拦截规则
        // excludePathPatterns 用户排除拦截
        registry.addInterceptor(sessionInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/wx/login")
                .excludePathPatterns("/wx/decodeUserInfo")
                .excludePathPatterns("/auth/login");
        super.addInterceptors(registry);
    }
}
