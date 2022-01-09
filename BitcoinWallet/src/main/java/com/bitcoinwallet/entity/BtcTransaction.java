package com.bitcoinwallet.entity;

import java.sql.Timestamp;
import java.time.ZoneOffset;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.bitcoinwallet.model.BtcRecord;

@Entity
@Table(name="btc_transaction")
public class BtcTransaction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name="bitcoin")
	private double amount;
	
	@Column(name="received_time")
	private Timestamp datetime;
	
	public BtcTransaction() {

	}

	public BtcTransaction(BtcRecord bitcoinRecord) {
		
		this.datetime= Timestamp.valueOf(bitcoinRecord.getDatetime().
				atZoneSameInstant(ZoneOffset.UTC).toLocalDateTime());
		this.amount = bitcoinRecord.getAmount();
	}

	public Timestamp getDatetime() {
		return datetime;
	}

	public void setDatetime(Timestamp datetime) {
		this.datetime = datetime;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "BtcTransaction [ amount=" + amount+" datetime=" + datetime + "]";
	}
	
	
	
}
