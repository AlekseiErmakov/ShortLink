package com.app.repository;

import com.app.model.ShortLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShortLinkRepository extends JpaRepository<ShortLink,Long> {
}
