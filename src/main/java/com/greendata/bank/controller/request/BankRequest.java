package com.greendata.bank.controller.request;

import java.io.Serializable;

public class BankRequest implements Serializable {
    private Long id;
    private String name;
    private Long bic;

    public BankRequest() {
    }

    public BankRequest(Long id, String name, Long bic) {
        this.id = id;
        this.name = name;
        this.bic = bic;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Long getBic() {
        return bic;
    }
}
