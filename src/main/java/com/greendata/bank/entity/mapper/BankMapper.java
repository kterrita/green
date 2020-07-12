package com.greendata.bank.entity.mapper;

import com.greendata.bank.controller.request.BankRequest;
import com.greendata.bank.entity.Bank;
import com.greendata.bank.entity.Deposit;
import com.greendata.bank.entity.dto.BankDto;
import com.greendata.bank.entity.dto.DepositDto;

import java.util.HashSet;
import java.util.Set;

public class BankMapper {
    private final DepositMapper depositMapper = new DepositMapper();

    public BankDto toDto(Bank bank) {
        final BankDto dto = new BankDto();
        dto.setId(bank.getId());
        dto.setBic(bank.getBic());
        dto.setName(bank.getName());
        Set<DepositDto> depositDtos = new HashSet<>();
        for (Deposit bankDeposit : bank.getDeposits()) {
            depositDtos.add(depositMapper.toDto(bankDeposit));
        }
        dto.setDeposits(depositDtos);
        return dto;
    }

    public Bank toEntity(BankRequest request) {
        final Bank bank = new Bank();
        bank.setId(request.getId());
        bank.setBic(request.getBic());
        bank.setName(request.getName());
        return bank;
    }
}
