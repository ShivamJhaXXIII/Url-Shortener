package com.shivam.shorten_url.Controller;

import com.shivam.shorten_url.Service.UrlService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@Controller
public class WebController {

    private final UrlService service;

    public WebController(UrlService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("shortUrl", "");
        model.addAttribute("originalUrl", "");
        return "index";
    }

    @PostMapping("/shorten")
    public String shortenUrl(@RequestParam("url") String originalUrl, Model model) {
        try {
            String code = service.shorten(originalUrl);

            // Build short URL from current request
            String shortUrl = ServletUriComponentsBuilder.fromCurrentContextPath()
                    .path("/" + code)
                    .toUriString();

            model.addAttribute("shortUrl", shortUrl);
            model.addAttribute("originalUrl", originalUrl);
            model.addAttribute("success", true);
        } catch (Exception e) {
            model.addAttribute("error", "Failed to shorten URL: " + e.getMessage());
        }

        return "index";
    }
}

