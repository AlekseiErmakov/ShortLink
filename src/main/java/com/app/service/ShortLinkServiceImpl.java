package com.app.service;


import com.app.exception.LinkNotFoundException;
import com.app.model.ShortLink;
import com.app.repository.ShortLinkRepository;
import com.app.util.StreamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
        try{
            ShortLink byLink = findByLink(shortLink.getLink());
            return byLink;
        }catch (LinkNotFoundException ex){
            return shortLinkRepository.saveAndFlush(shortLink);
        }
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
    public List<ShortLink> findSubList(int page, int amount) {
        if (page == 0 || amount == 0){
            return new ArrayList<>();
        }
        List<ShortLink> all = findAll();
        int pageCount = all.size() / amount + all.size()%amount;

        if (amount > all.size()){
            return findAll();
        }
        if (page > pageCount){
            findSubList(pageCount,amount,all);
        }
        return findSubList(page,amount,all);
    }

    private List<ShortLink> findSubList(int page, int amount, List<ShortLink> all){
        int start = (page - 1) * amount;
        int end;
        if ((start + amount) >= all.size()){
            end = all.size();
        } else {
            end = start + amount;
        }
        return all.subList(start,end);
    }

    @Override
    public ShortLink findByLink(String link) {
        ShortLink shortLink = shortLinkRepository.findByLink(link)
                .orElseThrow(() -> new LinkNotFoundException(link));

        return shortLinkRepository.saveAndFlush(shortLink);
    }

    @Override
    public ShortLink findByLinkWithStats(String link) {
        ShortLink find = findAll().stream()
                .filter(shortLink -> shortLink.getLink().equals(link))
                .findFirst().orElseThrow(() -> new LinkNotFoundException(link));
        return find;
    }



}
