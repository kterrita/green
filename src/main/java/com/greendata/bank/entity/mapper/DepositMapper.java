package com.greendata.bank.entity.mapper;

import com.greendata.bank.entity.Deposit;
import com.greendata.bank.entity.dto.DepositDto;

public class DepositMapper {

    public DepositDto toDto(Deposit deposit) {
        DepositDto dto = new DepositDto();
        dto.setId(deposit.getId());
        dto.setCreated(deposit.getCreated());
        dto.setInterestRate(deposit.getInterestRate());
        dto.setTerm(deposit.getTerm());
        return dto;
    }
}
