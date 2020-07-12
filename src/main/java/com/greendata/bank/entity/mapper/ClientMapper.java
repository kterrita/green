package com.greendata.bank.entity.mapper;

import com.greendata.bank.controller.request.ClientRequest;
import com.greendata.bank.entity.Client;
import com.greendata.bank.entity.Deposit;
import com.greendata.bank.entity.dto.ClientDto;
import com.greendata.bank.entity.dto.DepositDto;

import java.util.HashSet;
import java.util.Set;

public class ClientMapper {
    private final DepositMapper depositMapper = new DepositMapper();

    public ClientDto toDto(Client client) {
        final ClientDto dto = new ClientDto();
        dto.setId(client.getId());
        dto.setName(client.getName());
        dto.setShortName(client.getShortName());
        dto.setAddress(client.getAddress());
        dto.setBusinessType(client.getBusinessType());
        Set<DepositDto> depositDtos = new HashSet<>();
        for (Deposit bankDeposit : client.getDeposits()) {
            depositDtos.add(depositMapper.toDto(bankDeposit));
        }
        dto.setDeposits(depositDtos);
        return dto;
    }

    public Client toEntity(ClientRequest request) {
        Client client = new Client();
        client.setId(request.getId());
        client.setName(request.getName());
        client.setShortName(request.getShortName());
        client.setAddress(request.getAddress());
        client.setBusinessType(request.getBusinessType());
        return client;
    }

}
