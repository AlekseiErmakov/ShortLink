package com.app.service;


import com.app.model.ShortLink;

import java.util.List;

public interface ShortLinkService {
    ShortLink addShortLink(ShortLink shortLink);

    List<ShortLink> findAll();

    ShortLink findByLink(String link);
}
