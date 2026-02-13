package com.shivam.shorten_url.Controller;

import com.shivam.shorten_url.Service.UrlService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Map;

@RestController
public class UrlController {

    private final UrlService service;

    public UrlController(UrlService service) {
        this.service = service;
    }

    @PostMapping("/api/shorten")
    public Map<String, String> shorten(@RequestBody Map<String, String> body) {
        String code = service.shorten(body.get("url"));

        // Build short URL from current request
        String shortUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/" + code)
                .toUriString();

        return Map.of("shortUrl", shortUrl);
    }

    @GetMapping("/{code}")
    public ResponseEntity<Void> redirect(@PathVariable String code) {
        String url = service.originalUrl(code);

        return ResponseEntity.status(HttpStatus.FOUND)
                .location(URI.create(url))
                .build();
    }
}
