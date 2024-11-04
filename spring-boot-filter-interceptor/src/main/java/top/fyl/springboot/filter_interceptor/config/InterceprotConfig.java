package top.fyl.springboot.filter_interceptor.config;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;



import top.fyl.springboot.filter_interceptor.interceptor.AuthInterceptor;
import top.fyl.springboot.filter_interceptor.interceptor.CORSInterceptor;
import top.fyl.springboot.filter_interceptor.interceptor.FileUploadInterceptor;
import top.fyl.springboot.filter_interceptor.interceptor.LoggingInterceptor;

/**
 * @author dfysa
 * @data 2024/11/4 下午2:54
 * @description
 */
@Configuration
@Slf4j
@AllArgsConstructor
public class InterceprotConfig implements WebMvcConfigurer {
    private  final LoggingInterceptor loggingInterceptor;
    private  final AuthInterceptor authInterceptor;
    private final FileUploadInterceptor fileUploadInterceptor;
    //private final CORSInterceptor corsInterceptor;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加了loggingInterceptor拦截器，拦截所有请求
//        registry.addInterceptor(loggingInterceptor).addPathPatterns("/**");
//        registry.addInterceptor(authInterceptor).addPathPatterns("/**");
//        registry.addInterceptor(corsInterceptor).addPathPatterns("/**");
//        registry.addInterceptor(loggingInterceptor).addPathPatterns("/**");
//        registry.addInterceptor(authInterceptor).addPathPatterns("/**");
        registry.addInterceptor(fileUploadInterceptor).addPathPatterns("/api/oss/upload"); // 只对上传接口生效
    }
    // 全局跨域
//        @Bean
//        public CorsFilter corsFilter(){
//            final UrlBasedCorsConfigurationSource source=new UrlBasedCorsConfigurationSource();
//            final CorsConfiguration corsConfiguration=new CorsConfiguration();
//            corsConfiguration.setAllowCredentials(true);
//            corsConfiguration.addAllowedHeader("*");
//            corsConfiguration.addAllowedOriginPattern("*");
//            corsConfiguration.addAllowedMethod("*");
//            source.registerCorsConfiguration("/**",corsConfiguration);
//            return new CorsFilter(source);
//        }

}
