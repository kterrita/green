package com.greendata.bank.entity.mapper;

import com.greendata.bank.controller.request.DepositRequest;
import com.greendata.bank.entity.Bank;
import com.greendata.bank.entity.Client;
import com.greendata.bank.entity.Deposit;
import com.greendata.bank.entity.dto.DepositDto;

import java.time.Instant;

public class DepositMapper {

    public DepositDto toDto(Deposit deposit) {
        DepositDto dto = new DepositDto();
        dto.setId(deposit.getId());
        dto.setCreated(deposit.getCreated());
        dto.setInterestRate(deposit.getInterestRate());
        dto.setTerm(deposit.getTerm());
        return dto;
    }

    public Deposit toEntity(DepositRequest request, Bank bank, Client client) {
        Deposit deposit = new Deposit();
        deposit.setId(request.getId());
        deposit.setCreated(request.getCreated() == null ? Instant.now() : request.getCreated());
        deposit.setInterestRate(request.getInterestRate());
        deposit.setTerm(request.getTerm());
        deposit.setBank(bank);
        deposit.setClient(client);
        return deposit;
    }
}
