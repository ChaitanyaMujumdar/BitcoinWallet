package com.bitcointransactions.record.model;

import java.sql.Timestamp;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Component;

@Component
public class BitcoinRecord {
	
	private OffsetDateTime datetime;
	private Double amount;
	
	public BitcoinRecord() {
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
