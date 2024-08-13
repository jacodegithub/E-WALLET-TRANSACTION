package com.e_wallet.transactions;

import java.time.LocalDateTime;

import java.util.LinkedList;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "base_transaction")
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class BaseTransaction {

	 @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
//	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "txn_seq")
//    @SequenceGenerator(name = "txn_seq", sequenceName = "txn_sequence", allocationSize = 1)
    private Long id;

    private double balance;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "next_txn_id")
    @JsonIgnore
    private BaseTransaction nextTxn;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "prev_txn_id")
    private BaseTransaction prevTxn;

    @Column(name = "txn_time")
    private LocalDateTime txnTime;

    public BaseTransaction() {}

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public BaseTransaction getPrevTxn() {
        return prevTxn;
    }

    public void setPrevTxn(BaseTransaction prevTxn) {
        this.prevTxn = prevTxn;
    }

    public BaseTransaction getNextTxn() {
        return nextTxn;
    }

    public void setNextTxn(BaseTransaction nextTxn) {
        this.nextTxn = nextTxn;
    }

    public LocalDateTime getTxnTime() {
        return txnTime;
    }

    public void setTxnTime(LocalDateTime txnTime) {
        this.txnTime = txnTime;
    }
}

//    private static class Node<E> {
//        E item;
//        Node<E> next;
//        Node<E> prev;
//
//        Node(Node<E> prev, E element, Node<E> next) {
//            this.item = element;
//            this.next = next;
//            this.prev = prev;
//        }
//    }