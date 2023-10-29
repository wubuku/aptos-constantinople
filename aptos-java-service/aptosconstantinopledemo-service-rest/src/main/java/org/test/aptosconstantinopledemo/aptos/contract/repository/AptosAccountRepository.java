package org.test.aptosconstantinopledemo.aptos.contract.repository;

import org.test.aptosconstantinopledemo.aptos.contract.AptosAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AptosAccountRepository extends JpaRepository<AptosAccount, String> {
    
}
