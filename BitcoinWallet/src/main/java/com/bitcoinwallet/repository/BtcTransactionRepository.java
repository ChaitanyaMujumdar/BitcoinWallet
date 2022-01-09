package com.bitcoinwallet.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitcoinwallet.entity.BtcTransaction;

public interface BtcTransactionRepository extends JpaRepository<BtcTransaction,Integer> {

}
