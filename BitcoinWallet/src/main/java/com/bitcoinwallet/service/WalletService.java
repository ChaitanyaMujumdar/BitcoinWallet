package com.bitcoinwallet.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import com.bitcoinwallet.entity.BtcHistory;
import com.bitcoinwallet.model.BtcBalance;
import com.bitcoinwallet.repository.BtcHistoryRepository;

@Component
public class WalletService {

	
	@Autowired
	private BtcHistoryRepository btcHistoryRepository;
	
	@Transactional
	public List<BtcBalance> getBalanceBetween(Date startDate, Date endDate) {
		
		Timestamp start = new Timestamp(startDate.getTime());
		Timestamp end = new Timestamp(endDate.getTime());
		
		Double totalTillStart = (btcHistoryRepository.getTotalTillStartDatetime(start) != null)? btcHistoryRepository.getTotalTillStartDatetime(start) : 0.0;
		List<BtcHistory> balanceHourBy = btcHistoryRepository.findAllByUntilTimeBetween(start,end,Sort.by("untilTime"));
		
		
		for(BtcHistory bh : balanceHourBy) {
			totalTillStart = totalTillStart+bh.getTotalBtc();
			bh.setTotalBtc(totalTillStart);
		}
		
		List<BtcBalance> balances = balanceHourBy.stream()
				.map(btcHistory->new BtcBalance(btcHistory.getTotalBtc(),btcHistory.getUntilTime())).collect(Collectors.toList());
				
		return balances;
	}

	
}
