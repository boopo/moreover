package com.lv.spring.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private ApiInfo commentApiInfo(){

        return new ApiInfoBuilder()
                .title("矿且-API文档")
                .description("矿且的APP")
                .version("1.0")
                .contact(new Contact("lvyingzhao", "https://www.lvyingzhao.cn", "2221233575@qq.com"))
                .build();
    }

    @Bean
    public Docket webApiConfig(){

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("commentApi")
                .apiInfo(commentApiInfo())
                .select()
                //只显示api路径下的页面
                .paths(Predicates.and(PathSelectors.regex("/comments/.*")))
                .build();

    }

    private ApiInfo userApiInfo(){

        return new ApiInfoBuilder()
                .title("矿且-API文档")
                .description("矿且的APP")
                .version("1.0")
                .contact(new Contact("lvyingzhao", "https://www.lvyingzhao.cn", "2221233575@qq.com"))
                .build();
    }

    @Bean
    public Docket userApiConfig(){

        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("userApi")
                .apiInfo(userApiInfo())
                .select()
                //只显示api路径下的页面
                .paths(Predicates.and(PathSelectors.regex("/.*")))
                .build();

    }




}
