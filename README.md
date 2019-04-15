# ExchangeRateAggregationService
Spring Boot RESTful API service for exchange rate operations with bank currencies values.

------------------API DOCUMENTATION---------------------------------------------------------------------

Service can store data of currencies from various banks. Use upload form to insert file values in the system.
File loader consumes file types : .json, .xml, .csv. Each file stores information: currencyCodes, buying and selling values.
Supported data formats are shown below.


-----------------REQUESTS and OPERATIONS------------------------------------------------------------------------------
	
	GET "buying/{currency}"
  GET "buying/{currency}/ascendingsort"
  GET "buying/{currency}/descendingsort"
    show a specified currency's exchange rate for buying in json format. Information can be sorted in ascending or descending way
      by adding "/ascendingsort" or "/descendingsort" to the address
      Example: "buying/EUR" .
	
  GET "selling/{currency}"
  GET "selling/{currency}/ascendingsort"
  GET "selling/{currency}/descendingsort"
    show a specified currency's exchange rate for selling in json format. Information can be sorted in ascending or descending way
      by adding "/ascendingsort" or "/descendingsort" to the address
	
	GET "/report"
	  show the information about best sellings and buyings offers for each available currency from all banks in json format.
	
	POST "/banks"
    add new bank data in through the request body
	
	PUT "/banks/{bank}/selling/{currency}?value={}"
	  update selling value for a specified currency of a specified bank by value 
    Example : "/banks/OTP/selling/RUB?value=0.0" - forbid the selling of RUB for OTB bank
	}
  
  PUT "/banks/{bank}/buying/{currency}?value={}"
	  update selling value for a specified currency of a specified bank by value 
    Example : "/banks/Alfa/buying/RUB?value=2.5" - set the buying rate of RUB to 2.5 for Alfa bank
	}
  
  GET "/banks"
		show all uploaded banks data in json format.
    
  DELETE "/banks/{bank}/{currency}"
	  delete all selling and buying offers of a specified currency for a specified bank.
    Example: "/banks/Privat/CHF" .


-----------------SUPPORTED DATA FORMATS------------------------------------------------------------------
.json 

"
[
  {
		"currencyCode":"USD",
		"buying":27.7,
		"selling":27.05
	},
	{
		"currencyCode":"EUR",
		"buying":30.22,
		"selling":29.32
	},
]
"


.xml

"
<ratesList>
	<exchangeRate>
		<currencyCode>USD</currencyCode>
		<buying>29.50</buying>
		<selling>26.10</selling>
	</exchangeRate>
	<exchangeRate>
		<currencyCode>EUR</currencyCode>
		<buying>30.00</buying>
		<selling>31.00</selling>
	</exchangeRate>
</ratesList>
"


.csv - first row is a header

"
currencyCode, buying, selling
USD, 28.30, 26.55
EUR,30.22,31.32
CHF,22.40,29.68
"

