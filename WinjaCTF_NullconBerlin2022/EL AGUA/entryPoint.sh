#!/bin/bash

python3 /usr/local/webapp/pythonHTTPServer.py &

python3 /usr/local/webapp/DeleteFilesEvery30Seconds.py &

java -jar /home/RunJar/app.jar

