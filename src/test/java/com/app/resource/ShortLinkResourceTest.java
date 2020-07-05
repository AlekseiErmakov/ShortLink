package com.app.resource;

import com.app.dto.AddShortLinkRequest;
import com.app.dto.AddShortLinkResponse;
import com.app.model.ShortLink;
import com.app.service.ShortLinkService;
import com.app.service.ShortLinkServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;

public class ShortLinkResourceTest {

    private ShortLinkServiceImpl shortLinkService;

    private ShortLinkResource shortLinkResource;

    private static String original = "http://google.com";
    private static ShortLink google = new ShortLink();

    static {
        google.setOriginal(original);
        google.setLink("/l/121827jkljh");
    }

    @Before
    public void setUp() throws Exception {
        ShortLinkService shortLinkService = Mockito.mock(ShortLinkService.class);
        shortLinkResource = new ShortLinkResource(shortLinkService);
    }

    @Test
    public void cutLink() {
        AddShortLinkRequest request = new AddShortLinkRequest(original);

        Mockito.when(shortLinkService.addShortLink(google)).thenReturn(google);

        AddShortLinkResponse response = new AddShortLinkResponse(google.getLink());

        assertEquals(response, shortLinkResource.cutLink(request));

    }

    @Test
    public void getOriginalResource() {
    }

    @Test
    public void getFullLinkStats() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void getSublist() {
    }
}