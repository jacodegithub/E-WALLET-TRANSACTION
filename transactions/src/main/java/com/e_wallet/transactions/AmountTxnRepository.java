package com.e_wallet.transactions;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

public interface AmountTxnRepository extends JpaRepository<AmountTxn, Long> {
	AmountTxn findTopByAcctOrderByTxnTimeDesc(Account acc);
		
	AmountTxn findFirstByOrderByTxnTimeDesc();
	
}
