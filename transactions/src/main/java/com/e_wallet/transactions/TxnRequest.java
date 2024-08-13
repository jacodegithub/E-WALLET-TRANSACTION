package com.e_wallet.transactions;

public class TxnRequest {
	private double creditAmt;
	private double debitAmt;
	private Long acctId;
	
	public TxnRequest() {}

	public double getCreditAmt() {
		return creditAmt;
	}

	public void setCreditAmt(double creditAmt) {
		this.creditAmt = creditAmt;
	}

	public double getDebitAmt() {
		return debitAmt;
	}

	public void setDebitAmt(double debitAmt) {
		this.debitAmt = debitAmt;
	}

	public Long getAcctId() {
		return acctId;
	}

	public void setAcctId(Long acct) {
		this.acctId = acct;
	}
	
	
}
