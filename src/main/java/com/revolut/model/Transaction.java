package com.revolut.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.persistence.*;

@Entity
public class Transaction {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private long id;

    @JsonUnwrapped
    @Embedded
    private Money amount;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "accountNo")
    @JsonIgnore
    private Account account;

    public Transaction() {
    }

    public Transaction(Account account, Money amount) {
        this.amount = amount;
        this.account = account;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Money getAmount() {
        return amount;
    }

    public void setAmount(Money amount) {
        this.amount = amount;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
