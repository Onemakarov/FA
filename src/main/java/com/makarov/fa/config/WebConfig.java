package com.makarov.fa.config;

import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler(
                "/static/css/**",
                "/webjars/**")
                .addResourceLocations(
                        "classpath:/static/css/",
                        "classpath:/META-INF/resources/webjars/").setCachePeriod(0);
    }

}
