package com.greendata.bank.service;

import com.greendata.bank.controller.request.DepositRequest;
import com.greendata.bank.entity.Bank;
import com.greendata.bank.entity.Client;
import com.greendata.bank.entity.Deposit;
import com.greendata.bank.entity.dto.DepositDto;
import com.greendata.bank.entity.mapper.DepositMapper;
import com.greendata.bank.exception.BankNotFoundException;
import com.greendata.bank.exception.ClientNotFoundException;
import com.greendata.bank.exception.DepositNotFoundException;
import com.greendata.bank.exception.IdIsRequiredException;
import com.greendata.bank.repository.BankRepository;
import com.greendata.bank.repository.ClientRepository;
import com.greendata.bank.repository.DepositRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class DepositService {
    private final DepositMapper depositMapper = new DepositMapper();
    private final DepositRepository depositRepository;
    private final ClientRepository clientRepository;
    private final BankRepository bankRepository;

    public DepositService(DepositRepository depositRepository, ClientRepository clientRepository, BankRepository bankRepository) {
        this.depositRepository = depositRepository;
        this.clientRepository = clientRepository;
        this.bankRepository = bankRepository;
    }

    public List<DepositDto> getAllDeposits() {
        final List<Deposit> deposits = depositRepository.findAll();
        return deposits.stream()
                .map(depositMapper::toDto)
                .collect(Collectors.toList());
    }

    public DepositDto getDepositById(Long depositId) {
        return depositMapper.toDto(
                depositRepository.findById(depositId)
                        .orElseThrow(() -> new BankNotFoundException(String.format("Deposit with id %d is not found", depositId)))
        );
    }

    public List<DepositDto> findAllByCreatedIsBeforeOrderByCreatedAsc(Instant time) {
        final List<Deposit> deposits = depositRepository.findAllByCreatedIsBeforeOrderByCreatedAsc(time);
        return deposits.stream()
                .map(depositMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<DepositDto> findAllByCreatedIsAfterOrderByCreatedAsc(Instant time) {
        final List<Deposit> deposits = depositRepository.findAllByCreatedIsAfterOrderByCreatedAsc(time);
        return deposits.stream()
                .map(depositMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<DepositDto> findDepositsByBankIdAndClientId(Long bankId, Long clientId) {
        final List<Deposit> deposits = depositRepository.findDepositsByBank_IdAndClient_Id(bankId, clientId);
        return deposits.stream()
                .map(depositMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<DepositDto> findDepositsByBankId(Long bankId) {
        final List<Deposit> deposits = depositRepository.findDepositsByBank_Id(bankId);
        return deposits.stream()
                .map(depositMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<DepositDto> findDepositsByClientId(Long clientId) {
        final List<Deposit> deposits = depositRepository.findDepositsByClient_Id(clientId);
        return deposits.stream()
                .map(depositMapper::toDto)
                .collect(Collectors.toList());
    }

    public DepositDto createOrUpdate(DepositRequest request) {
        final Long clientId = request.getClientId();
        final Long bankId = request.getBankId();
        if (clientId == null || bankId == null) {
            throw new IdIsRequiredException("Client id or Bank id is not provided.");
        }
        final Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(String.format("Client with id %d is not found", clientId)));
        final Bank bank = bankRepository.findById(bankId)
                .orElseThrow(() -> new ClientNotFoundException(String.format("Bank with id %d is not found", bankId)));
        final Deposit deposit = depositRepository.save(depositMapper.toEntity(request, bank, client));
        return depositMapper.toDto(deposit);
    }

    public void delete(Long id) {
        try {
            depositRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            throw new DepositNotFoundException(String.format("Deposit with id %d is not found", id));
        }
    }
}
