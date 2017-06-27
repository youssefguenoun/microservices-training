package com.api.tuto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

@SpringBootApplication
@EnableZuulProxy
public class VersionProxy {

    public static void main(String[] args) {
        SpringApplication.run(VersionProxy.class, args);
    }

}
