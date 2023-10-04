#!/bin/bash

python3 /app/pythonHTTPServer.py &

python3 /app/DeleteFilesEvery30Seconds.py &

python3 /app/main.py
