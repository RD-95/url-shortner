package com.urlshortener.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class UrlRequest {

    @NotBlank(message = "URL must not be blank")
    @Pattern(
        regexp = "^(https?://).*",
        message = "URL must start with http:// or https://"
    )
    private String originalUrl;

    public UrlRequest() {}

    public UrlRequest(String originalUrl) {
        this.originalUrl = originalUrl;
    }

    public String getOriginalUrl() {
        return originalUrl;
    }

    public void setOriginalUrl(String originalUrl) {
        this.originalUrl = originalUrl;
    }
}
