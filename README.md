# Customer SSN and Address validator 
Validate customer SSN and Address using below API call.

API: http://{host}:{port}/customers/validate

Method: POST

body:
````
{

	"name": "abc",
	"phone": "1034895",
	"ssn": "0000-000-9600",
	"address": {
		
		"street": "1000 N 4 st",
		"city": "fairfield",
		"state": "iowa",
		"zipCode": "53446",
		"country": "USA"
	}
}
````
N.B. host-> localhost port-> 8080