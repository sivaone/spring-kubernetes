package com.sivanagireddy.notebook.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "notebook")
@Getter
@Setter
public class NotebookProperties {
    @Value("${uploadDir:/tmp/uploads/}")
    private String uploadDir;
}
