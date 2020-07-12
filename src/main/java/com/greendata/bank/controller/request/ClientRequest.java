package com.greendata.bank.controller.request;

import com.greendata.bank.entity.BusinessEntity;
import com.greendata.bank.entity.dto.DepositDto;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class ClientRequest implements Serializable {
    private Long id;
    private String name;
    private String shortName;
    private String address;
    private BusinessEntity businessType;

    public ClientRequest() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    public String getAddress() {
        return address;
    }

    public BusinessEntity getBusinessType() {
        return businessType;
    }
}
