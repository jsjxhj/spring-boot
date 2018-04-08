package com.owen.springboot.security.repository;

import com.owen.springboot.security.entity.Url;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UrlRepository extends JpaRepository<Url, Long> {
}
