package com.bitcointransactions.record.service;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.bitcointransactions.record.model.BitcoinRecord;
import com.bitcointransactions.record.model.BtcHistory;
import com.bitcointransactions.record.model.WalletBalanceAtHour;
import com.bitcointransactions.record.repo.BtcHistoryRepository;

@Component
public class Wallet {

	
	@Autowired
	private BtcHistoryRepository btcHistoryRepository;
	
	public List<WalletBalanceAtHour> getTotalBalanceBetween(OffsetDateTime startDatetime, OffsetDateTime endDatetime) {
		
		Timestamp start = Timestamp.valueOf(startDatetime.truncatedTo(ChronoUnit.HOURS)
				.plusHours(1).atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
		
		Timestamp end = Timestamp.valueOf(endDatetime.truncatedTo(ChronoUnit.HOURS)
				.plusHours(1).atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
		
		Double totalTillStart = (btcHistoryRepository.getTotalTillStartDatetime(start) != null)? btcHistoryRepository.getTotalTillStartDatetime(start) : 0.0;
		List<BtcHistory> walletBalanceHourBy = btcHistoryRepository.findAllByUntilTimeBetween(start,end,Sort.by("untilTime"));
		
		
		for(BtcHistory bh : walletBalanceHourBy) {
			totalTillStart = totalTillStart+bh.getTotalBtc();
			bh.setTotalBtc(totalTillStart);
		}
		
		List<WalletBalanceAtHour> balanceSheet = walletBalanceHourBy.stream()
				.map(btcHistory->new WalletBalanceAtHour(btcHistory.getTotalBtc(),btcHistory.getUntilTime())).collect(Collectors.toList());
				
		return balanceSheet;
	}

	
}
