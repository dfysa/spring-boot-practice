package top.fyl.springboot.filter_interceptor.filter;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author dfysa
 * @data 2024/11/4 下午3:37
 * @description
 */
@Slf4j
public class RateLimitFilter implements Filter {
    private static final int LIMIT =5;
    private static final int TIME_WINDOW =60*1000;
    // 存储每个IP地址的请求信息，key是IP地址
    private static final ConcurrentHashMap<String, UserRequest> USER_REQUESTS = new ConcurrentHashMap<>();
    // 存储不同的IP地址，key是Ip地址
    private static final ConcurrentHashMap<String, String> CAPTCHA_STORE = new ConcurrentHashMap<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("RateLimitFilter init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletResponse response= (HttpServletResponse) servletResponse;
        HttpServletRequest request= (HttpServletRequest) servletRequest;
        // 获取请求的客户端地址
        String clientIp = request.getRemoteAddr();
        // 计算该IP地址的请求信息，包括请求次数和时间
        UserRequest userRequest= USER_REQUESTS.compute(clientIp,(key,value)-> {

            // 如果该用户的请求为空，或者已经超过时间窗口

            if (value == null || System.currentTimeMillis() - value.timestamp > TIME_WINDOW) {
                return new UserRequest(1, System.currentTimeMillis());

            } else {
                // 增加请求次数
                value.count++;
                //返回该用户请求对象
                return value;

            }

        });

        // 检查用户请求是否超过限制，如果超过，就生成验证码图片返回给用户
        if (userRequest.count>LIMIT){
            // 使用hutool生成验证码
            LineCaptcha lineCaptcha=CaptchaUtil.createLineCaptcha(200, 100);
            // 获取验证码的文本内容
            String code=lineCaptcha.getCode();
            // 存入该IP地址的验证码仓库
            CAPTCHA_STORE.put(clientIp,code);
            // 把验证码返回给客户端
            response.setContentType("image/png");
            ServletOutputStream outputStream=response.getOutputStream();
            //把验证码图片，通过字节输出流，输出到客户端
            lineCaptcha.write(outputStream);
            outputStream.flush();
            outputStream.close();


        }
        filterChain.doFilter(servletRequest,servletResponse);


    }

    @Override
    public void destroy() {
        log.info("RateLimitFilter destroy");
    }
    private static class UserRequest{
        // 用户请求计数
        int count;
        // 最后一次请求的时间戳
        long timestamp;

        UserRequest(int count,long timestamp){
            this.count=count;
            this.timestamp=timestamp;
        }

    }
}
