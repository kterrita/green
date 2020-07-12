package com.greendata.bank.entity.dto;

import com.greendata.bank.entity.BusinessEntity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ClientDto implements Serializable {
    private Long id;
    private String name;
    private String shortName;
    private String address;
    private BusinessEntity businessType;
    private Set<DepositDto> deposits = new HashSet<>();

    public ClientDto() {
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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public BusinessEntity getBusinessType() {
        return businessType;
    }

    public void setBusinessType(BusinessEntity businessType) {
        this.businessType = businessType;
    }

    public Set<DepositDto> getDeposits() {
        return deposits;
    }

    public void setDeposits(Set<DepositDto> deposits) {
        this.deposits = deposits;
    }
}
