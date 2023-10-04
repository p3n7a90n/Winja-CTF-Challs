## Category
Source Code Review

## Description
Challenge is based on the SSL/TLS websocket config.

## Solution
1. Host any websocket server with ssl_context and the server certificate should be signed by lets encrypt
2. After connecting to the server, send the flag value to get the flag from the client

Server python file
```python
# https://websockets.readthedocs.io/en/stable/intro/quickstart.html#secure-server-example

#!/usr/bin/env python

import asyncio
import pathlib
import ssl
import websockets

async def hello(websocket):
    async for message in websocket:
        print(message)
        await websocket.send("flag")

    # name = await websocket.recv()
    # print(f"<<< {name}")

    # greeting = f"Hello {name}!"

    # await websocket.send(greeting)
    # print(f">>> {greeting}")

ssl_context = ssl.SSLContext(ssl.PROTOCOL_TLS_SERVER)
#localhost_pem = pathlib.Path(__file__).with_name("localhost.pem")

#localhost_pem = pathlib.Path("/home/p3n7a90n/Documents/Winja/NullconGoa2022/TestFiles/01ce-115-99-221-128.ngrok.io/server_cert.pem")
localhost_pem = pathlib.Path("/home/p3n7a90n/Documents/Winja/NullconGoa2022/TestFiles/selfSigned/self_signed.pem")
ssl_context.load_cert_chain(localhost_pem)
#ssl_context.verify_mode = ssl.CERT_NONE

async def main(): #, ssl=ssl_context
    async with websockets.serve(hello, "localhost", 8765,ssl=ssl_context):
        await asyncio.Future()  # run forever

if __name__ == "__main__":
    asyncio.run(main())

```
./ngrok tcp 8765
https://satcom.chall.winja.site/websocket?domain=4.tcp.ngrok.io&port=13371
