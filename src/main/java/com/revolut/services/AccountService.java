package com.revolut.services;

import com.revolut.model.Account;

import javax.inject.Singleton;
import java.util.Arrays;
import java.util.Currency;
import java.util.List;

@Singleton
public class AccountService {
    private final static List<String> supportedCurrencies = Arrays.asList(
        "USD", "EUR", "RUB"
    );

    public Account create(Account requested) {
        Account account = new Account();

        if (requested != null) {
            if (requested.getBalance() != null) {
                verifyCurrencySupported(requested.getBalance().getCurrency());

                account.setBalance(requested.getBalance());
            }
        }

        account.save();
        return account;
    }

    private void verifyCurrencySupported(Currency currency) {
        if (currency == null)
            throw new IllegalArgumentException("Currency should be specified.");

        if (!supportedCurrencies.contains(currency.getCurrencyCode())) {
            throw new IllegalArgumentException(
                String.format("Unsupported currency: %s. Supported currencies are: %s.",
                    currency.getCurrencyCode(),
                    String.join(", ", supportedCurrencies)
                ));
        }
    }
}
