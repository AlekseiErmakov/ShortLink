package com.app.service;


import com.app.model.ShortLink;

import java.util.List;

public interface ShortLinkService {
    ShortLink addShortLink(ShortLink shortLink);

    List<ShortLink> findAll();

    List<ShortLink> findSubList(int page, int amount);

    ShortLink findByLink(String link);

    ShortLink findByLinkWithStats(String link);
}
