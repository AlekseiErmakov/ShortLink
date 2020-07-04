package com.app.resource;


import com.app.exception.LinkNotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;


@Provider
public class NotFoundExceptionHandler
        implements ExceptionMapper<LinkNotFoundException> {

    public Response toResponse(LinkNotFoundException ex) {
        return Response.status(Response.Status.NOT_FOUND).build();
    }
}