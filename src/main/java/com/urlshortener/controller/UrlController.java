package com.urlshortener.controller;

import com.urlshortener.model.UrlRequest;
import com.urlshortener.model.UrlResponse;
import com.urlshortener.service.UrlShortenerService;
import javax.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class UrlController {

    private final UrlShortenerService urlShortenerService;

    public UrlController(UrlShortenerService urlShortenerService) {
        this.urlShortenerService = urlShortenerService;
    }

    /**
     * Shorten a full URL.
     * POST /shorten
     * Body: { "originalUrl": "https://www.example.com/some/very/long/path" }
     */
    @PostMapping("/shorten")
    public ResponseEntity<UrlResponse> shortenUrl(@Valid @RequestBody UrlRequest request) {
        UrlResponse response = urlShortenerService.shorten(request.getOriginalUrl());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * Redirect short URL to original URL.
     * GET /{shortCode}
     */
    @GetMapping("/{shortCode}")
    public ResponseEntity<Void> redirect(@PathVariable String shortCode) {
        Optional<String> originalUrl = urlShortenerService.getOriginalUrl(shortCode);

        if (originalUrl.isPresent()) {
            HttpHeaders headers = new HttpHeaders();
            headers.add(HttpHeaders.LOCATION, originalUrl.get());
            return ResponseEntity.status(HttpStatus.FOUND).headers(headers).build();
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
}
