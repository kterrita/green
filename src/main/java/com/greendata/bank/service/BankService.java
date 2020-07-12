package com.greendata.bank.service;

import com.greendata.bank.controller.request.BankRequest;
import com.greendata.bank.entity.Bank;
import com.greendata.bank.entity.dto.BankDto;
import com.greendata.bank.entity.mapper.BankMapper;
import com.greendata.bank.exception.BankNotFoundException;
import com.greendata.bank.repository.BankRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BankService {
    private final BankMapper bankMapper = new BankMapper();
    private final BankRepository bankRepository;

    public BankService(BankRepository bankRepository) {
        this.bankRepository = bankRepository;
    }

    public List<BankDto> getAllBanks() {
        final List<Bank> banks = bankRepository.findAll();
        return banks.stream()
                .map(bankMapper::toDto)
                .collect(Collectors.toList());
    }

    public BankDto getBankById(Long bankId) {
        return bankMapper.toDto(
                bankRepository.findById(bankId)
                        .orElseThrow(() -> new BankNotFoundException(String.format("Bank with id %d is not found", bankId)))
        );
    }

    public List<BankDto> getAllBanksOrderedAsc(String property) {
        final List<Bank> banks = bankRepository.findAll(Sort.by(property).ascending());
        return banks.stream()
                .map(bankMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<BankDto> getAllBanksOrderedDesc(String property) {
        final List<Bank> banks = bankRepository.findAll(Sort.by(property).descending());
        return banks.stream()
                .map(bankMapper::toDto)
                .collect(Collectors.toList());
    }

    public BankDto findBankByName(String name) {
        return bankMapper.toDto(
                bankRepository.findBankByName(name)
                        .orElseThrow(() -> new BankNotFoundException(String.format("Bank with name %s is not found", name)))
        );
    }

    public BankDto findBankByBic(Long bic) {
        return bankMapper.toDto(
                bankRepository.findBankByBic(bic)
                        .orElseThrow(() -> new BankNotFoundException(String.format("Bank with bic %s is not found", bic)))
        );
    }

    public BankDto create(BankRequest request) {
        final Bank bank = bankRepository.save(bankMapper.toEntity(request));
        return bankMapper.toDto(bank);
    }

    public BankDto update(BankRequest request) {
        final Bank bank = bankRepository.save(bankMapper.toEntity(request));
        return bankMapper.toDto(bank);
    }

    public void delete(Long id) {
        bankRepository.deleteById(id);
    }
}
