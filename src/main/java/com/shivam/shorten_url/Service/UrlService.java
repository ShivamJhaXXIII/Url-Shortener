package com.shivam.shorten_url.Service;

import com.shivam.shorten_url.Entity.Url;
import com.shivam.shorten_url.Repository.UrlRepo;
import com.shivam.shorten_url.Utility.Base62Encoder;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UrlService {

    private final UrlRepo repo;
    private final Base62Encoder encoder;

    public UrlService(UrlRepo repo, Base62Encoder encoder) {
        this.encoder = encoder;
        this.repo = repo;
    }


    public String shorten(String originalUrl) {
        Url url = new Url();
        url.setOriginalUrl(originalUrl);

        Url saved = repo.save(url);

        String code = encoder.encode(saved.getId());
        saved.setShortUrl(code);

        repo.save(saved);
        return code;
    }

    public String originalUrl(String shortUrl) {
        Url url = repo.findByShortUrl(shortUrl)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "URL not found"));

        url.setClickCount(url.getClickCount() + 1);
        repo.save(url);
        return url.getOriginalUrl();
    }
}
