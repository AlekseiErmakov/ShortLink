package com.app.service;

import com.app.exception.LinkNotFoundException;
import com.app.model.ShortLink;
import com.app.repository.ShortLinkRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ShortLinkServiceImplTest {

    @Mock
    private ShortLinkRepository repository;

    @InjectMocks
    private ShortLinkServiceImpl shortLinkService;

    private static String google = "http://google.com";
    private static String mail = "http://mail.ru";
    private static String maven = "https://mvnrepository.com/artifact/org.mockito/mockito-all/1.10.19";

    private static String googleLink = "/l/1231238";
    private static String mailLink = "/l/qweqwe123";
    private static String mavenLink = "/l/123qesd";

    private static ShortLink googleShortLink = new ShortLink();
    private static ShortLink mailShortLink = new ShortLink();
    private static ShortLink mavenShortLink = new ShortLink();

    private static List<ShortLink> links = new ArrayList<>();

    static {
        mailShortLink.setOriginal(mail);
        mailShortLink.setLink(mailLink);
        mailShortLink.setCount(200L);

        mavenShortLink.setOriginal(maven);
        mavenShortLink.setLink(mavenLink);
        mailShortLink.setCount(11L);

        googleShortLink.setOriginal(google);
        googleShortLink.setLink(googleLink);
        googleShortLink.setCount(10L);

        links.add(mailShortLink);
        links.add(mavenShortLink);
        links.add(googleShortLink);
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        Mockito.when(repository.findByOrderByCountDesc()).thenReturn(links);
    }

    @Test
    public void addShortLink() {
        ShortLink shortLink = new ShortLink();
        shortLink.setOriginal(maven);
        shortLink.setLink("/l/1267127981dsj");
        Mockito.when(repository.saveAndFlush(shortLink)).thenReturn(shortLink);
        Mockito.when(repository.findByLink(shortLink.getLink())).thenReturn(Optional.ofNullable(null));
        ShortLink saved = shortLinkService.addShortLink(shortLink);

        assertEquals(maven, saved.getOriginal());

        assertTrue(saved.getLink().startsWith("/l/"));

    }

    @Test
    public void findAll() {
        List<ShortLink> all = shortLinkService.findAll();

        assertEquals(links, all);

        assertEquals(mailShortLink, all.get(0));
        assertEquals(1, all.get(0).getRank());

        assertEquals(mavenShortLink, all.get(1));
        assertEquals(2, all.get(1).getRank());

        assertEquals(googleShortLink, all.get(2));
        assertEquals(3, all.get(2).getRank());
    }

    @Test
    public void findSubList() {
        List<ShortLink> subList = shortLinkService.findSubList(1, 2);
        assertEquals(Arrays.asList(mailShortLink, mavenShortLink), subList);

        List<ShortLink> subList2 = shortLinkService.findSubList(2, 2);
        assertEquals(Arrays.asList(googleShortLink), subList2);
    }

    @Test
    public void findByLink() {
        Mockito.when(repository.findByLink(googleLink)).thenReturn(Optional.of(googleShortLink));
        Mockito.when(repository.saveAndFlush(googleShortLink)).thenReturn(googleShortLink);
        assertEquals(googleShortLink, shortLinkService.findByLink(googleLink));
    }

    @Test(expected = LinkNotFoundException.class)
    public void findByFakeLink() {
        String fake = "/l/317213jh12";

        Mockito.when(repository.findByLink(fake)).thenReturn(Optional.ofNullable(null));
        shortLinkService.findByLink(fake);
    }

    @Test
    public void findByLinkWithStats() {

        ShortLink foundLink = shortLinkService.findByLinkWithStats(mailLink);
        assertEquals(mailShortLink,foundLink);
    }
}