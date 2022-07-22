package com.cg.uploader;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
@Data
public class CloudinaryConfig {
    @Value("${application.uploader.cloud-name}")
    private String cloudName;
    @Value("${application.uploader.api-key}")
    private String apiKey;
    @Value("${application.uploader.api-secret}")
    private String apiSecret;

}
