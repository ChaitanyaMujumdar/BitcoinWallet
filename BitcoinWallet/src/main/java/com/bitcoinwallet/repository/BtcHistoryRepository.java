package com.bitcoinwallet.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

import com.bitcoinwallet.entity.BtcHistory;

public interface BtcHistoryRepository extends PagingAndSortingRepository<BtcHistory,Integer>{
	
	@Transactional
	@Modifying
	@Query("update BtcHistory set total_btc = total_btc+ ?1 where until_time=?2")
	public void updateBtcHistory(double btcAmount, Timestamp receivedAtHour);

	@Query("select sum(bh.totalBtc) from BtcHistory bh where bh.untilTime< ?1")
	public Double getTotalTillStartDatetime(Timestamp start);

	public BtcHistory findByUntilTime(Timestamp untilTime);
	
	public List<BtcHistory> findAllByUntilTimeBetween(Timestamp start, Timestamp end,Sort sort);
	
	
	

}
