package com.bitcointransactions.record.model;

import java.sql.Timestamp;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class WalletBalanceAtHour {
	
	private String datetime;
	private Double amount;
	
	
	public WalletBalanceAtHour(Double amount, String datetime) {
		this.datetime = datetime;
		this.amount = amount;
	}

	public WalletBalanceAtHour(Double amount, Timestamp untilTime) {
		this.datetime = untilTime.toLocalDateTime().atOffset(ZoneOffset.UTC).format(DateTimeFormatter.ofPattern("uuuu-MM-dd'T'HH:mm:ssxxx"));
		this.amount = amount;
	}

	public String getDatetime() {
		return datetime;
	}
	public void setDatetime(String datetime) {
		this.datetime = datetime;
	}
	public Double getAmount() {
		return amount;
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	
}
