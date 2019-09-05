package com.revolut.client;

import com.revolut.model.Account;
import com.revolut.model.Transaction;
import com.revolut.model.Transfer;
import com.revolut.services.TransferService;
import org.glassfish.jersey.jackson.JacksonFeature;

import javax.ws.rs.client.*;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class MoneyTransferClient {
    private final WebTarget target;

    private static final GenericType<List<Transaction>> TRANSACTIONS_LIST_TYPE = new GenericType<List<Transaction>>() {
    };

    public MoneyTransferClient(String baseUri) {
        Client client = ClientBuilder.newClient();
        client.register(TransferService.class);
        client.register(JacksonFeature.class);
        target = client.target(baseUri);
    }

    public Account createAccount() {
        return createAccount(null);
    }

    public Account createAccount(Account requested) {
        return target.path("accounts").request(MediaType.APPLICATION_JSON).post(Entity.json(requested), Account.class);
    }

    public Invocation.Builder accountByNo(int accountNo) {
        return target.path("accounts/" + accountNo).request(MediaType.APPLICATION_JSON);
    }

    public Account getAccountByNo(int accountNo) {
        return accountByNo(accountNo).get(Account.class);
    }

    public Response transfer(Transfer transfer) {
        return target.path("transfer").request(MediaType.APPLICATION_JSON).post(Entity.json(transfer));
    }

    public List<Transaction> getAccountTransactions(int accountNo) {
        return target.path("accounts/" + accountNo + "/transactions").request(MediaType.APPLICATION_JSON).get(TRANSACTIONS_LIST_TYPE);
    }
}
