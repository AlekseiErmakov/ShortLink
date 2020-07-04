package com.app.service;



import com.app.exception.LinkNotFoundException;
import com.app.model.ShortLink;
import com.app.repository.ShortLinkRepository;
import com.app.util.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShortLinkServiceImpl implements ShortLinkService {


    private ShortLinkRepository shortLinkRepository;

    @Autowired
    public ShortLinkServiceImpl(ShortLinkRepository shortLinkRepository) {
        this.shortLinkRepository = shortLinkRepository;
    }

    @Override
    @Transactional
    public ShortLink addShortLink(ShortLink shortLink) {
        return shortLinkRepository.save(shortLink);
    }

    @Override
    public List<ShortLink> findAll() {
        List<ShortLink> sortedLinks = shortLinkRepository.findByOrderByCountDesc();
        return StreamUtils.withIndexies(sortedLinks).map(shortLinkIndexValue -> {
            ShortLink value = shortLinkIndexValue.getValue();
            value.setRank(1 + shortLinkIndexValue.getIndex());
            return value;
        }).collect(Collectors.toList());

    }

    @Override
    public ShortLink findByLink(String link) {
        ShortLink shortLink = shortLinkRepository.findByLink(link)
                .orElseThrow(() -> new LinkNotFoundException(link));
        updateScore(shortLink);
        return shortLink;
    }

    @Override
    public ShortLink findByLinkWithStats(String link) {
        ShortLink find = findAll().stream()
                .filter(shortLink -> shortLink.getLink().equals(link))
                .findFirst().orElseThrow(()->new LinkNotFoundException(link));
        return find;
    }

    private void updateScore(ShortLink shortLink) {
        long count = shortLink.getCount();
        shortLink.setCount(++count);
        shortLinkRepository.save(shortLink);
    }
}
