package top.fyl.springboot.filter_interceptor.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import top.fyl.springboot.filter_interceptor.filter.AuthFilter;
import top.fyl.springboot.filter_interceptor.filter.CorsFilter;
import top.fyl.springboot.filter_interceptor.filter.LoggingFilter;
import top.fyl.springboot.filter_interceptor.filter.RateLimitFilter;

/**
 * @author dfysa
 * @data 2024/11/4 下午1:44
 * @description 过滤器的注册中心
 */
@Configuration
//@WebFilter(urlPatterns = "/*")
public class FilterConfig {
//    @Bean
//    public FilterRegistrationBean<CustomFilter> filterRegistrationBean(){
//        FilterRegistrationBean<CustomFilter> registrationBean=new FilterRegistrationBean<>();
//        registrationBean.setFilter(new CustomFilter());
//        // 设置过滤器拦截的URL路径，这里设置为全部路径
//        registrationBean.addUrlPatterns("/*");
//        // 设置过滤器的执行顺序，值越小 执行优先度越高
//        registrationBean.setOrder(1);
//        return registrationBean;
//
//    }
//
//    @Bean
//    public FilterRegistrationBean<LoggingFilter> LoggingFilter(){
//        FilterRegistrationBean<LoggingFilter> registrationBean=new FilterRegistrationBean<>();
//        registrationBean.setFilter(new LoggingFilter());
//        // 设置过滤器拦截的URL路径，这里设置为全部路径
//        registrationBean.addUrlPatterns("/*");
//        // 设置过滤器的执行顺序，值越小 执行优先度越高
//        registrationBean.setOrder(1);
//        return registrationBean;
//
//    }

//
//    @Bean
//    public FilterRegistrationBean<AuthFilter> AuthFilter(){
//        FilterRegistrationBean<AuthFilter> registrationBean=new FilterRegistrationBean<>();
//        registrationBean.setFilter(new AuthFilter());
//        // 设置过滤器拦截的URL路径，这里设置为全部路径
//        registrationBean.addUrlPatterns("/*");
//        // 设置过滤器的执行顺序，值越小 执行优先度越高
//        registrationBean.setOrder(1);
//        return registrationBean;
//
//    }

//    @Bean
//    public FilterRegistrationBean<CorsFilter> CorsFilter(){
//        FilterRegistrationBean<CorsFilter> registrationBean=new FilterRegistrationBean<>();
//        registrationBean.setFilter(new CorsFilter());
//        // 设置过滤器拦截的URL路径，这里设置为全部路径
//        registrationBean.addUrlPatterns("/*");
//        // 设置过滤器的执行顺序，值越小 执行优先度越高
//        registrationBean.setOrder(1);
//        return registrationBean;
//
//    }

        @Bean
    public FilterRegistrationBean<RateLimitFilter> CorsFilter(){
        FilterRegistrationBean<RateLimitFilter> registrationBean=new FilterRegistrationBean<>();
        registrationBean.setFilter(new RateLimitFilter());
        // 设置过滤器拦截的URL路径，这里设置为全部路径
        registrationBean.addUrlPatterns("/*");
        // 设置过滤器的执行顺序，值越小 执行优先度越高
        registrationBean.setOrder(1);
        return registrationBean;

    }
}
