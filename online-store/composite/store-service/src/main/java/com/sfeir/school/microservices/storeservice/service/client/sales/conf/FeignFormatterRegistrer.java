package com.sfeir.school.microservices.storeservice.service.client.sales.conf;

import org.springframework.cloud.netflix.feign.FeignFormatterRegistrar;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;

/**
 * Created by youssefguenoun on 29/06/2017.
 */
@Configuration
public class FeignFormatterRegistrer implements FeignFormatterRegistrar {
    @Override
    public void registerFormatters(FormatterRegistry registry) {
        registry.addFormatter(dateFormatter());
    }

    private DateFormatter dateFormatter(){
        return new DateFormatter();
    }
}
