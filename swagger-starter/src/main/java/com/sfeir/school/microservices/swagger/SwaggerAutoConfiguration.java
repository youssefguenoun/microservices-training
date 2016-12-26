package com.sfeir.school.microservices.swagger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.awt.print.Pageable;
import java.util.Date;

import static com.google.common.base.Predicates.not;

/**
 * Created by abderrazakbouadma on 26/12/2016.
 */

@Configuration
@EnableSwagger2
@EnableConfigurationProperties(SwaggerConfigurationProperties.class)
public class SwaggerAutoConfiguration {

    public static final String DEFAULT_INCLUDE_PATTERN = "/.*";

    private final SwaggerConfigurationProperties swaggerConfigurationProperties;

    @Autowired
    public SwaggerAutoConfiguration(SwaggerConfigurationProperties swaggerConfigurationProperties) {
        this.swaggerConfigurationProperties = swaggerConfigurationProperties;
    }

    @Bean
    public Docket unsecuredDocket() {

        Contact contact = new Contact(
                swaggerConfigurationProperties.getContactName(),
                swaggerConfigurationProperties.getContactUrl(),
                swaggerConfigurationProperties.getContactEmail());

        ApiInfo apiInfo = new ApiInfo(
                swaggerConfigurationProperties.getTitle(),
                swaggerConfigurationProperties.getDescription(),
                swaggerConfigurationProperties.getVersion(),
                swaggerConfigurationProperties.getTermsOfServiceUrl(),
                contact,
                swaggerConfigurationProperties.getLicense(),
                swaggerConfigurationProperties.getLicenseUrl());

        //noinspection Guava
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .forCodeGeneration(true)
                .genericModelSubstitutes(ResponseEntity.class)
                .ignoredParameterTypes(Pageable.class)
                .ignoredParameterTypes(java.sql.Date.class)
                .directModelSubstitute(java.time.LocalDate.class, java.sql.Date.class)
                .directModelSubstitute(java.time.ZonedDateTime.class, Date.class)
                .directModelSubstitute(java.time.LocalDateTime.class, Date.class)
                .select()
                .paths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN))
                .paths(not(PathSelectors.regex("/error.*")))
                .paths(not(PathSelectors.regex("/admin.*")))
                .build();
    }

}
