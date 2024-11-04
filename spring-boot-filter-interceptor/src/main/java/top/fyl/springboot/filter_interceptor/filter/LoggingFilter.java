package top.fyl.springboot.filter_interceptor.filter;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cglib.core.Local;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * @author dfysa
 * @data 2024/11/4 下午1:53
 *
 * @description
 */
@Slf4j
public class LoggingFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("LoggingFilter 初始化");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        // 请求资源的路径
        String path=request.getRequestURI();
        // 请求的客户端地址
        String clientIp = request.getRemoteAddr();
        // 记录请求开始时间
        log.info("请求已经到达过滤器：path:{},clientIp:{},beginTime:{}",path,clientIp, LocalDateTime.now());
        filterChain.doFilter(servletRequest,servletResponse);
        log.info("请求已经到达过滤器：path:{},clientIp:{},endTime:{}",path,clientIp,LocalDateTime.now());


    }

    @Override
    public void destroy() {
        log.info("LoggingFilter销毁");
    }
}
