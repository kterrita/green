package com.greendata.bank.controller;

import com.greendata.bank.controller.request.BankRequest;
import com.greendata.bank.entity.Bank;
import com.greendata.bank.entity.dto.BankDto;
import com.greendata.bank.repository.BankRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BankControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private BankRepository repository;
    private BankDto bankDto1;
    private BankDto bankDto2;
    private String uri;

    @BeforeEach
    void setUp() {
        BankRequest request1 = createRequest(null, "bName1", 1234567L);
        BankRequest request2 = createRequest(null, "aName2", 1234568L);
        bankDto1 = restTemplate.postForObject("http://localhost:" + port + "/banks", request1, BankDto.class);
        bankDto2 = restTemplate.postForObject("http://localhost:" + port + "/banks", request2, BankDto.class);
        uri = "http://localhost:" + port;
    }

    @AfterEach
    void tearDown() {
        repository.deleteAll();
    }

    @Test
    void getAllBanks() {
        final ResponseEntity<List<BankDto>> response = restTemplate.exchange(uri + "/banks/all",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BankDto>>(){});

        final List<BankDto> banks = response.getBody();
        Objects.requireNonNull(banks);

        assertAll(
                () -> assertTrue(banks.contains(bankDto1)),
                () -> assertTrue(banks.contains(bankDto2)),
                () -> assertEquals(2, banks.size())
                );

    }

    @Test
    void getBankById() {
        final BankDto response = restTemplate.getForObject(uri + "/banks/id/" + bankDto1.getId(),
                BankDto.class);

        assertEquals(response, bankDto1);
    }

    @Test
    void getAllBanksOrderedAsc() {
        final ResponseEntity<List<BankDto>> response = restTemplate.exchange(uri + "/banks/sorted/name/asc",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BankDto>>(){});

        final List<BankDto> banks = response.getBody();
        Objects.requireNonNull(banks);

        assertAll(
                () -> assertEquals(bankDto2, banks.get(0)),
                () -> assertEquals(bankDto1, banks.get(1)),
                () -> assertEquals(2, banks.size())
        );
    }

    @Test
    void getAllBanksOrderedDesc() {
        final ResponseEntity<List<BankDto>> response = restTemplate.exchange(uri + "/banks/sorted/name/desc",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<BankDto>>(){});

        final List<BankDto> banks = response.getBody();
        Objects.requireNonNull(banks);

        assertAll(
                () -> assertEquals(bankDto1, banks.get(0)),
                () -> assertEquals(bankDto2, banks.get(1)),
                () -> assertEquals(2, banks.size())
        );
    }

    @Test
    void findBankByName() {
        final BankDto response = restTemplate.getForObject(uri + "/banks/name/" + bankDto1.getName(),
                BankDto.class);

        assertEquals(response, bankDto1);
    }

    @Test
    void findBankByBic() {
        final BankDto response = restTemplate.getForObject(uri + "/banks/bic/" + bankDto1.getBic(),
                BankDto.class);

        assertEquals(response, bankDto1);
    }

    @Test
    void create() {
        final BankRequest create = createRequest(null, "bName1", 123456911L);
        final BankDto response = restTemplate.postForObject(uri + "/banks", create,
                BankDto.class);

        final BankDto bank = restTemplate.getForObject(uri + "/banks/id/" + response.getId(),
                BankDto.class);

        assertAll(
                () -> assertNotNull(bank),
                () -> assertEquals(response.getId(), bank.getId()),
                () -> assertEquals(response.getName(), bank.getName()),
                () -> assertEquals(response.getBic(), bank.getBic())
        );
    }

    @Test
    void update() {
        final BankRequest create = createRequest(null, "bName100", 123456901L);
        final BankDto response = restTemplate.postForObject(uri + "/banks", create,
                BankDto.class);
        final BankRequest update = createRequest(response.getId(), "bName101", 12345690L);
        restTemplate.put(uri + "/banks", update);

        final BankDto responseUpdated = restTemplate.getForObject(uri + "/banks/id/" + response.getId(),
                BankDto.class);

        assertAll(
                () -> assertNotNull(responseUpdated),
                () -> assertEquals(update.getId(), responseUpdated.getId()),
                () -> assertEquals(update.getName(), responseUpdated.getName()),
                () -> assertEquals(update.getBic(), responseUpdated.getBic())
        );
    }

    @Test
    void delete() {
        restTemplate.delete(uri + "/banks/" + bankDto1.getId());

        final List<Bank> banks = repository.findAll().stream()
                .filter(b -> b.getId().equals(bankDto1.getId()))
                .collect(Collectors.toList());

        assertTrue(banks.isEmpty());
    }

    private BankRequest createRequest(Long id, String name, Long bic) {
        return new BankRequest(id, name, bic);
    }
}