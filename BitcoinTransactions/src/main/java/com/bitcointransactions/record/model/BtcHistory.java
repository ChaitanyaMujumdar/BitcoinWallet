package com.bitcointransactions.record.model;

import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="btc_history")
public class BtcHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="total_btc")
	private Double totalBtc;
	
	@Column(name="until_time")
	private Timestamp untilTime;

	
	
	public BtcHistory() {
		
	}

	public BtcHistory(double totalBtc, Timestamp untilTime) {
		this.totalBtc = totalBtc;
		this.untilTime = untilTime;
	}

	public BtcHistory(BitcoinRecord bitcoinRecord) {
		totalBtc = bitcoinRecord.getAmount();
		untilTime = Timestamp.valueOf(bitcoinRecord.getDatetime().truncatedTo(ChronoUnit.HOURS)
				.plusHours(1).atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
	}

	public Double getTotalBtc() {
		return totalBtc;
	}

	public void setTotalBtc(Double totalBtc) {
		this.totalBtc = totalBtc;
	}

	public Timestamp getUntilTime() {
		return untilTime;
	}

	public void setUntilTime(Timestamp untilTime) {
		this.untilTime = untilTime;
	}

	@Override
	public String toString() {
		return "BtcHistory [totalBtc=" + totalBtc + ", untilTime=" + untilTime + "]";
	}
	
	
}
