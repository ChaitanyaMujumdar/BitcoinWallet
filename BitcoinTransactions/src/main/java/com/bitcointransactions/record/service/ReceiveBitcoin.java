package com.bitcointransactions.record.service;

import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.bitcointransactions.record.model.BitcoinRecord;
import com.bitcointransactions.record.model.BtcHistory;
import com.bitcointransactions.record.model.BtcTransaction;
import com.bitcointransactions.record.repo.BtcHistoryRepository;
import com.bitcointransactions.record.repo.BtcTransactionRepository;

@Component
public class ReceiveBitcoin {
	
	@Autowired
	private BtcTransactionRepository btcTransactionsRepository;
	
	@Autowired
	private BtcHistoryRepository btcHistoryRepository;
	
	private Timestamp untilTime;
	private BtcTransaction bitcoinTransaction;
	
	public boolean saveTransaction(BitcoinRecord bitcoinRecord) {
		
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
