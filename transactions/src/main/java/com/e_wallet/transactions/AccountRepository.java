package com.e_wallet.transactions;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
	Account findByAccountId(Long id);
}
