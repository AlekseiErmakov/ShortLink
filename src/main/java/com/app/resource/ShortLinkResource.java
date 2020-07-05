package com.app.resource;

import com.app.dto.AddShortLinkRequest;
import com.app.dto.AddShortLinkResponse;
import com.app.model.ShortLink;
import com.app.service.ShortLinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@Path("")
@Service
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ShortLinkResource {

    private ShortLinkService shortLinkService;

    @Autowired
    public ShortLinkResource(ShortLinkService shortLinkService) {
        this.shortLinkService = shortLinkService;
    }

    @POST
    @Path("/generate")
    public AddShortLinkResponse cutLink(AddShortLinkRequest request) {

        ShortLink shortLink = new ShortLink();
        shortLink.setOriginal(request.getOriginal());
        ShortLink fromDb = shortLinkService.addShortLink(shortLink);

        AddShortLinkResponse response = new AddShortLinkResponse(fromDb.getLink());
        return response;
    }

    @GET
    @Path("l/{uuid}")
    public Response getOriginalResource(@PathParam("uuid") String uuid) throws URISyntaxException {
        String root = "/l/";
        ShortLink shortLink = shortLinkService.findByLink(root + uuid);
        URI uri = new URI(shortLink.getOriginal());
        return Response.temporaryRedirect(uri).build();
    }

    @GET
    @Path("/stats/l/{uuid}")
    public ShortLink getFullLinkStats(@PathParam("uuid") String uuid) {
        String root = "/l/";
        ShortLink shortLink = shortLinkService.findByLinkWithStats(root + uuid);
        return shortLink;
    }

    @GET
    @Path("/stats")
    public List<ShortLink> getSublist(@QueryParam("page") int page, @QueryParam("count") int count) {
        return shortLinkService.findSubList(page, count);
    }
}
