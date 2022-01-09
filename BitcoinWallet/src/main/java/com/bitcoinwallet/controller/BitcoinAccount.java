package com.bitcoinwallet.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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

import com.bitcoinwallet.model.BtcBalance;
import com.bitcoinwallet.model.BtcRecord;
import com.bitcoinwallet.service.TransactionService;
import com.bitcoinwallet.service.WalletService;


@RestController
@RequestMapping("/")
public class BitcoinAccount {

	@Autowired
	private TransactionService transactionService;
	
	@Autowired
	private WalletService walletService;
		
	private static final String[] formats = { 
            "yyyy-MM-dd'T'HH:mm:ss'Z'",   "yyyy-MM-dd'T'HH:mm:ssZ", "yyyy-MM-dd'T'HH:mm:ssX",
            "yyyy-MM-dd'T'HH:mm:ss",      "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'",
            "yyyy-MM-dd'T'HH:mm:ss.SSSZ", "yyyy-MM-dd HH:mm:ss", 
            "MM/dd/yyyy HH:mm:ss",        "MM/dd/yyyy'T'HH:mm:ss.SSS'Z'", 
            "MM/dd/yyyy'T'HH:mm:ss.SSSZ", "MM/dd/yyyy'T'HH:mm:ss.SSS", 
            "MM/dd/yyyy'T'HH:mm:ssZ",     "MM/dd/yyyy'T'HH:mm:ss", 
            "yyyy:MM:dd HH:mm:ss",        "yyyy-MM-dd", };
	
	@PostMapping(value="/SaveBitcoin",consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> saveBitcoin(@RequestBody String record){
		
		try {
			Object obj = new JSONParser().parse(record); 
	        JSONObject jo = (JSONObject) obj;
	        Date date = null; 
	        String datetime = (String) jo.get("datetime");
	        Double amount = Double.parseDouble(jo.get("amount").toString());
	        
	        for (String parse : formats) {
	            SimpleDateFormat sdf = new SimpleDateFormat(parse);
	            try {
	                date = sdf.parse(datetime);
	                break;
	            } catch (Exception e) {
	            	
	            }
	        }
			
			transactionService.saveRecord(new BtcRecord(date,amount));
			return ResponseEntity.ok("Bitcoin transaction successfully recorded");
			
		}
		catch(ParseException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request");
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request");
		}
		
		
	}
	
	@GetMapping(value="/GetBalance",consumes=MediaType.APPLICATION_JSON_VALUE,produces=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> getBalance(@RequestBody String dates) {
		
		try {
			Object obj = new JSONParser().parse(dates); 
	        JSONObject jo = (JSONObject) obj;
	        
	        String startDatetime = (String)jo.get("startDatetime");
	        String endDatetime = (String)jo.get("endDatetime");
	        
	        Date startDate = null; 
	        Date endDate = null; 
	        for (String parse : formats) {
	            SimpleDateFormat sdf = new SimpleDateFormat(parse);
	            try {
	            	startDate = sdf.parse(startDatetime);
	            	endDate = sdf.parse(endDatetime);
	                break;
	            } catch (Exception e) {

	            }
	        }
		
			List<BtcBalance> balances = walletService.getBalanceBetween(startDate,endDate);
			return ResponseEntity.ok(balances);
		
		} 
		catch (ParseException e1) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "entity not found");
		}
		catch(Exception e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Bad Request");
		}
	}
}
