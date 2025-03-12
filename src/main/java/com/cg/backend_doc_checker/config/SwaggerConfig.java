package com.cg.backend_doc_checker.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {
    //This class is used for customize swagger

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(getInfo())
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo getInfo() {

//		return new ApiInfo("Document Checker Application:", "1.0", "Terms of Service", new Contact("Saurabh","https://learnwithsaurabh.com"), "Liences of APIs", "API license URL", Collections.EMPTY_LIST);
        return new ApiInfo(
                "Document Checker Application",
                "This project is developed by Saurabh",
                "1.0",
                "urn:tos",
//			      Contact(String name, String url, String email)
                new Contact("Saurabh", "https://learnwithsaurabh.com", "saurabhkumar93289@gmail.com"),
                "Liences 2.0",
                "API license URL: http://www.apache.org/licenses/LICENSE-2.0",
                Collections.EMPTY_LIST);
    }
}
