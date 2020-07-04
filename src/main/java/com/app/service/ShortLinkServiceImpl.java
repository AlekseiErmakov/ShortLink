package com.app.service;



import com.app.exception.LinkNotFoundException;
import com.app.model.ShortLink;
import com.app.repository.ShortLinkRepository;
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
        List<ShortLink> all = shortLinkRepository.findAll();
        List<ShortLink> sorted = all.stream()
                .sorted((Comparator.comparing(ShortLink::getCount)))
                .collect(Collectors.toList());
        for (int i = 0; i < sorted.size(); i++) {
            long rank = i + 1;
            sorted.get(i).setRank(rank);
        }
        return sorted;
    }

    @Override
    public ShortLink findByLink(String link) {
        ShortLink shortLink = shortLinkRepository.findByLink(link)
                .orElseThrow(() -> new LinkNotFoundException(link));
        updateScore(shortLink);
        return shortLink;
    }

    private void updateScore(ShortLink shortLink) {
        long count = shortLink.getCount();
        shortLink.setCount(++count);
        shortLinkRepository.save(shortLink);
    }
}
