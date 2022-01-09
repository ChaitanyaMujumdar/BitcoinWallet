package com.bitcointransactions.record.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bitcointransactions.record.model.BtcTransaction;

public interface BtcTransactionRepository extends JpaRepository<BtcTransaction,Integer> {

}
