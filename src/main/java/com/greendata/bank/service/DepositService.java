package com.greendata.bank.service;

import com.greendata.bank.entity.Deposit;
import com.greendata.bank.entity.dto.DepositDto;
import com.greendata.bank.entity.mapper.DepositMapper;
import com.greendata.bank.exception.BankNotFoundException;
import com.greendata.bank.repository.DepositRepository;
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

    public DepositService(DepositRepository depositRepository) {
        this.depositRepository = depositRepository;
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
}
