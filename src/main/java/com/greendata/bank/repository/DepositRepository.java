package com.greendata.bank.repository;

import com.greendata.bank.entity.Deposit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

/**
 * It contains not all possible request, just a few
 * I can add more requests (if you need, even custom) in the future keeping backward compatibility
 */
@Repository
public interface DepositRepository extends JpaRepository<Deposit, Long> {

    List<Deposit> findAllByOrderByCreatedAsc();

    List<Deposit> findAllByOrderByCreatedDesc();

    List<Deposit> findAllByOrderByInterestRateAsc();

    List<Deposit> findAllByOrderByTermAsc();

    List<Deposit> findAllByCreatedIsBeforeOrderByCreatedAsc(Instant time);

    List<Deposit> findAllByCreatedIsAfterOrderByCreatedAsc(Instant time);

    List<Deposit> findDepositsByBank_IdAndClient_Id(Long bankId, Long clientId);

    List<Deposit> findDepositsByBank_Id(Long bankId);

    List<Deposit> findDepositsByClient_Id(Long clientId);
}
