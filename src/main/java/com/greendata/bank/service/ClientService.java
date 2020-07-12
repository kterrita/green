package com.greendata.bank.service;

import com.greendata.bank.entity.BusinessEntity;
import com.greendata.bank.entity.Client;
import com.greendata.bank.entity.dto.ClientDto;
import com.greendata.bank.entity.mapper.ClientMapper;
import com.greendata.bank.exception.ClientNotFoundException;
import com.greendata.bank.repository.ClientRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClientService {
    private final ClientMapper clientMapper = new ClientMapper();
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public List<ClientDto> getAllClients() {
        final List<Client> clients = clientRepository.findAll();
        return clients.stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    public ClientDto getClientById(Long clientId) {
        return clientMapper.toDto(
                clientRepository.findById(clientId)
                .orElseThrow(() -> new ClientNotFoundException(String.format("Client with id %d is not found", clientId)))
        );
    }

    public List<ClientDto> getAllClientsOrderedAsc(String property) {
        final List<Client> clients = clientRepository.findAll(Sort.by(property).ascending());
        return clients.stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ClientDto> getAllClientsOrderedDesc(String property) {
        final List<Client> clients = clientRepository.findAll(Sort.by(property).descending());
        return clients.stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ClientDto> getAllByNameContains(String pattern) {
        final List<Client> clients = clientRepository.findAllByNameContains(pattern);
        return clients.stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<ClientDto> getAllByBusinessType(String entity) {
        BusinessEntity type = BusinessEntity.valueOf(entity);
        final List<Client> clients = clientRepository.findAllByBusinessType(type);
        return clients.stream()
                .map(clientMapper::toDto)
                .collect(Collectors.toList());
    }

    public ClientDto getClientByName(String name) {
        return clientMapper.toDto(
                clientRepository.findClientByName(name)
                        .orElseThrow(() -> new ClientNotFoundException(""))
        );
    }

}
