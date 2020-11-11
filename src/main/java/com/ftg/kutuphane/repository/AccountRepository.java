package com.ftg.kutuphane.repository;

import com.ftg.kutuphane.entitiy.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByUserName(String userName);
}
