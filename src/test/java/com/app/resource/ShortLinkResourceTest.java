package com.app.resource;

import com.app.dto.AddShortLinkRequest;
import com.app.dto.AddShortLinkResponse;
import com.app.model.ShortLink;
import com.app.service.ShortLinkService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class ShortLinkResourceTest {

    @Mock
    private ShortLinkService shortLinkService;

    @InjectMocks
    private ShortLinkResource shortLinkResource;

    private static String original = "http://google.com";
    private static ShortLink google = new ShortLink();
    private static ShortLink googleWithLink = new ShortLink();
    private static String link = "/api/l/12yg12y3g";
    private static String UUID = "12yg12y3g";

    static {
        google.setOriginal(original);
        googleWithLink.setLink(link);
        googleWithLink.setCount(10);
        googleWithLink.setOriginal(original);
        googleWithLink.setId(2L);
        googleWithLink.setRank(1);
    }

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void cutLink() {
        AddShortLinkRequest request = new AddShortLinkRequest(original);
        Mockito.when(shortLinkService.addShortLink(google)).thenReturn(googleWithLink);
        AddShortLinkResponse response = new AddShortLinkResponse(googleWithLink.getLink());

        assertEquals(response, shortLinkResource.cutLink(request));
    }

    @Test
    public void getOriginalResource() throws URISyntaxException {

        Mockito.when(shortLinkService.findByLink(link)).thenReturn(google);
        URI uri = new URI(original);
        Response build = Response.temporaryRedirect(uri).build();
        assertEquals(build.getStatus(), shortLinkResource.getOriginalResource(UUID).getStatus());
    }


    @Test
    public void getFullLinkStats() {
        Mockito.when(shortLinkService.findByLinkWithStats(link)).thenReturn(googleWithLink);

        assertEquals(googleWithLink, shortLinkResource.getFullLinkStats(UUID));
    }

    @Test
    public void getSublist() {
        Mockito.when(shortLinkService.findSubList(1, 1)).thenReturn(Arrays.asList(googleWithLink));
        assertEquals(Arrays.asList(googleWithLink), shortLinkResource.getSublist(1, 1));
    }
}