package com.app.repository;

import com.app.model.ShortLink;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import java.util.List;

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
    @Test
    public void testSaveLink() {

    }

    @Test
    public void testUpdateLink() {

    }

    @Test
    public void findByLink() {
    }

    @Test
    public void findByOrderByCountDesc() {
    }
}