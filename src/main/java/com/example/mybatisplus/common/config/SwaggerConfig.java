package com.example.mybatisplus.common.config;
import com.fasterxml.classmate.Annotations;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;


@Configuration // 标明是配置类
@EnableSwagger2 //开启swagger功能
public class SwaggerConfig {
    @Bean
    public Docket docket(){
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("教师监考管理系统")
                .version("2.0")
                .description("教师监考系统项目接口文档")
                .build();
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.mybatisplus.web.controller"))
                .paths(PathSelectors.any())
                .build();
        return docket;
    }
    //配置Swagger信息——ApiInfo
    private  ApiInfo apiInfo(){
        //作者信息
        Contact contact = new Contact("Dopichen", "https://github.com/dopiChen/InvigilationSource", "1192597201@qq.com");
        return new ApiInfo(
                "Dopichen_SwaggerAPI",
                "Api Documentation",
                "1.0",
                "https://blog.csdn.net/bookssea",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList()
        );
    }
}
