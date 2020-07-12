package com.greendata.bank.controller;

import com.greendata.bank.controller.request.DepositRequest;
import com.greendata.bank.entity.dto.DepositDto;
import com.greendata.bank.exception.FieldIsNotAllowedForUpdateOperation;
import com.greendata.bank.exception.IdIsRequiredException;
import com.greendata.bank.service.DepositService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.List;

@RestController
@RequestMapping("/deposits")
public class DepositController {
    private final DepositService depositService;

    public DepositController(DepositService depositService) {
        this.depositService = depositService;
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<DepositDto> getAllClients() {
        return depositService.getAllDeposits();
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public DepositDto getBankById(@PathVariable Long id) {
        return depositService.getDepositById(id);
    }

    @GetMapping("/before/{time}")
    @ResponseStatus(HttpStatus.OK)
    List<DepositDto> findAllByCreatedIsBeforeOrderByCreatedAsc(@PathVariable Instant time) {
        return depositService.findAllByCreatedIsBeforeOrderByCreatedAsc(time);
    }

    @GetMapping("/after/{time}")
    @ResponseStatus(HttpStatus.OK)
    List<DepositDto> findAllByCreatedIsAfterOrderByCreatedAsc(@PathVariable Instant time) {
        return depositService.findAllByCreatedIsAfterOrderByCreatedAsc(time);
    }

    @GetMapping("/bank/{bankId}/client/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public List<DepositDto> findDepositsByBankIdAndClientId(@PathVariable Long bankId, @PathVariable Long clientId) {
        return depositService.findDepositsByBankIdAndClientId(bankId, clientId);
    }

    @GetMapping("/bank/{bankId}}")
    @ResponseStatus(HttpStatus.OK)
    public List<DepositDto> findDepositsByBankId(@PathVariable Long bankId) {
        return depositService.findDepositsByBankId(bankId);
    }

    @GetMapping("/client/{clientId}")
    @ResponseStatus(HttpStatus.OK)
    public List<DepositDto> findDepositsByClientId(@PathVariable Long clientId) {
        return depositService.findDepositsByClientId(clientId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public DepositDto create(@RequestBody @NotNull DepositRequest request) {
        if (request.getId() != null) {
            throw new FieldIsNotAllowedForUpdateOperation("Id field is not allowed");
        }
        return depositService.createOrUpdate(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public DepositDto update(@RequestBody @NotNull DepositRequest request) {
        if (request.getId() == null) {
            throw new IdIsRequiredException();
        }
        if (request.getCreated() != null) {
            throw new FieldIsNotAllowedForUpdateOperation("Created field is not allowed");
        }

        return depositService.createOrUpdate(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable @NotNull Long id) {
        depositService.delete(id);
    }
}
