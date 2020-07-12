package com.greendata.bank.entity.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class BankDto implements Serializable {
    private Long id;
    private String name;
    private Long bic;
    private Set<DepositDto> deposits = new HashSet<>();

    public BankDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getBic() {
        return bic;
    }

    public void setBic(Long bic) {
        this.bic = bic;
    }

    public Set<DepositDto> getDeposits() {
        return deposits;
    }

    public void setDeposits(Set<DepositDto> deposits) {
        this.deposits = deposits;
    }

    @Override
    public String toString() {
        return "BankDto{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", bic=" + bic +
                '}';
    }
}
