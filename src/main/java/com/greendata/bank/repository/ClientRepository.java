package com.greendata.bank.repository;

import com.greendata.bank.entity.BusinessEntity;
import com.greendata.bank.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * It contains not all possible request, just a few
 * I can add more requests (if you need, even custom) in the future keeping backward compatibility
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    List<Client> findAllByOrderByNameAsc();

    List<Client> findAllByOrderByBusinessTypeAsc();

    List<Client> findAllByOrderByIdAsc();

    List<Client> findAllByNameContains(String pattern);

    List<Client> findAllByBusinessType(BusinessEntity type);

    Optional<Client> findClientByName(String name);
}
