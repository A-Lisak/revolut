package com.revolut.resources;

import com.revolut.model.Transfer;
import com.revolut.services.TransferService;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("transfer")
@Singleton
public class TransferResource {
    @Inject
    TransferService transferService;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Transfer transfer(Transfer transfer) {
        return transferService.transfer(transfer);
    }
}
