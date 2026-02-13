package com.shivam.shorten_url.Repository;

import com.shivam.shorten_url.Entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UrlRepo extends JpaRepository<Url, Long> {
    Optional<Url> findByShortUrl(String shortUrl);
}
