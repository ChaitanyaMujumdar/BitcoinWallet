package com.bitcoinwallet.service;

import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bitcoinwallet.model.BtcRecord;
import com.bitcoinwallet.entity.BtcHistory;
import com.bitcoinwallet.entity.BtcTransaction;
import com.bitcoinwallet.repository.BtcHistoryRepository;
import com.bitcoinwallet.repository.BtcTransactionRepository;

@Component
public class TransactionService {
	
	@Autowired
	private BtcTransactionRepository btcTransactionsRepository;
	
	@Autowired
	private BtcHistoryRepository btcHistoryRepository;
	
	private Timestamp untilTime;
	private BtcTransaction bitcoinTransaction;
	
	@Transactional
	public boolean saveRecord(BtcRecord bitcoinRecord) {
		
		bitcoinTransaction = new BtcTransaction(bitcoinRecord);
		btcTransactionsRepository.save(bitcoinTransaction);
		
		untilTime = Timestamp.valueOf(bitcoinRecord.getDatetime().truncatedTo(ChronoUnit.HOURS)
				.plusHours(1).atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
		
		
		if (btcHistoryRepository.findByUntilTime(untilTime)==null) {
			btcHistoryRepository.save(new BtcHistory(bitcoinRecord.getAmount(),untilTime));
		}
		else {
			btcHistoryRepository.updateBtcHistory(bitcoinTransaction.getAmount(), untilTime);
		}
		
		return true;
		
	}

}
