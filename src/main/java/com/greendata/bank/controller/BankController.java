package com.greendata.bank.controller;

import com.greendata.bank.controller.request.BankRequest;
import com.greendata.bank.entity.dto.BankDto;
import com.greendata.bank.exception.IdIsRequiredException;
import com.greendata.bank.service.BankService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/banks")
public class BankController {
    private final BankService bankService;

    public BankController(BankService bankService) {
        this.bankService = bankService;
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<BankDto> getAllBanks() {
        return bankService.getAllBanks();
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public BankDto getBankById(@PathVariable Long id) {
        return bankService.getBankById(id);
    }

    @GetMapping("/sorted/{property}/asc")
    @ResponseStatus(HttpStatus.OK)
    public List<BankDto> getAllBanksOrderedAsc(@PathVariable String property) {
        return bankService.getAllBanksOrderedAsc(property);
    }

    @GetMapping("/sorted/{property}/desc")
    @ResponseStatus(HttpStatus.OK)
    public List<BankDto> getAllBanksOrderedDesc(@PathVariable String property) {
        return bankService.getAllBanksOrderedDesc(property);
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public BankDto findBankByName(@PathVariable String name) {
        return bankService.findBankByName(name);
    }

    @GetMapping("/bic/{bic}")
    @ResponseStatus(HttpStatus.OK)
    public BankDto findBankByBic(@PathVariable Long bic) {
        return bankService.findBankByBic(bic);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BankDto create(@RequestBody @NotNull BankRequest request) {
        return bankService.create(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public BankDto update(@RequestBody BankRequest request) {
        if (request.getId() == null) {
            throw new IdIsRequiredException();
        }

        return bankService.update(request);
    }

    @DeleteMapping
    @ResponseStatus(HttpStatus.OK)
    public void delete(@RequestBody BankRequest request) {
        if (request.getId() == null) {
            throw new IdIsRequiredException();
        }

        bankService.delete(request.getId());
    }
}
