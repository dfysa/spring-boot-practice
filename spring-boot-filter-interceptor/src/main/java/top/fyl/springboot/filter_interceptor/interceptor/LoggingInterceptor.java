package top.fyl.springboot.filter_interceptor.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;

/**
 * @author dfysa
 * @data 2024/11/4 下午2:48
 * @description
 */

@Component
@Slf4j
public class LoggingInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
//        log.info("拦截器初始化");
//        String path =request.getRequestURI();
//        String clientIp=request.getRemoteAddr();
//        log.info("请求已经到达拦截器：path:{},clientIp:{},beginTime:{}",path,clientIp, LocalDateTime.now());
//        // 请求放行


        // 获取文件信息
        MultipartFile file = (MultipartFile) request.getAttribute("file");
        log.info("文件上传: 名称={}, 大小={}", file.getOriginalFilename(), file.getSize());



        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("请求已经到达拦截器：path:{},endTime:{}",request.getRequestURI(), LocalDateTime.now());    }
}
