package com.revolut.exceptions;

import org.glassfish.grizzly.http.util.HttpStatus;

import javax.persistence.RollbackException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RollbackExceptionMapper implements ExceptionMapper<RollbackException> {
    @Override
    public Response toResponse(RollbackException e) {
        return Response.status(HttpStatus.CONFLICT_409.getStatusCode()).
                entity("Related accounts are being updated.").
                type("text/plain").
                build();
    }
}
