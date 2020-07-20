package com.sivanagireddy.notebook.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.resource.PathResourceResolver;

@Configuration
@EnableConfigurationProperties(NotebookProperties.class)
public class NotebookConfig implements WebMvcConfigurer {

    @Autowired
    private NotebookProperties properties;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        System.out.println("Upload dir: " + properties.getUploadDir());
        registry.addResourceHandler("/uploads/**")
                .addResourceLocations("file:" + properties.getUploadDir())
                .setCachePeriod(3600)
                .resourceChain(true)
                .addResolver(new PathResourceResolver());
    }
}
