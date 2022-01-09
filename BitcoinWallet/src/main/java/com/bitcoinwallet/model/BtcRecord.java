package com.bitcoinwallet.model;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class BtcRecord {
	
	private OffsetDateTime datetime;
	private Double amount;
	
	public BtcRecord() {
	}
	
	public BtcRecord(Date date, Double amount) {
		this.datetime = date.toInstant().atOffset(ZoneOffset.UTC);
		this.amount = amount;
	}

	public OffsetDateTime getDatetime() {
		return datetime;
	}
	public void setDatetime(OffsetDateTime datetime) {
		this.datetime = datetime;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "BitcoinRecord [datetime=" + datetime + ", amount=" + amount + "]";
	}
	
	
}
