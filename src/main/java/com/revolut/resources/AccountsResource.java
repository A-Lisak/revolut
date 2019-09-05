package com.revolut.resources;

import com.revolut.model.Account;
import com.revolut.persistence.PersistenceUnit;
import com.revolut.services.AccountService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("accounts")
public class AccountsResource extends BaseResource {
    @Inject
    private AccountService accountService;

    @Inject
    public AccountsResource(PersistenceUnit persistenceUnit) {
        super(persistenceUnit.createEntityManager());
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Account create(Account requested) {
        return accountService.create(requested);
    }

    @Path("{accountNo}")
    public AccountResource getAccount(@PathParam("accountNo") int accountNo) {
        return new AccountResource(accountNo, getEntityManager());
    }
}
