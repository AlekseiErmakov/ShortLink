package com.app.repository;

import com.app.model.ShortLink;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/applicationContext.xml")
public class ShortLinkRepositoryTest {

    @Autowired
    private ShortLinkRepository repository;

    private static String original = "https://mvnrepository.com/artifact/org.springframework/spring-web";
    private static ShortLink shortLink = new ShortLink();

    static {
        shortLink.setOriginal(original);

    }

    @Before
    public void setUp(){
        repository.deleteAll();
    }

    @Test
    public void testSaveLink() {
        ShortLink shortLink = repository.saveAndFlush(ShortLinkRepositoryTest.shortLink);

        assertTrue(shortLink.getLink().startsWith("/api/l/"));

        repository.delete(shortLink);

    }

    @Test
    public void findByLink() {
        ShortLink shortLink = repository.saveAndFlush(ShortLinkRepositoryTest.shortLink);

        assertEquals(shortLink,repository.findByLink(shortLink.getLink()).get());
        repository.delete(shortLink);
    }

    @Test
    public void findByOrderByCountDesc() {
        ShortLink one = new ShortLink();
        one.setOriginal("One");
        one.setCount(100L);

        ShortLink two = new ShortLink();
        two.setOriginal("two");
        two.setCount(200L);

        ShortLink three = new ShortLink();
        three.setOriginal("three");
        three.setCount(2L);

        ShortLink oneLink = repository.saveAndFlush(one);
        ShortLink twoLink = repository.saveAndFlush(two);
        ShortLink threeLink = repository.saveAndFlush(three);

        List<ShortLink> byOrderByCountDesc = repository.findByOrderByCountDesc();

        assertEquals(twoLink,byOrderByCountDesc.get(0));

        assertEquals(Arrays.asList(twoLink,oneLink,threeLink),byOrderByCountDesc);

        repository.delete(byOrderByCountDesc);
    }
}