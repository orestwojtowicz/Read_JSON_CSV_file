package com.wojtowicz.file_reader.docs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Damian Wójtowicz
 * @project file_reader
 * @date 28.01.20
 */

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    Contact contact = new Contact(
            "Damian Wójtowicz",
            "https://orestwojtowicz/github.io",
            "damianwojtowicz94@gmail.com"
    );

    List<VendorExtension> vendorExtensions = new ArrayList<>();

    ApiInfo apiInfo = new ApiInfo(
            "File Reader",
            "Documentation for REST endpoints",
            "1.0",
            "https://github.com/orestwojtowicz/Read_JSON_CSV_file",
            contact,
            "licence - MIT",
            "",
            vendorExtensions
    );

    @Bean
    public Docket apiDocket() {

        return (new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.wojtowicz.file_reader"))
                .paths(PathSelectors.any())
                .build());
    }
}