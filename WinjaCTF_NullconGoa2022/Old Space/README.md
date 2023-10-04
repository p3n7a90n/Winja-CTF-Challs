## Category
Web

## Description
This challenge is based on the client side encryption

## Solution

1. In the client side, user input is getting encrypted and then the request is getting served to the server.
2. User has to look into the js code to understand the encryption, write some custom code to bruteforce the username and password.
3. User has to look into the response, decrypt it to get the flag corresponding to the correct username and password.


Top 100 password lists
https://github.com/WillieStevenson/top-100-passwords/blob/master/password-list.txt

Note: Username and password is same.

Decrypt Code
```python3
function callback(data,status){

    var decrypted = CryptoJS.AES.decrypt(parse(data), key);
    
    console.log("decrypted: ",decrypted.toString(CryptoJS.enc.Utf8));

}

```
