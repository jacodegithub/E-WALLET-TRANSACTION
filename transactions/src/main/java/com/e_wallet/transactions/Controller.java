package com.e_wallet.transactions;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@Autowired
	private TransactionService txnService;
	
	@Autowired
	private AccountRepository acctRepo;
	
	@GetMapping("/getMoneyTxns")
	public List<AmountTxn> getAllAmtTxns() {
		List<AmountTxn> txns = txnService.getAllTransaction();
		return txns;
	}
	
	@PostMapping("/transaction")
	public String transaction(@RequestBody TxnRequest amtTxn) {
		try {
			txnService.addTransaction(amtTxn);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return "Transaction Successfull..!!!";
	}
	
	@PostMapping("/addAccount") 
	public String addAccount(@RequestBody Account account) {
		try {
			Account acc = new Account();
			acc.setUsername(account.getUsername());
			acc.setAccountId(account.getAccountId());
			acc.setBalance(account.getBalance());
			acctRepo.save(account);
		}catch(Exception e) {
			System.out.println("Error while creating account...!!! "+e);
		}
		
		return "Account Successfully created...!!!";
	}
}
