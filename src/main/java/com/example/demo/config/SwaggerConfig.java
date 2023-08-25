package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig   {

    /**
     * *
        swagger 就是这个页面就可以变成swagger
     */

    @Bean
    public Docket docket(Environment environment){
//       显示swagger 环境
//        Profiles profiles= Profiles.of("dev","test");
//        获得项目环境
//      boolean flag=   environment.acceptsProfiles(profiles);

        return  new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("吴兰昆")
                .enable(true)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))
                .build();
    }
        //配置多个分组
            @Bean
            public Docket docket1(){
                return  new Docket(DocumentationType.SWAGGER_2).groupName("A");
            }
            @Bean
            public Docket docket2(){
                return  new Docket(DocumentationType.SWAGGER_2).groupName("B");
            }


    //配置多个分组结束

    private ApiInfo apiInfo(){
        Contact contact = new Contact("吴兰昆","","1191041701@qq.com");
        return  new ApiInfo("开发文档",
                "再小的帆也能远航",
                "1.0",
                "urn:tos",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList());
    }
}
