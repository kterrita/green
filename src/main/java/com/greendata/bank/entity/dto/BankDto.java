package com.greendata.bank.entity.dto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BankDto bankDto = (BankDto) o;
        return id.equals(bankDto.id) &&
                name.equals(bankDto.name) &&
                bic.equals(bankDto.bic) &&
                Objects.equals(deposits, bankDto.deposits);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, bic, deposits);
    }
}
