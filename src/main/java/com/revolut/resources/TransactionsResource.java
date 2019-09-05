package com.revolut.resources;

import com.revolut.model.Transaction;

import javax.persistence.EntityManager;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

public class TransactionsResource extends BaseResource {
    private AccountResource accountResource;
    public TransactionsResource(AccountResource accountResource, EntityManager entityManager) {
        super(entityManager);
        this.accountResource = accountResource;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Transaction> getTransactions() {
        return accountResource.getAccount().getTransactions();
    }
}
