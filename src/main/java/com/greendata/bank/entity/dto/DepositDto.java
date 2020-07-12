package com.greendata.bank.entity.dto;

import java.io.Serializable;
import java.time.Instant;

public class DepositDto implements Serializable {

    private Long id;
    private Instant created;
    private Double interestRate;
    private Short term;

    public DepositDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getCreated() {
        return created;
    }

    public void setCreated(Instant created) {
        this.created = created;
    }

    public Double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(Double interestRate) {
        this.interestRate = interestRate;
    }

    public Short getTerm() {
        return term;
    }

    public void setTerm(Short term) {
        this.term = term;
    }
}
