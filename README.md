# BitcoinWallet
Anymind Bitcoin Wallet Project

This project saves the imaginary bitcoins sent by apis and saves them in a wallet. 

Bitcoins can be sent by api as below. 
Request url : http://localhost:8443/SaveBitcoin
Request body : 
{
"datetime": "2010-01-03T20:30:00+00:00",
"amount": 10
}

On success the program will return the output message 'Bitcoin transaction successfully recorded'
datetime supports most of the date time formats. If offset is not provided, then it will be converted to UTC time and the transaction will be saved in the database and wallet will be updated with the amount. If time is not present it will midnight as the time and process the transaction.

You can hit below api to get the balance between two times. It will return the total amount in that wallet during the period per hour whenever there has been a payment to the wallet. Please keep both start date time and end date time in the same format. 
Request url : http://localhost:8443/GetBalance
Request body :
{
"startDatetime": "2019-10-05T00:48:01+00:00",
"endDatetime": "2019-11-30T18:48:02+00:00"
}

Output will look like below
[
    {
        "datetime": "2019-10-05T17:00:00+00:00",
        "amount": 135.0
    },
    {
        "datetime": "2019-10-08T21:00:00+00:00",
        "amount": 140.0
    },
    {
        "datetime": "2019-11-03T21:00:00+00:00",
        "amount": 165.0
    }
]


Please find the table structure for the program below.

create schema bitcoin;
use bitcoin;
create table btc_transaction(
	id int auto_increment not null,
    bitcoin decimal(12,4),
	received_time timestamp,
    primary key(id)
);
create table btc_history(
	id int auto_increment not null,
    total_btc decimal(12,4),
    until_time timestamp,
    primary key(id)
);

select * from btc_transaction;
select * from btc_history;
