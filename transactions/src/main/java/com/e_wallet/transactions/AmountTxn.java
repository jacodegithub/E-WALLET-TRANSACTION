package com.e_wallet.transactions;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Entity
@Table(name = "amt_txn")
//@AttributeOverrides({
//    @AttributeOverride(name = "txnTime", column = @Column(name = "txn_time")),
//    @AttributeOverride(name = "balance", column = @Column(name = "balance"))
//})
public class AmountTxn extends BaseTransaction {
	
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

    private double creditAmt;
    private double debitAmt;

//    @EmbeddedId
//    private CompositeKey compKey;
    
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "prev_txn_id")
//    private AmountTxn prevTxn;
//
//    @OneToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "next_txn_id")
//    @JsonIgnore
//    private AmountTxn nextTxn;

    @ManyToOne
    @JsonIgnoreProperties
    @JoinColumn(name = "acc_id", nullable = false)
    private Account acct;

    public AmountTxn() {}

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

    public Account getAcct() {
        return acct;
    }

    public void setAcct(Account acct) {
        this.acct = acct;
    }

//    public AmountTxn getNextTxn() {
//        return nextTxn;
//    }
//
//    public void setNextTxn(AmountTxn nextTxn) {
//        this.nextTxn = (AmountTxn) nextTxn;
//    }
//
//    public AmountTxn getPrevTxn() {
//        return prevTxn;
//    }
//
//    public void setPrevTxn(AmountTxn prevTxn) {
//        this.prevTxn = (AmountTxn) prevTxn;
//    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
    
    
}
