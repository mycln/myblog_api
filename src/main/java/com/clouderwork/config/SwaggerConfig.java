package com.clouderwork.config;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

import static springfox.documentation.builders.PathSelectors.regex;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket productApi() {
        String tokenstr = "A514890C9146D362D78DE76DCBDA9241";
        ParameterBuilder tokenParameterBuilder = new ParameterBuilder();
        ParameterBuilder timeParameterBuilder = new ParameterBuilder();
        ParameterBuilder signParameterBuilder = new ParameterBuilder();

        tokenParameterBuilder.name("token").defaultValue(tokenstr).description("用户token")
                .modelRef(new ModelRef("string")).parameterType("header").required(false).build();

        timeParameterBuilder.name("timestamp").defaultValue("1534402561000").description("时间戳")
                .modelRef(new ModelRef("string")).parameterType("header").required(false).build();

        signParameterBuilder.name("sign").defaultValue("sign").description("加密串")
                .modelRef(new ModelRef("string")).parameterType("header").required(false).build();

        List<Parameter> addParameters = new ArrayList<Parameter>();
        addParameters.add(tokenParameterBuilder.build());
        addParameters.add(signParameterBuilder.build());
        addParameters.add(timeParameterBuilder.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .globalOperationParameters(addParameters)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.clouderwork.api"))
                .paths(doFilteringRules())
                .build()
                .apiInfo(metaData());
    }

    //termsOfServiceUrl need Change
    private ApiInfo metaData() {
        ApiInfo apiInfo = new ApiInfo(
                "接口描述",
                "接口描述文档",
                "1.0",
                "",
                new Contact("Chang lina", "https://github.com/mycln", "changlina.1989@163.com"),
                "Apache License Version 2.0",
                "https://www.apache.org/licenses/LICENSE-2.0");
        return apiInfo;
    }
    private Predicate<String> doFilteringRules() {
//		return Predicates.not(PathSelectors.regex("/error.*"));
		return regex("/*.*");
	}

}
