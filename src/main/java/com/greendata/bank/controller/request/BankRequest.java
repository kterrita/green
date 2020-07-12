package com.greendata.bank.controller.request;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class BankRequest implements Serializable {
    private Long id;
    private String name;
    private Long bic;

    public BankRequest() {
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
