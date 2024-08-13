package com.e_wallet.transactions;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransactionService {

    @Autowired
    private AmountTxnRepository amtTxnRepo;

    @Autowired
    private AccountRepository accRepo;

    public void addTransaction(TxnRequest txnDto) {
        Account account = accRepo.findByAccountId(txnDto.getAcctId());
        AmountTxn lastTxn = amtTxnRepo.findTopByAcctOrderByTxnTimeDesc(account);
        
        double balance = 0.0;
        
        AmountTxn newTxn = new AmountTxn();
        
        newTxn.setAcct(account);
        newTxn.setCreditAmt(txnDto.getCreditAmt());
        newTxn.setDebitAmt(txnDto.getDebitAmt());
        newTxn.setTxnTime(LocalDateTime.now());
        
        if(lastTxn == null) {
        	newTxn.setBalance(txnDto.getCreditAmt());
        	account.setBalance(txnDto.getCreditAmt());
        	
        }
        
        if (lastTxn != null) {
        	
        	balance = lastTxn.getBalance() + txnDto.getCreditAmt();
        	newTxn.setBalance(balance);        	
        	account.setBalance(balance);
        	
            newTxn.setPrevTxn(lastTxn);
            amtTxnRepo.save(newTxn);
            lastTxn.setNextTxn(newTxn);
            amtTxnRepo.save(lastTxn);
        }else {
//        	newTxn.setPrevTxn(null);
//        	newTxn.setNextTxn(null);
        	amtTxnRepo.save(newTxn);
        }
        
        accRepo.save(account);
    }

    public List<AmountTxn> getAllTransaction() {
        List<AmountTxn> transactions = new ArrayList<>();
        
        List<AmountTxn> sep_txns = new ArrayList<>();
        
//        AmountTxn currentTransaction = amtTxnRepo.findFirstByOrderByTxnTimeDesc();
        
        transactions = amtTxnRepo.findAll();
        
        transactions.forEach(t -> {
        	if(t.getNextTxn() == null) {
        		sep_txns.add(t);
        	}
        });
        
//        while (currentTransaction != null) {
//            transactions.add(currentTransaction);
//            currentTransaction = (AmountTxn) currentTransaction.getNextTxn();
//        }
        
        return sep_txns;
    }
}
