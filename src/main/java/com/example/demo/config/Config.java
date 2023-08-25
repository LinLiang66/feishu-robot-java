package com.example.demo.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import common.JwtInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class Config implements WebMvcConfigurer {
    //分页插件
    @Bean
    public PaginationInterceptor paginationInterceptor(){
       return  new PaginationInterceptor();
   }

    //这个是重构token类的
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        System.out.println("addInterceptors运行,跳转token验证");
//        registry.addInterceptor(jwtInterceptor())
//                //添加拦截路径
//                .addPathPatterns("/**")
//                //添加放行路径
//                .excludePathPatterns("/custom/user","/docket")
//                .excludePathPatterns("/v2/api-docs",
//                        "/swagger-resources",
//                        "/swagger-resources/**",
//                        "/configuration/ui",
//                        "/configuration/security",
//                        "/swagger-ui.html/**",
//                        "/webjars/**");
//    }

    @Bean
    public JwtInterceptor jwtInterceptor() {
        System.out.println("jwtInterceptor运行,跳转token验证");
        //拦截器
        return new JwtInterceptor();
    }

    //这个是重构swagger
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    //registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");//这行是本来的
    //registry.addResourceHandler("/static/**").addResourceLocations("/www/wwwroot/java");//这行是修改的
        registry.addResourceHandler("/static/**").addResourceLocations("file:F:/filepath/");//这行是修改的

        registry.addResourceHandler("doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }


}

