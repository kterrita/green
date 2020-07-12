package com.greendata.bank.repository;

import com.greendata.bank.entity.Bank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * It contains not all possible request, just a few
 * I can add more requests (if you need, even custom) in the future keeping backward compatibility
 */
@Repository
public interface BankRepository extends JpaRepository<Bank, Long> {

    Optional<Bank> findBankByName(String name);

    Optional<Bank> findBankByBic(Long bic);

    List<Bank> findAllByOrderByNameAsc();

    List<Bank> findAllByOrderByBicAsc();

}
