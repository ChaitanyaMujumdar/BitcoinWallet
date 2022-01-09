package com.bitcointransactions.record.controller;

import java.time.OffsetDateTime;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.bitcointransactions.record.model.BitcoinRecord;
import com.bitcointransactions.record.model.WalletBalanceAtHour;
import com.bitcointransactions.record.service.ReceiveBitcoin;
import com.bitcointransactions.record.service.Wallet;

@RestController
@RequestMapping("/")
public class BitcoinTransactions {

	@Autowired
	private ReceiveBitcoin receiveBitcoin;
	
	@Autowired
	private Wallet wallet;
		
	@PostMapping(value="/SaveRecord",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveRecord(@RequestBody BitcoinRecord bitcoinRecord) {
		
		receiveBitcoin.saveTransaction(bitcoinRecord);
		return ResponseEntity.ok("Bitcoin transaction successfully recorded");
		
	}
	
	@GetMapping(value="/GetBalance",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getWalletBalance(@RequestBody String dates) {
		
		try {
			Object obj = new JSONParser().parse(dates); 
	        JSONObject jo = (JSONObject) obj;
	          
	        OffsetDateTime startDatetime = OffsetDateTime.parse((String)jo.get("startDatetime"));
			OffsetDateTime endDatetime = OffsetDateTime.parse((String)jo.get("endDatetime"));
		
			List<WalletBalanceAtHour> history = wallet.getTotalBalanceBetween(startDatetime,endDatetime);
			return ResponseEntity.ok(history);
		
		} 
		catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
		}
	}
}
