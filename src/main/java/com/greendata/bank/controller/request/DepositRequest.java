package com.greendata.bank.controller.request;

import java.io.Serializable;
import java.time.Instant;

public class DepositRequest implements Serializable {
    private Long id;
    private Instant created;
    private Double interestRate;
    private Short term;
    private Long clientId;
    private Long bankId;

    public DepositRequest() {
    }

    public Long getId() {
        return id;
    }

    public Instant getCreated() {
        return created;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public Short getTerm() {
        return term;
    }

    public Long getClientId() {
        return clientId;
    }

    public Long getBankId() {
        return bankId;
    }
}
