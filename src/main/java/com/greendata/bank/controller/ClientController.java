package com.greendata.bank.controller;

import com.greendata.bank.controller.request.ClientRequest;
import com.greendata.bank.entity.dto.ClientDto;
import com.greendata.bank.exception.FieldIsNotAllowedForUpdateOperation;
import com.greendata.bank.exception.IdIsRequiredException;
import com.greendata.bank.service.ClientService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/clients")
public class ClientController {
    private final ClientService clientService;

    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/all")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDto> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/id/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDto getBankById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @GetMapping("/sorted/{property}/asc")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDto> getAllBanksOrderedAsc(@PathVariable String property) {
        return clientService.getAllClientsOrderedAsc(property);
    }

    @GetMapping("/sorted/{property}/desc")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDto> getAllBanksOrderedDesc(@PathVariable String property) {
        return clientService.getAllClientsOrderedDesc(property);
    }

    @GetMapping("/pattern/{pattern}")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDto> getAllByNameContains(@PathVariable String pattern) {
        return clientService.getAllByNameContains(pattern);
    }

    @GetMapping("/type/{type}")
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDto> getAllByBusinessType(@PathVariable String type) {
        return clientService.getAllByBusinessType(type);
    }

    @GetMapping("/name/{name}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDto getClientByName(@PathVariable String name) {
        return clientService.getClientByName(name);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ClientDto create(@RequestBody @NotNull ClientRequest request) {
        if (request.getId() != null) {
            throw new FieldIsNotAllowedForUpdateOperation("Id field is not allowed");
        }
        return clientService.createOrUpdate(request);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public ClientDto update(@RequestBody @NotNull ClientRequest request) {
        if (request.getId() == null) {
            throw new IdIsRequiredException();
        }

        return clientService.createOrUpdate(request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable @NotNull Long id) {
        clientService.delete(id);
    }
}
